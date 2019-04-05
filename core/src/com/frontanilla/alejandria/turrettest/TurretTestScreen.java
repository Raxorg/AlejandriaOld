package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TurretTestScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Turret turret;
    private Enemy enemy;

    @Override
    public void show() {
        super.show();
        // Creamos el batch
        batch = new SpriteBatch();
        // Creamos al enemigo
        enemy = new Enemy(0, 0, 150, 150, this);
        // Creamos la torreta
        turret = new Turret(500, 250, 300, 300, enemy);
        // Creamos la clase que maneja el input
        TurretTestInput input = new TurretTestInput(turret);
        // Activamos el input
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        turret.render(batch);
        enemy.render(batch);
        batch.end();
    }

    public Turret getTurret() {
        return turret;
    }
}
