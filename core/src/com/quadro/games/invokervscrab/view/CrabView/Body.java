package com.quadro.games.invokervscrab.view.CrabView;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Body extends Part {

    public Body(float x, float y, float scale) {
        super(x, y, scale);
        mSize = new float[] {100, 50};
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1, 0, 0, 1);
        sr.ellipse(
                -0.5f * mSize[0],
                -0.5f * mSize[1],
                mSize[0],
                mSize[1]
        );
        sr.end();
    }

}
