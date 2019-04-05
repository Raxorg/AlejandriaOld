package com.frontanilla.alejandria.screens;


import com.badlogic.gdx.assets.AssetManager;
import com.frontanilla.alejandria.utils.Assets;

public class SplashScreen extends MyScreen {

    private AssetManager assetManager;
    private boolean doneLoading;

    @Override
    public void show() {
        super.show();
        assetManager = new AssetManager();
        Assets.instance.loadAllAssets(assetManager);
        doneLoading = false;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

    }
}
