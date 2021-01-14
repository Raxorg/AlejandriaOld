package com.frontanilla.alejandria.modules.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class AnimationTest extends Game {

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Animation<TextureRegion> fireAttackAnimation;
    private float boundsY, boundsRadius;
    private Animation<Float> boundsAnimation;
    private float time;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        Texture fireballFrames = new Texture(Gdx.files.internal("animation/fireballFrames.png"));
        TextureRegion[][] rawAnimationFrames = TextureRegion.split(fireballFrames, 1150, 460);
        TextureRegion[] animationFrames = new TextureRegion[rawAnimationFrames.length * rawAnimationFrames[0].length];
        int i = 0;
        for (TextureRegion[] rawAnimationFrame : rawAnimationFrames) {
            for (int row = 0; row < rawAnimationFrame.length; row++, i++) {
                animationFrames[i] = rawAnimationFrame[row];
            }
        }
        fireAttackAnimation = new Animation<>(0.05f, animationFrames);
        fireAttackAnimation.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion firstFrame = fireAttackAnimation.getKeyFrame(0);
        boundsY = firstFrame.getRegionHeight() / 4f;
        boundsRadius = firstFrame.getRegionHeight() / 6f;

        Float[] floats = new Float[fireAttackAnimation.getKeyFrames().length];
        for (i = 0; i < floats.length; i++) {
            floats[i] = MathUtils.map(0, floats.length, boundsRadius, (1150f / 2f) - boundsRadius, i);
        }
        boundsAnimation = new Animation<>(0.025f, floats);
        boundsAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.6f, 0.7f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glLineWidth(10);
        time += Gdx.graphics.getDeltaTime();
        spriteBatch.begin();
        spriteBatch.draw(fireAttackAnimation.getKeyFrame(time), 0f, 0f, 1150 / 2f, 460 / 2f);
        spriteBatch.end();
        shapeRenderer.begin();
        shapeRenderer.circle(boundsAnimation.getKeyFrame(time), boundsY, boundsRadius);
        shapeRenderer.end();
    }
}