package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    private Texture texture;
    private float speed;
    private Rectangle bounds;
    private int damage;
    private Enemy enemy;
    private boolean hasExploded;

    public Bullet(float x, float y, float w, float h, Turret turret) {
        bounds = new Rectangle(x, y, w, h);
        texture = new Texture("bullet.png");
        enemy = turret.getEnemy();
        speed = 50f;
        damage = 1;
        hasExploded = false;
    }

    public void render(SpriteBatch batch) {
        if (hasExploded) {
            return;
        }
        batch.draw(
                texture,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);
        move(Gdx.graphics.getDeltaTime());
    }

    public void move(float delta) {
        float degrees = Utils.getAngle(
                bounds.x,
                enemy.getX() + enemy.getW() / 2,
                bounds.y,
                enemy.getY() + enemy.getH() / 2);
        bounds.x += MathUtils.cosDeg(degrees) * speed * delta;
        bounds.y += MathUtils.sinDeg(degrees) * speed * delta;
        if (bounds.overlaps(enemy.getBounds())) {
            enemy.setHealth(enemy.getHealth() - damage);
            hasExploded = true;
        }
    }
}
