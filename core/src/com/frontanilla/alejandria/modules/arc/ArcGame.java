package com.frontanilla.alejandria.modules.arc;

import com.badlogic.gdx.Game;

public class ArcGame extends Game {

    private Arc arc;

    @Override
    public void create() {
        arc = new Arc();
    }

    @Override
    public void render() {
        arc.test();
    }
}
