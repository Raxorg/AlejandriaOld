package com.frontanilla.alejandria.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    private static final String TAG = Assets.class.getName();
    @SuppressWarnings("LibGDXStaticResource")
    public static final Assets instance = new Assets();

    // Display
    public DisplayAssets displayAssets;
    // Game
    public GameAssets gameAssets;
    // Effects
    public EffectAssets effectAssets;

    private AssetManager assetManager;

    private Assets() {
    }

    // Load the loading screen assets
    public void loadDisplayAssets(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        assetManager.load("loading.png", Texture.class);
        assetManager.load("buttons.png", Texture.class);
        assetManager.load("fonts/ol.fnt", BitmapFont.class);
        assetManager.finishLoading();

        Texture loading = assetManager.get("loading.png");
        Texture buttons = assetManager.get("buttons.png");
        BitmapFont font = assetManager.get("fonts/ol.fnt");

        displayAssets = new DisplayAssets(loading, buttons, font);
        System.out.println("Display assets created");
    }

    public void loadAllAssets(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        assetManager.load("game.png", Texture.class);
        assetManager.load("effects.png", Texture.class);
    }

    public void getAllAssets() {
        // Game
        Texture game = assetManager.get("game.png", Texture.class);
        gameAssets = new GameAssets(game);
        System.out.println("Game assets created");
        // Effects
        Texture effects = assetManager.get("effects.png", Texture.class);
        effectAssets = new EffectAssets(effects);
        System.out.println("Effect assets created");
    }

    public class DisplayAssets {

        public final TextureRegion pixel, loading, buttonFrame1, buttonFrame3, buttonFrame5;
        public final BitmapFont timesSquare;

        public DisplayAssets(Texture loadingTexture, Texture buttons, BitmapFont timesSquareFont) {
            pixel = new TextureRegion(buttons, 100, 100, 1, 1);
            loading = new TextureRegion(loadingTexture, 0, 0, 800, 800);
            buttonFrame1 = new TextureRegion(buttons, 900, 0, 300, 300);
            buttonFrame3 = new TextureRegion(buttons, 0, 0, 900, 300);
            buttonFrame5 = new TextureRegion(buttons, 0, 300, 1500, 300);
            timesSquare = timesSquareFont;
        }
    }

    public class GameAssets {

        public final TextureRegion tank, wall3, wall2, wall1, base3, base2, base1;
        public final TextureRegion frame;

        public GameAssets(Texture game) {
            wall3 = new TextureRegion(game, 0, 0, 15, 15);
            wall2 = new TextureRegion(game, 15, 0, 15, 15);
            wall1 = new TextureRegion(game, 30, 0, 15, 15);
            base3 = new TextureRegion(game, 0, 15, 15, 15);
            base2 = new TextureRegion(game, 15, 15, 15, 15);
            base1 = new TextureRegion(game, 30, 15, 15, 15);
            tank = new TextureRegion(game, 0, 30, 15, 15);
            frame = new TextureRegion(game, 15, 30, 15, 15);
        }
    }

    public class EffectAssets {

        public EffectAssets(Texture effects) {

        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

}