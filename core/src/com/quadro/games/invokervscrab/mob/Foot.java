package com.quadro.games.invokervscrab.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Foot extends Part {

    private boolean mRight = false;

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

    public Foot(float x, float y, float scale, boolean right) {
        super(x, y, scale);
        mRight = right;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        float[] innerDelta = mInnerDelta[ (int)(Math.random() * mInnerDelta.length) ];

        sr.setColor(1, 0, 0, 1);
        float xf = mScale * (mRight ? 1 : -1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.polygon(new float[] {
                +10 * xf,
                +10 * mScale,

                +40 * xf + 5 * innerDelta[0] * xf,
                -40 * mScale - 5 * innerDelta[1] * mScale,

                +10 * xf + 10 * innerDelta[0] * xf,
                -80 * mScale - 10 * innerDelta[1] * mScale,

                +30 * xf + 5 * innerDelta[0] * xf,
                -40 * mScale - 5 * innerDelta[1] * mScale,

                -10 * xf,
                +10 * mScale,

                +10 * xf,
                +10 * mScale,
        });
        sr.end();
    }

}
