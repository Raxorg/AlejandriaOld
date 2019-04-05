package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Turret {

    private Texture texture;
    private float x, y, w, h, time;
    private Bar bar;
    private ArrayList<Bullet> bullets;
    private Enemy enemy;

    public Turret(float x, float y, float w, float h, Enemy enemy) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        time = 0;
        texture = new Texture("turret.png");
        bar = new Bar(x - w / 4, y, w / 4, h);
        bullets = new ArrayList<>();
        this.enemy = enemy;
    }

    public void render(SpriteBatch batch) {
        // Calculamos el tiempo
        time += Gdx.graphics.getDeltaTime();
        if (time >= 1) {
            shoot();
            time -= 1;
        }
        // Dibujamos la torreta
        batch.draw(texture, x, y, w, h);
        // Dibujamos su barra de munición
        bar.render(batch);
        // Dibujamos sus balas
        for (Bullet b : bullets) {
            b.render(batch);
        }
    }

    public void shoot() {
        if (bar.getAmmo() > 0) {
            // Creamos una bala nueva
            Bullet b = new Bullet(
                    x + w / 2,
                    y + h / 2,
                    w / 10,
                    h / 10,
                    this);
            // Añadimos la bala a la lista de balas
            bullets.add(b);
            // Reducimos la munición de la torreta
            bar.setAmmo(bar.getAmmo() - 1);
        }
    }

    public void detectEnemy() {

    }

    public void recharge() {
        bar.setAmmo(10);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
