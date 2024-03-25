package fr.onyx.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL30;

public class VertexArray {
	private int id;
	private ElementBuffer elementBuffer;
	private List<VertexBuffer> vertexBuffers;

	public VertexArray(ElementBuffer elementBuffer) {
		this.id = GL30.glGenVertexArrays();
		this.elementBuffer = elementBuffer;
		this.vertexBuffers = new ArrayList<VertexBuffer>();
		Bind();
		BindElementArrayBuffer();
		Unbind();
	}

	public void Destroy() {
		GL30.glDeleteBuffers(this.id);
	}

	public int GetVertexCount() {
		return this.elementBuffer.GetIndiciesCount();
	}

	public void BindVertexBuffer(VertexBuffer buffer) {
		buffer.Bind();
		buffer.BindVertexAttribs();
		this.vertexBuffers.add(buffer);
	}

	public void Bind() {
		GL30.glBindVertexArray(this.id);
	}

	public void Unbind() {
		GL30.glBindVertexArray(0);
	}

	private void BindElementArrayBuffer() {
		this.elementBuffer.Bind();
	}
}
