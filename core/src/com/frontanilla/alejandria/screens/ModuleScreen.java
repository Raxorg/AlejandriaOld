package com.frontanilla.alejandria.screens;


import com.badlogic.gdx.Screen;
import com.frontanilla.alejandria.modules.Module;

public class ModuleScreen implements Screen {

    private Module module;

    public ModuleScreen(Module module) {
        this.module = module;
    }

    @Override
    public void show() {
        module.show();
    }

    @Override
    public void render(float delta) {
        module.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        module.resize(width, height);
    }

    @Override
    public void pause() {
        module.pause();
    }

    @Override
    public void resume() {
        module.resume();
    }

    @Override
    public void hide() {
        module.hide();
    }

    @Override
    public void dispose() {
        module.dispose();
    }
}
