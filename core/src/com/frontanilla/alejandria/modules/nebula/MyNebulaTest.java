package com.frontanilla.alejandria.modules.nebula;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alyrow.nebulagenerator.lib.NebulaGenerator;
import com.github.alyrow.nebulagenerator.lib.NebulasGenerator;

import static com.github.alyrow.nebulagenerator.lib.NebulaGenerator.NoiseType.DEFAULT;
import static com.github.alyrow.nebulagenerator.lib.NebulaGenerator.NoiseType.FOAM_NOISE;
import static com.github.alyrow.nebulagenerator.lib.NebulaGenerator.NoiseType.WHITE_NOISE;

public class MyNebulaTest extends Game {

    private SpriteBatch spriteBatch;
    private Sprite nebula, overlay, overlay2;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Texture blackStarfield = new Texture(Gdx.files.internal("images/starfield2.png"));
        int width = blackStarfield.getWidth();
        int height = blackStarfield.getHeight();
        NebulasGenerator nebulasGenerator = new NebulasGenerator(width, height);
        // A generator
        NebulaGenerator generator0 = new NebulaGenerator(
                width,          // Width
                height,         // Height
                DEFAULT,        // Noise type
                12955341,       // Seed
                9,              // Octave
                Color.BLUE,     // Color
                15f,            // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        //nebulasGenerator.addGenerator(generator0);
        // Another generator
        NebulaGenerator generator1 = new NebulaGenerator(
                width,          // Width
                height,         // Height
                FOAM_NOISE,     // Noise type
                78852342,       // Seed
                13,             // Octave
                Color.PURPLE,   // Color
                3f,             // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        //nebulasGenerator.addGenerator(generator1);
        // Another generator
        Color color = Color.RED.cpy().lerp(Color.PURPLE, 0.9f);
        NebulaGenerator generator2 = new NebulaGenerator(
                width,          // Width
                height,         // Height
                WHITE_NOISE,    // Noise type
                7242341,        // Seed
                9,              // Octave
                color,          // Color
                1.5f,           // Alpha
                new Vector2(),  // Offset max
                new Vector2()   // Offset
        );
        nebulasGenerator.addGenerator(generator2);
        // Starfield pixmap
        Pixmap pixmap = new Pixmap(Gdx.files.internal("images/starfield2.png"));
        // Generate
        Texture nebulaTexture = nebulasGenerator.generateTextureNebulasBlendedWithAPixmapGammaCorrection(pixmap);
        nebulaTexture = nebulasGenerator.generateTextureNebulas();
        nebula = new Sprite(nebulaTexture);
        nebula.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Overlay
        overlay = new Sprite(new Texture(Gdx.files.internal("images/gradientOverlay.png")));
        overlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay.setColor(Color.NAVY.cpy().lerp(Color.CLEAR, 0.75f));
        overlay.flip(false, true);
        // Overlay 2
        overlay2 = new Sprite(new Texture(Gdx.files.internal("images/overlay2.png")));
        overlay2.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        overlay2.setColor(Color.RED.cpy().lerp(Color.CLEAR, 0.7f));
    }

    @Override
    public void render() {
        spriteBatch.begin();
        nebula.draw(spriteBatch);
        //overlay.draw(spriteBatch);
        //overlay2.draw(spriteBatch);
        spriteBatch.end();
    }
}