package com.frontanilla.alejandria.modules.examples3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.frontanilla.alejandria.modules.Module;

public class Base3DModule extends Module {

    private PerspectiveCamera camera;
    private CameraInputController cameraInputController;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance modelInstance;


    @Override
    public void show() {
        // Create camera sized to screens width/height with Field of View of 75 degrees
        camera = new PerspectiveCamera(
                75,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        // Move the camera 25 units back along the z-axis and look at the origin
        camera.position.set(10f, 10f, 25f);
        camera.lookAt(0f, 0f, 0f);
        camera.update();

        // Near and Far (plane) represent the minimum and maximum ranges of the camera in, um, units
        camera.near = 0.1f;
        camera.far = 300.0f;

        // A ModelBatch is like a SpriteBatch, just for models.  Use it to batch up geometry for OpenGL
        modelBatch = new ModelBatch();

        // TODO
        ModelBuilder modelBuilder = new ModelBuilder();

        // TODO
        model = modelBuilder.createCylinder(
                5, 5, 5, 16,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        // TODO
        modelInstance = new ModelInstance(model);

        // TODO
        cameraInputController = new CameraInputController(camera);
        cameraInputController.rotateLeftKey = Input.Keys.A;
        cameraInputController.rotateRightKey = Input.Keys.D;

        // TODO
        Gdx.input.setInputProcessor(cameraInputController);
    }

    @Override
    public void render(float delta) {
        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // TODO
        cameraInputController.update();

        // Like spriteBatch, just with models!  pass in the box Instance and the environment
        modelBatch.begin(camera);
        modelBatch.render(modelInstance);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
