package com.frontanilla.alejandria.modules.textgradient;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This was taken from Stack Overflow: https://stackoverflow.com/questions/60260658/blend-a-color-selectively
 */
public class FontGradient2 extends ApplicationAdapter {

    private SpriteBatch batch;
    private GradientFont font;
    private Color topColor;
    private Color bottomColor;

    public static class GradientFont extends BitmapFont {

        static void applyGradient(float[] vertices, int vertexCount, float color1, float color2, float color3, float color4) {
            for (int index = 0; index < vertexCount; index += 20) {
                vertices[index + SpriteBatch.C1] = color1; // C1: 2
                vertices[index + SpriteBatch.C2] = color2; // C2: 7
                vertices[index + SpriteBatch.C3] = color3; // C3: 12
                vertices[index + SpriteBatch.C4] = color4; // C4: 17
            }
        }

        GlyphLayout drawGradient(Batch batch, CharSequence str, float x, float y, Color topColor, Color bottomColor) {
            BitmapFontCache cache = getCache();
            float tc = topColor.toFloatBits();
            float bc = bottomColor.toFloatBits();
            cache.clear();
            GlyphLayout layout = cache.addText(str, x, y);
            for (int page = 0; page < cache.getFont().getRegions().size; page++) {
                applyGradient(cache.getVertices(page), cache.getVertexCount(page), bc, tc, tc, bc);
            }
            cache.draw(batch);
            return layout;
        }

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new GradientFont();
        font.getData().setScale(5f);

        topColor = Color.GREEN;
        bottomColor = Color.BLUE;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.drawGradient(batch, "Hello world", 300, 300, topColor, bottomColor);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}