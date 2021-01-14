package com.frontanilla.alejandria.modules.shaders.shadertut;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderLesson1 implements ApplicationListener {

    //Minor differences:
    //LibGDX Position attribute is a vec4
    //u_projView is called u_projTrans
    //we need to set ShaderProgram.pedantic to false
    //LibGDX uses lower-left as origin (0, 0)

    final String VERT =
            "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
                    "uniform mat4 u_projTrans;\n" +
                    " \n" +
                    "void main() {\n" +
                    "	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
                    "}";

    final String FRAG =
            "void main() {\n" +
                    "	gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);\n" +
                    "}";

    Texture tex;
    SpriteBatch batch;
    OrthographicCamera cam;
    ShaderProgram shader;

    @Override
    public void create() {
        //the texture does not matter since we will ignore it anyways
        tex = new Texture(256, 256, Pixmap.Format.RGBA8888);

        //important since we aren't using some uniforms and attributes that SpriteBatch expects
        ShaderProgram.pedantic = false;

        shader = new ShaderProgram(VERT, FRAG);
        if (!shader.isCompiled()) {
            System.err.println(shader.getLog());
            System.exit(0);
        }

        if (shader.getLog().length() != 0)
            System.out.println(shader.getLog());

        batch = new SpriteBatch(1000, shader);
        batch.setShader(shader);

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false);
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, width, height);
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //notice that LibGDX coordinate system origin is lower-left
        batch.draw(tex, 10, 10);
        batch.draw(tex, 10, 320, 32, 32);

        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        shader.dispose();
        tex.dispose();
    }
}
