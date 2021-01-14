package com.frontanilla.alejandria.modules.textgradient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * This was taken from LibGDX forums: https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=23433
 */
public class FontGradient extends Game {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private BitmapFont menuItemFont;
    private int width, height;
    private FrameBuffer frameBuffer;
    private OrthographicCamera camera;
    private Texture fbTexture;
    private Sprite fbSprite;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        batch = new SpriteBatch();

        menuItemFont = new BitmapFont();
        menuItemFont.getData().setScale(10f);

        glyphLayout = new GlyphLayout();
        glyphLayout.setText(menuItemFont, "ONE PLAYER");

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        float strWidth = glyphLayout.width;
        float strHeight = glyphLayout.height;
        float strX = width / 2f - strWidth / 2;
        float strY = height / 2f + strHeight / 2;

        frameBuffer.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);

        //renderRectangle(strWidth, strHeight, strX, strY);
        renderText(strX, strY);

        Gdx.gl.glEnable(GL20.GL_BLEND);

        //Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_DST_ALPHA, GL20.GL_SRC_COLOR);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_COLOR, GL20.GL_ONE_MINUS_DST_ALPHA);

        //Gdx.gl.glBlendFunc(GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_DST_COLOR);
        //Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_COLOR);

        //Gdx.gl.glBlendFunc(GL20.GL_ZERO, GL20.GL_DST_COLOR);
        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_ZERO);

        //Gdx.gl.glBlendFunc(GL20.GL_ZERO, GL20.GL_ZERO);

        //batch.enableBlending();
        //batch.setBlendFunction(GL20.GL_ZERO, GL20.GL_ONE);
        //batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //renderText(strX, strY);
        renderRectangle(strWidth, strHeight, strX, strY);

        //batch.disableBlending();

        Gdx.gl.glDisable(GL20.GL_BLEND);
        frameBuffer.end();

        fbTexture = frameBuffer.getColorBufferTexture();
        fbSprite = new Sprite(fbTexture);
        fbSprite.flip(false, true);

        batch.begin();
        batch.draw(fbSprite, 0, 0);
        batch.end();
    }

    private void renderText(float strX, float strY) {
        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        menuItemFont.draw(batch, "ONE PLAYER", strX, strY);
        batch.end();
    }

    private void renderRectangle(float strWidth, float strHeight, float strX, float strY) {
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(strX - 10, strY + 10, strWidth + 20, -(strHeight + 20),
                Color.LIME, Color.LIME, Color.BLACK, Color.BLACK);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        fbTexture.dispose();
        fbSprite.getTexture().dispose();
    }
}
