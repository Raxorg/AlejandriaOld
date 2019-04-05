package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {

    private Texture texture;
    private float speed;
    private Rectangle bounds;
    private int health;
    private TurretTestScreen screen;
    private boolean isAlive;

    public Enemy(float x, float y, float w, float h, TurretTestScreen screen) {
        bounds = new Rectangle(x, y, w, h);
        texture = new Texture("enemy.png");
        speed = 50f;
        health = 10;
        this.screen = screen;
        isAlive = true;
    }

    public void render(SpriteBatch batch) {
        if(!isAlive) {
            return;
        }
        batch.draw(
                texture,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        move(Gdx.graphics.getDeltaTime());
        if (health <= 0) {
            isAlive = false;
        }
    }

    public void move(float delta) {
        Turret t = screen.getTurret();
        float degrees = Utils.getAngle(
                bounds.x,
                t.getX() + t.getW() / 2,
                bounds.y,
                t.getY() + t.getH() / 2);
        bounds.x += MathUtils.cosDeg(degrees) * speed * delta;
        bounds.y += MathUtils.sinDeg(degrees) * speed * delta;
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public float getW() {
        return bounds.width;
    }

    public float getH() {
        return bounds.height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
