package com.quadro.games.invokervscrab.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Body extends Part {

    private float mSizeX = 100;

    private float mSizeY = 50;

    public Body(float x, float y, float scale) {
        super(x, y, scale);
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1, 0, 0, 1);
        sr.ellipse(
                -0.5f * mSizeX * mScale,
                -0.5f * mSizeY * mScale,
                mSizeX * mScale,
                mSizeY * mScale
        );
        sr.end();
    }

}
