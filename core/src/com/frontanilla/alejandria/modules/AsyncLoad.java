package com.frontanilla.alejandria.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;

public class AsyncLoad {

    private static void loadTexture(final String path) {

        final long startTime = System.currentTimeMillis();

        FileHandle file = Gdx.files.internal(path);
        Pixmap pixmap = new Pixmap(file);

        final FileTextureData textureData = new FileTextureData(file, pixmap, Pixmap.Format.RGBA8888, false);

        Gdx.app.postRunnable(new Runnable() {

            @Override
            public void run() {

                Texture texture = new Texture(textureData);
                long endTime = System.currentTimeMillis();

            }
        });

    }
}
