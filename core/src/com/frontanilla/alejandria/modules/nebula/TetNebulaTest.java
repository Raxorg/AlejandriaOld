package com.frontanilla.alejandria.modules.nebula;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.alyrow.nebulagenerator.lib.NebulasGenerator;

import squidpony.squidmath.FastNoise;

public class TetNebulaTest extends Game {

    private SpriteBatch spriteBatch;
    private Sprite nebula, overlay;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Texture blackStarfield = new Texture(Gdx.files.internal("images/starfield2.png"));
        int width = blackStarfield.getWidth();
        int height = blackStarfield.getHeight();
        NebulasGenerator nebulasGenerator = new NebulasGenerator(width, height);
        // Blue nebula generator
        FastNoise simplex = new FastNoise(12955341, 0.75f, FastNoise.SIMPLEX_FRACTAL);
        Color color = new Color().fromHsv(220f, 0.4f, 1f);
        TetNebulaGenerator greenGenerator = new TetNebulaGenerator(
                width,          // Width
                height,         // Height
                simplex,        // Noise type
                12955341,       // Seed
                12,             // Octave
                color,          // Color
                15f,            // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        nebulasGenerator.addGenerator(greenGenerator);
        // Purple nebula generator
        FastNoise foam = new FastNoise(78852342, 2.5f, FastNoise.FOAM_FRACTAL);
        color = new Color().fromHsv(290f, 0.3f, 1f);
        TetNebulaGenerator orangeGenerator = new TetNebulaGenerator(
                width,          // Width
                height,         // Height
                foam,           // Noise type
                78852342,       // Seed
                13,             // Octave
                color,          // Color
                6f,             // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        nebulasGenerator.addGenerator(orangeGenerator);
        // Red nebula generator
        FastNoise white = new FastNoise(7890, 0.5f, FastNoise.FOAM_FRACTAL);
        white.setFractalType(FastNoise.FBM);
        color = new Color().fromHsv(330f, 0.6f, 1f);
        TetNebulaGenerator redGenerator = new TetNebulaGenerator(
                width,          // Width
                height,         // Height
                white,          // Noise type
                78912345,       // Seed
                7,              // Octave
                color,          // Color
                2.5f,             // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        nebulasGenerator.addGenerator(redGenerator);
        // Starfield pixmap
        Pixmap pixmap = new Pixmap(Gdx.files.internal("images/starfield2.png"));
        // Generate
        Texture nebulaTexture = nebulasGenerator.generateTextureNebulasBlendedWithAPixmapGammaCorrection(pixmap);
        nebula = new Sprite(nebulaTexture);
        nebula.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Overlay
        overlay = new Sprite(new Texture(Gdx.files.internal("images/gradientOverlay.png")));
        overlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay.setColor(Color.NAVY.cpy().lerp(Color.CLEAR, 0.75f));
        overlay.flip(false, true);
    }

    @Override
    public void render() {
        spriteBatch.begin();
        nebula.draw(spriteBatch);
        overlay.draw(spriteBatch);
        spriteBatch.end();
    }
}
