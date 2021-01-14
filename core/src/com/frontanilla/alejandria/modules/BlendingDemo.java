package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BlendingDemo extends Game {

    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer fbo;
    private Batch batch;
    private boolean drawDirect;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        viewport = new ScreenViewport();
        int width = Gdx.graphics.getBackBufferWidth();
        int height = Gdx.graphics.getBackBufferHeight();
        fbo = new FrameBuffer(Format.RGBA8888, width, height, false);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) drawDirect = !drawDirect;

        if (drawDirect) {
            drawShapes();
        } else {
            fbo.bind();
            drawShapes();
            fbo.end();

            Texture texture = fbo.getColorBufferTexture();
            int width = Gdx.graphics.getBackBufferWidth();
            int height = Gdx.graphics.getBackBufferHeight();

            Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
            Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);

            viewport.apply(false);
            batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
            batch.begin();
            batch.draw(texture, 0, 0, texture.getWidth(), texture.getHeight(), 0, 0, 1, 1);
            batch.end();
        }
    }

    private void drawShapes() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeType.Filled);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ZERO);
        shapeRenderer.circle(0, 0, 100);
        shapeRenderer.flush();

        Gdx.gl.glBlendFuncSeparate(GL20.GL_ZERO, GL20.GL_ZERO, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);
        shapeRenderer.circle(100, 0, 64);
        shapeRenderer.flush();

        shapeRenderer.end();

        // restore state
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}