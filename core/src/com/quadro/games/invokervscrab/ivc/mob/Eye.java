package com.quadro.games.invokervscrab.ivc.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Eye extends Part {

    private float mRadiusOuter = 40;

    private float mRadiusInner = 20;

    private int mRandomIndex = 0;

    final private static float[][] mInnerDelta = new float[][] {
            {-0.7f, -0.7f},
            {-1, 0},
            {-0.7f, 0.7f},
            {0, -1},
            {0, 0},
            {0, 1},
            {0.7f, -0.7f},
            {1, 0},
            {0.7f, 0.7f},
    };

    public Eye(float x, float y, float scale) {
        super(x, y, scale);
    }

    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 0, 0, 1);

        float x = mRandom[0] * 10 - 5;
        float y = mRandom[1] * 10 - 5;
        sr.translate(x, y, 0);

        sr.circle(
                0,
                0,
                mRadiusOuter
        );

        float[] innerDelta = mInnerDelta[ mRandomIndex ];

        sr.setColor(1, 1, 0.5843f, 1);
        sr.circle(
                0,
                0,
                (mRadiusOuter - 2)
        );

        sr.setColor(0, 0, 0, 1);
        sr.circle(
                innerDelta[0] * mRadiusInner,
                innerDelta[1] * mRadiusInner,
                mRadiusInner
        );

        sr.translate(-x, -y, 0);
        sr.end();
    }

    @Override
    public void randomize() {
        super.randomize();
        mRandomIndex = (int)(Math.random() * mInnerDelta.length);
    }

}
