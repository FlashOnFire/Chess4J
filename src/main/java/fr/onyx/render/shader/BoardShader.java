package fr.onyx.render.shader;

public class BoardShader extends ShaderProgram {

	private static String vertexShader = """
			#version 330

			layout(location = 0) in vec3 position;
			layout(location = 1) in vec3 color;

			out vec3 pass_color;

			void main(void){
				gl_Position = vec4(position.x, position.z, position.y, 1.0);
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

	public BoardShader() {

	}

	public void LoadShader() {
		super.LoadProgram(vertexShader, fragmentShader);
		GetAllUniformLocation();
	}

	@Override
	protected void GetAllUniformLocation() {

	}
}
