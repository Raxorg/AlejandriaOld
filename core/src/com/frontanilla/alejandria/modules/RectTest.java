package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.glClearColor;

public class RectTest extends Game {

    private SpriteBatch batch;

    private TextureRegion rect;

    private Color dark = new Color(0x4e4e4e);
    private Color blue = new Color(0x96a2a5);
    private Color light = new Color(0xc9c9c9);

    public void create() {
        //create our font
        Texture fontTex = new Texture("badlogic.jpg");

        //in Photoshop, we included a small white box at the bottom right of our font sheet
        //we will use this to draw lines and rectangles within the same batch as our text
        rect = new TextureRegion(fontTex, fontTex.getWidth() - 2, fontTex.getHeight() - 2, 1, 1);


        glClearColor(0.5f, .5f, .5f, 1f);
        batch = new SpriteBatch();
    }

    private void drawRect(float x, float y, float width, float height, float thickness) {
        batch.draw(rect, x, y, width, thickness);
        batch.draw(rect, x, y, thickness, height);
        batch.draw(rect, x, y + height - thickness, width, thickness);
        batch.draw(rect, x + width - thickness, y, thickness, height);
    }

    private void drawLine(float x1, float y1, float x2, float y2, float thickness) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        batch.draw(rect, x1, y1, dist, thickness);
    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1f, 0f, 1f, 1f);

        batch.begin();

        float x = 25;
        float y = 50;
        float width = 250f;
        float height = 200f;
        batch.setColor(dark);
        batch.draw(rect, x, y, width, height);
        batch.setColor(blue);
        batch.draw(rect, x += 2, y += 2, width -= 4, height -= 4);
        batch.setColor(light);
        batch.draw(rect, x += 5, y += 5, width -= 10, height -= 10);
        batch.setColor(dark);

        float fw = 100f;
        float fx = x + width / 2 - fw / 2;
        float fy = y + height / 2 - 20f / 2;
        float base = fy + 20f + 1;

        //draw underline
        drawLine(fx, base, fx + fw, base, 1);

        //draw some other stuff
        batch.setColor(Color.WHITE);

        //test drawing a rectangle
        drawRect(180, y + 50, 50, 25, 3);

        //test drawing a rotated line
        batch.draw(rect, x + width + 30, 25f, 100f, 1f);

        //test drawing line from point A to point B
        batch.setColor(Color.PINK);
        drawLine(400, 300, Mouse.getX(), Display.getHeight() - Mouse.getY() - 1, 3);

        batch.end();
    }
}