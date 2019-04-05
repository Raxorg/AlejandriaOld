package com.frontanilla.alejandria.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frontanilla.alejandria.AlejandriaApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1500;
        config.height = config.width / 2;
        new LwjglApplication(new AlejandriaApp(), config);
    }
}
