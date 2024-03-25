package fr.onyx.render;

import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class Camera {
    public static final float fov = 70.0f;
    public static final float aspect = 70.0f;
    public static final float zNear = 0.01f;
    public static final float zFar = 100.0f;

    private Vector3f pos;

    private float yaw = 0.0f;
    private float pitch = 0.0f;

    public Camera() {
    }

    public void move(float x, float y) {
        this.pos.x += x;
        this.pos.y += y;
    }

    public void rotate(float yaw, float pitch) {
        this.yaw += yaw;
        this.pitch += pitch;
    }

    public Vector3f getPos() {
        return pos;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getFov() {
        return fov;
    }

    public void setX(float x) {
        this.pos.x = x;
    }

    public void setY(float y) {
        this.pos.y = y;
    }

    public void setZ(float z) {
        this.pos.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void reset() {
        resetPosition();
        resetRotation();
    }

    public void resetPosition() {
        pos = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    public void resetRotation() {
        yaw = 0.0f;
        pitch = 0.0f;
    }

    public void moveForward(float distance) {
        pos.x += distance * (float) Math.cos(Math.toRadians(yaw));
        pos.y += distance * (float) Math.sin(Math.toRadians(yaw));
    }

    public void moveRight(float distance) {
        pos.x += distance * (float) Math.cos(Math.toRadians(yaw));
        pos.y += distance * (float) Math.sin(Math.toRadians(yaw));
    }

    public void moveUp(float distance) {
        pos.z += distance;
    }

    public void moveDown(float distance) {
        pos.z -= distance;
    }

    public void addYaw(float angle) {
        yaw += angle;
    }

    public void addPitch(float angle) {
        pitch += angle;
    }

    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }

    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Matrix4f getMatrix() {
        Vector3f forward = new Vector3f(
                (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))),
                (float) (Math.sin(Math.toRadians(pitch))),
                (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)))
        );

        return new Matrix4f()
                .perspective((float) Math.toRadians(fov), aspect, zNear, zFar)
                .lookAt(pos, forward, new Vector3f(0.0f, 0.0f, 1.0f));
    }
}
