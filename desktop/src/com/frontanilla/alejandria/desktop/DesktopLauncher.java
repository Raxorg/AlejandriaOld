package com.frontanilla.alejandria.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frontanilla.alejandria.modules.animation.AnimationTest;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 768;
        // new LwjglApplication(new AlejandriaApp(), config);
        // new LwjglApplication(new ShaderLesson4B(), config);
        // new LwjglApplication(new ShaderLesson6(), config);
        // new LwjglApplication(new DynamicTexturePixmap(), config);
        // new LwjglApplication(new BlendingDemo(), config);
        // new LwjglApplication(new RectTest(), config);
        // new LwjglApplication(new FontGradient(), config);
        // new LwjglApplication(new ArcGame(), config);
        // new LwjglApplication(new DecalTest(), config);
        // new LwjglApplication(new FontGradient(), config);
        // new LwjglApplication(new FontGradient(), config);
        // new LwjglApplication(new FitTextExample(), config);
        // new LwjglApplication(new MyNebulaTest(), config);
        // new LwjglApplication(new TetNebulaTest(), config);
        new LwjglApplication(new AnimationTest(), config);
    }
}
