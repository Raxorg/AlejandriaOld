package com.frontanilla.alejandria.modules.windowsize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class WindowChanger {

    // Works only in desktop
    public void changeWindowSize() {
        Gdx.graphics.setWindowedMode(MathUtils.random(500, 1000), 800);
    }
}