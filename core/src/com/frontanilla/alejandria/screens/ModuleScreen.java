package com.frontanilla.alejandria.screens;


import com.frontanilla.alejandria.modules.Module;

public class ModuleScreen extends MyScreen {

    private Module module;

    @Override
    public void show() {
        module.show();
    }

    @Override
    public void render(float delta) {
        module.render(delta);
    }

    @Override
    public void dispose() {
        module.dispose();
    }
}
