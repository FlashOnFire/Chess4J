package fr.onyx.render;

import org.joml.Vector3f;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL30.*;
import fr.onyx.render.shader.BoardShader;

public class Renderer {
	private BoardShader shader;
	private VertexArray vao;

	private static int BOARD_WIDTH = 8;
	private static int BOARD_HEIGHT = 8;
	private static int BOARD_SIZE = BOARD_WIDTH * BOARD_HEIGHT;
	private static int SQUARE_VERTEX_COUNT = 4;

	public Renderer() {
		this.shader = new BoardShader();
	}

	public void Init() {
		shader.LoadShader();
		InitBoard();
	}

	private float[] GetBoardPositions() {
		float[] positions = new float[BOARD_SIZE * SQUARE_VERTEX_COUNT * 3];
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				float x = i / (float) BOARD_WIDTH;
				float dx = (i + 1) / (float) BOARD_WIDTH;
				float z = j / (float) BOARD_HEIGHT;
				float dz = (j + 1) / (float) BOARD_HEIGHT;

				float trueX = 2 * x - 1;
				float trueZ = 2 * z - 1;
				float trueDX = 2 * dx - 1;
				float trueDZ = 2 * dz - 1;

				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3] = trueX;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 1] = 0.0f;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 2] = trueZ;

				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 3] = trueDX;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 4] = 0.0f;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 5] = trueZ;

				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 6] = trueX;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 7] = 0.0f;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 8] = trueDZ;

				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 9] = trueDX;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 10] = 0.0f;
				positions[(BOARD_WIDTH * i + j) * SQUARE_VERTEX_COUNT * 3 + 11] = trueDZ;
			}
		}
		return positions;
	}

	private float[] GetBoardColors() {
		float[] colors = new float[BOARD_SIZE * SQUARE_VERTEX_COUNT * 3];
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				Vector3f color;
				if ((i + j) % 2 != 0) {
					color = new Vector3f(1.0f, 1.0f, 1.0f);
				} else {
					color = new Vector3f(0.0f, 0.0f, 0.0f);
				}
				int squareIndex = i * BOARD_WIDTH + j;
				for (int k = 0; k < SQUARE_VERTEX_COUNT; k++) {
					colors[squareIndex * SQUARE_VERTEX_COUNT * 3 + k * 3] = color.x;
					colors[squareIndex * SQUARE_VERTEX_COUNT * 3 + k * 3 + 1] = color.y;
					colors[squareIndex * SQUARE_VERTEX_COUNT * 3 + k * 3 + 2] = color.z;
				}
			}
		}
		return colors;
	}

	private int[] GetBoardIndicies() {
		int[] indices = new int[BOARD_SIZE * 6];
		for (int i = 0; i < BOARD_SIZE; i++) {
			indices[i * 6] = i * 4;
			indices[i * 6 + 1] = i * 4 + 1;
			indices[i * 6 + 2] = i * 4 + 2;
			indices[i * 6 + 3] = i * 4 + 1;
			indices[i * 6 + 4] = i * 4 + 2;
			indices[i * 6 + 5] = i * 4 + 3;
		}
		return indices;
	}

	private void InitBoard() {
		ElementBuffer eBuffer = new ElementBuffer(GetBoardIndicies());
		this.vao = new VertexArray(eBuffer);

		VertexBuffer positionBuffer = new VertexBuffer(GetBoardPositions(), 3);
		positionBuffer.AddVertexAttribPointer(0, 3, 0);

		VertexBuffer colorBuffer = new VertexBuffer(GetBoardColors(), 3);
		colorBuffer.AddVertexAttribPointer(1, 3, 0);

		this.vao.Bind();
		this.vao.BindVertexBuffer(positionBuffer);
		this.vao.BindVertexBuffer(colorBuffer);
		this.vao.Unbind();
	}

	public void Render(Camera cam) {
		this.shader.Start();
		this.shader.SetCamMatrix(cam.getMatrix());
		RenderVao(vao);
	}

	public void RenderVao(VertexArray vertexArray) {
		this.shader.Start();
		vertexArray.Bind();
		GL30.glDrawElements(GL30.GL_TRIANGLES, vertexArray.GetVertexCount(), GL_UNSIGNED_INT, 0);
		vertexArray.Unbind();
	}
}
