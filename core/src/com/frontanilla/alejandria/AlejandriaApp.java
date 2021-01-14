package com.frontanilla.alejandria;

import com.badlogic.gdx.Game;
import com.frontanilla.alejandria.modules.examples3d.Base3DModule;
import com.frontanilla.alejandria.screens.ModuleScreen;

public class AlejandriaApp extends Game {

    @Override
    public void create() {
        setScreen(new ModuleScreen(new Base3DModule()));
    }
}
