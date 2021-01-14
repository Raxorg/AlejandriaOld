package com.frontanilla.alejandria.modules.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

class CameraHandler {

    //private Screen screen;
    private static final float GRID_WIDTH = 3000f, GRID_HEIGHT = 1500f;
    // Logic
    private float pivotX, pivotY;

    void newPivot(float x, float y) {
        pivotX = x;
        pivotY = y;
    }

    void moveCamera(float x, float y) {
        OrthographicCamera camera = new OrthographicCamera(); //screen.getCamera();
        camera.translate(pivotX - x, pivotY - y, 0f);
        float cameraX = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, GRID_WIDTH - camera.viewportWidth / 2f);
        float cameraY = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2f, GRID_HEIGHT - camera.viewportHeight / 2f);
        camera.position.x = cameraX;
        camera.position.y = cameraY;
        camera.update();
    }

    /*void setScreen(Screen screen) {
        this.screen = screen;
    }*/
}