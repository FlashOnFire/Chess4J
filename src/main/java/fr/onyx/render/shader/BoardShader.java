package fr.onyx.render.shader;

import org.joml.Matrix4f;

public class BoardShader extends ShaderProgram {

	private static String vertexShader = """
			#version 330

			layout(location = 0) in vec3 position;
			layout(location = 1) in vec3 color;

			uniform mat4 camMatrix;

			out vec3 pass_color;

			void main(void){
				gl_Position = camMatrix * vec4(position, 1.0);
				pass_color = color;
			}
				""";

	private static String fragmentShader = """
			#version 330

			in vec3 pass_color;

			out vec4 out_color;

			void main(void){
				out_color = vec4(pass_color, 1.0);
			}
				""";

	private int location_CamMatrix = 0;

	public BoardShader() {

	}

	public void LoadShader() {
		super.LoadProgram(vertexShader, fragmentShader);
	}

	@Override
	protected void GetAllUniformLocation() {
		location_CamMatrix = GetUniformLocation("camMatrix");
	}

	public void SetCamMatrix(Matrix4f mat) {
		LoadMat4(location_CamMatrix, mat);
	}
}
