package com.frontanilla.alejandria.modules.text;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FitTextExample extends Game {

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private String text;
    private float rectangleWidth = 200f;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        font = new BitmapFont();
        text = "Text that fits";

        GlyphLayout layout = new GlyphLayout(font, text);
        float scaleFactor = rectangleWidth / layout.width;
        font.getData().setScale(scaleFactor, scaleFactor);
    }

    @Override
    public void render() {
        spriteBatch.begin();
        font.draw(spriteBatch, text, 300f, 200f);
        spriteBatch.end();
        shapeRenderer.begin();
        shapeRenderer.rect(300f, 100f, rectangleWidth, 100f);
        shapeRenderer.end();
    }
}