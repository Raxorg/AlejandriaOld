package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bar {
    private Texture texture;
    private float x, y, w, h;
    private int ammo;

    public Bar(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        texture = new Texture("bar.png");
        ammo = 10;
    }

    public void render(SpriteBatch batch) {
        float newH = (h / 10) * ammo;
        batch.draw(texture, x, y, w, newH);
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
