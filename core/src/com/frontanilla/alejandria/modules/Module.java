package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Module {

    protected SpriteBatch batch = new SpriteBatch();

    public abstract void show();

    public abstract void render(float delta);

    public abstract void dispose();
}
