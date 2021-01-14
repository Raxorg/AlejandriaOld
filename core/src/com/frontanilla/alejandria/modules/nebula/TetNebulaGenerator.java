package com.frontanilla.alejandria.modules.nebula;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.alyrow.nebulagenerator.lib.NebulaGenerator;

import squidpony.squidmath.FastNoise;

/**
 * Created by Tommy Ettinger on 9/12/2020.
 */
public class TetNebulaGenerator extends NebulaGenerator {
    long seed = 10000;
    int width, height;
    Color color;
    Vector2 offset;
    Vector2 offset_max;
    int octave = 11;
    float alpha = 1;
    FastNoise generator;

    public TetNebulaGenerator(int width, int height, FastNoise gen, long seed, int octave, Color color, float alpha,
                              Vector2 offset_max, Vector2 offset) {
        super(width, height, NoiseType.DEFAULT, seed, octave, color, alpha, offset_max, offset);
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.generator = gen;
        this.generator.setFractalLacunarity(0.5f);
        this.generator.setFractalGain(2.0f);
        this.generator.setFractalOctaves(this.octave = octave);
        this.generator.setSeed((int) (seed ^ seed >>> 32));
        this.color = color;
        this.alpha = alpha;
        this.offset_max = offset_max;
        this.offset = offset;
    }

    @Override
    public Pixmap generatePixmapNebula(Pixmap pix) {
        //pix = new Pixmap(this.width, this.height, Pixmap.Format.RGBA8888);

        Color cln = new Color(1, 1, 1, 0).sub(color);

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                float n = generator.getConfiguredNoise((x + this.offset.x), (y + this.offset.y));
                float div = 0.5f;
                float r = ((/*pc.r+*/cln.r + n) * div);
                float g = ((/*pc.g+*/cln.g + n) * div);
                float b = ((/*pc.b+*/cln.b + n) * div);

                pix.setBlending(Pixmap.Blending.SourceOver);
                pix.setColor(
                        MathUtils.clamp(r - cln.r + 0.2f, 0f, 1f),
                        MathUtils.clamp(g - cln.g + 0.2f, 0f, 1f),
                        MathUtils.clamp(b - cln.b + 0.2f, 0f, 1f),
                        MathUtils.clamp((n * n * n) * alpha, 0f, 1f));
//				pix.setColor(new Color(r,g,b,0f).sub(cln).add(0.2f, 0.2f, 0.2f, (n * n * n) * alpha));
                pix.drawPixel(x, y);
                pix.setBlending(Pixmap.Blending.None);
            }
        }
        return pix;
    }

}
