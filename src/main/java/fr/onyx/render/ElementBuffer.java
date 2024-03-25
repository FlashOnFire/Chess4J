package fr.onyx.render;

import org.lwjgl.opengl.GL30;

public class ElementBuffer {
	private int id;
	private int indiciesCount;

	public ElementBuffer(int[] indicies) {
		this.indiciesCount = indicies.length;
		this.id = GL30.glGenBuffers();
		Bind();
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, indicies.length * 4, GL30.GL_STATIC_DRAW);
		GL30.glBufferSubData(GL30.GL_ELEMENT_ARRAY_BUFFER, 0, indicies);
		Unbind();
	}

	public void Destroy() {
		GL30.glDeleteBuffers(this.id);
	}

	public void Bind() {
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, this.id);
	}

	public void Unbind() {
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public int GetIndiciesCount() {
		return this.indiciesCount;
	}
}
