package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeightmapModule extends Module {

    SpriteBatch batch;
    Pixmap pixmap;
    Texture texture;

    @Override
    public void show() {
        pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        pixmap.setColor(Color.RED);
        pixmap.fillCircle(5, 5, 3);

        pixmap.setColor(new Color(0, 0, .5f, 1));
        pixmap.drawPixel(0, 0);
        pixmap.drawPixel(9, 0);
        pixmap.drawPixel(0, 9);
        pixmap.drawPixel(9, 9);

        texture = new Texture(pixmap);

        // TODO read each pixel to determine height of the terrain
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(
                texture,
                Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2
        );
        batch.end();
    }

    @Override
    public void dispose() {
        pixmap.dispose();
    }

}
