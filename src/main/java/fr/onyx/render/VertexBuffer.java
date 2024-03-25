package fr.onyx.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL30;

public class VertexBuffer {
	private int id;
	private int dataStride;
	List<VertexAttribPointer> vertexAttribs;

	public VertexBuffer(float[] data, int stride) {
		this.id = GL30.glGenBuffers();
		this.dataStride = stride;
		this.vertexAttribs = new ArrayList<VertexAttribPointer>();
		Bind();
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, data.length * 4, GL30.GL_STATIC_DRAW);
		GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, 0, data);
		Unbind();
	}

	public void Destroy() {
		GL30.glDeleteBuffers(id);
	}

	public void Bind() {
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, this.id);
	}

	public void Unbind() {
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
	}

	public void AddVertexAttribPointer(int index, int coordinateSize, int offset) {
		VertexAttribPointer pointer = new VertexAttribPointer(index, coordinateSize, offset);
		this.vertexAttribs.add(pointer);
	}

	public void BindVertexAttribs() {
		for (VertexAttribPointer vertexAttribPointer : vertexAttribs) {
			GL30.glEnableVertexAttribArray(vertexAttribPointer.index);
			GL30.glVertexAttribPointer(vertexAttribPointer.index, vertexAttribPointer.size, GL_FLOAT, false,
					this.dataStride * 4, vertexAttribPointer.offset);
		}
	}
}
