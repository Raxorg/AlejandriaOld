package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraHandler {

    // Camera
    private OrthographicCamera dynamicCamera;
    private float lastZoomDistance, lastPinchX, lastPinchY;

    public CameraHandler(OrthographicCamera dynamicCamera) {
        this.dynamicCamera = dynamicCamera;
    }

    public void pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        // Calculate distances
        float initialDistance = initialPointer1.dst(initialPointer2);
        float distance = pointer1.dst(pointer2);
        // Calculate pinch coordinates
        float initialPinchX = (initialPointer1.x + initialPointer2.x) / 2;
        float initialPinchY = (initialPointer1.y + initialPointer2.y) / 2;
        float pinchX = (pointer1.x + pointer2.x) / 2;
        float pinchY = (pointer1.y + pointer2.y) / 2;
        // This to avoid first time zooming or panning horrible behavior
        if (lastZoomDistance == 0) {
            lastZoomDistance = initialDistance;
        }
        if (lastPinchX == lastPinchY && lastPinchX == 0) {
            lastPinchX = initialPinchX;
            lastPinchY = initialPinchY;
        }
        // Zoom
        float distanceDifference = distance - lastZoomDistance;
        dynamicCamera.zoom -= distanceDifference / 2000f;
        // Pan TODO: Think of panning with one finger
        float deltaX = (pinchX - lastPinchX) * dynamicCamera.zoom;
        float deltaY = (pinchY - lastPinchY) * dynamicCamera.zoom;
        dynamicCamera.translate(-deltaX, -deltaY);
        // We need to update these for future calculations
        lastZoomDistance = distance;
        lastPinchX = (pointer1.x + pointer2.x) / 2;
        lastPinchY = (pointer1.y + pointer2.y) / 2;
        dynamicCamera.update();
    }

    public void pinchStop() {
        lastZoomDistance = 0;
        lastPinchX = 0;
        lastPinchY = 0;
    }
}
