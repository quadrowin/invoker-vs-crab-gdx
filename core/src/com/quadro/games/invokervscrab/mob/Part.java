package com.quadro.games.invokervscrab.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
abstract public class Part {

    private float[] mPosition;

    protected float mScale = 1;

    public Part(float x, float y, float scale) {
        mPosition = new float[] {x, y};
        mScale = scale;
    }

    abstract public void draw(ShapeRenderer sr);

    public float getX() {
        return mPosition[0];
    }

    public float getY() {
        return mPosition[1];
    }

}
