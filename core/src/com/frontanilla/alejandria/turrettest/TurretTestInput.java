package com.frontanilla.alejandria.turrettest;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class TurretTestInput extends InputAdapter {

    private Turret t;

    public TurretTestInput(Turret t) {
        this.t = t;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.R) {
            t.recharge();
        }
        return true;
    }
}
