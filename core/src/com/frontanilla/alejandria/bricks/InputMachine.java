package com.frontanilla.alejandria.bricks;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputMachine extends InputAdapter implements GestureListener {

    // Input related
    private OrthographicCamera camera;
    private Vector3 unprojected;
    private boolean enabled;
    // Camera related
    private float lastZoomDistance;
    private float lastPinchX, lastPinchY;
    // Test
    private InputMachine inputMachine;

    public InputMachine(OrthographicCamera camera) {
        this.camera = camera;
        unprojected = new Vector3();
        enabled = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(camera.unproject(new Vector3(screenX, screenY, 0)));
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!enabled || pointer != 0) {
            return false;
        }
        unprojected.set(camera.unproject(new Vector3(screenX, screenY, 0)));
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!enabled) {
            return false;
        }
        unprojected.set(camera.unproject(new Vector3(x, y, 0)));
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, deltaY);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
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
        camera.zoom -= distanceDifference / 300;
        // Pan
        float deltaX = (pinchX - lastPinchX) * camera.zoom;
        float deltaY = (pinchY - lastPinchY) * camera.zoom;
        camera.translate(-deltaX, deltaY);
        // We need to update these for future calculations
        lastZoomDistance = distance;
        lastPinchX = (pointer1.x + pointer2.x) / 2;
        lastPinchY = (pointer1.y + pointer2.y) / 2;
        return false;
    }

    @Override
    public void pinchStop() {

    }

    private void initInputMachine(OrthographicCamera camera) {
        inputMachine = new InputMachine(camera);
        inputMachine.setEnabled(true);
        float halfSquareSize = 5f;
        float tapSecondsInterval = 0.55f;
        float longPressDuration = 1.25f;
        float maxFlingDelay = 0.15f;
        GestureDetector gestureDetector = new GestureDetector(
                halfSquareSize,
                tapSecondsInterval,
                longPressDuration,
                maxFlingDelay,
                inputMachine
        );
    }
}
