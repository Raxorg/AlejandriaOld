package com.frontanilla.alejandria;

import com.badlogic.gdx.Game;
import com.frontanilla.alejandria.screens.SplashScreen;
import com.frontanilla.alejandria.turrettest.TurretTestScreen;

public class AlejandriaApp extends Game {



    @Override
    public void create() {
        setScreen(new TurretTestScreen());
    }
}
