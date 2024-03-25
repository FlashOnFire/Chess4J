package fr.onyx.render.shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

public abstract class ShaderProgram {
	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;

	public ShaderProgram() {
		this.programId = 0;
		this.vertexShaderId = 0;
		this.fragmentShaderId = 0;
	}

	public void Start() {
		GL30.glUseProgram(this.programId);
	}

	public void Stop() {
		GL30.glUseProgram(0);
	}

	public void LoadProgram(String vertexSource, String fragmentSource) {
		this.vertexShaderId = LoadShader(vertexSource, GL30.GL_VERTEX_SHADER);
		this.fragmentShaderId = LoadShader(fragmentSource, GL30.GL_FRAGMENT_SHADER);

		this.programId = GL30.glCreateProgram();
		GL30.glAttachShader(this.programId, vertexShaderId);
		GL30.glAttachShader(this.programId, this.fragmentShaderId);
		GL30.glLinkProgram(this.programId);
		GL30.glValidateProgram(this.programId);

		if (GL30.glGetProgrami(programId, GL30.GL_VALIDATE_STATUS) == 0) {
			System.err.println("Warning validating Shader code: " + GL30.glGetProgramInfoLog(programId, 1024));
		}

		GetAllUniformLocation();
	}

	private int LoadShader(String source, int type) {
		int shaderId = GL30.glCreateShader(type);

		GL30.glShaderSource(shaderId, source);
		GL30.glCompileShader(shaderId);

		IntBuffer compileSuccesful = BufferUtils.createIntBuffer(1);
		GL30.glGetShaderiv(shaderId, GL30.GL_COMPILE_STATUS, compileSuccesful);

		if (compileSuccesful.get() != 1) {
			System.out.println("Shader did not compile !");
			return -1;

		}

		return shaderId;
	}

	protected abstract void GetAllUniformLocation();

	protected int GetUniformLocation(String uniformName) {
		int location = GL30.glGetUniformLocation(programId, uniformName);
		if (location == -1) {
			System.out.println("Uniform value not found !");
		}
		return location;
	}

	public void LoadFloat(int location, float value) {
		GL30.glUniform1f(location, value);
	}

	public void LoadInt(int location, int value) {
		GL30.glUniform1i(location, value);
	}

	public void LoadVector(int location, Vector3f vector) {
		GL30.glUniform3f(location, vector.x, vector.y, vector.z);
	}

	public void LoadMat4(int location, Matrix4f mat) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			FloatBuffer buffer = mat.get(stack.mallocFloat(16));
			GL30.glUniformMatrix4fv(location, false, buffer);
		}
	}
}
