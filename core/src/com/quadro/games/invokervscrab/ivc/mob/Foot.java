package com.quadro.games.invokervscrab.ivc.mob;

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

    private int mRandomIndex = 0;

    public Foot(float x, float y, float scale, boolean right) {
        super(x, y, scale);
        mRight = right;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        float[] innerDelta = mInnerDelta[mRandomIndex];

        sr.setColor(1, 0, 0, 1);
        float xf = (mRight ? 1 : -1);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        float[] polygon = new float[]{
                +10 * xf,
                +10,

                +40 * xf + 5 * innerDelta[0] * xf,
                -40 - 5 * innerDelta[1],

                +30 * xf + 5 * innerDelta[0] * xf,
                -40 - 5 * innerDelta[1],

                +10 * xf + 10 * innerDelta[0] * xf,
                -80 - 10 * innerDelta[1],

                +30 * xf + 5 * innerDelta[0] * xf,
                -40 - 5 * innerDelta[1],

                +30 * xf + 5 * innerDelta[0] * xf,
                -40 - 5 * innerDelta[1],

                -10 * xf,
                +10,

                +10 * xf,
                +10,
        };

        for (int i = 0; i < polygon.length - 5; i += 2) {
            sr.triangle(
                    polygon[i + 0], polygon[i + 1],
                    polygon[i + 2], polygon[i + 3],
                    polygon[i + 4], polygon[i + 5]
            );
        }

        sr.end();
    }

    @Override
    public void randomize() {
        super.randomize();
        mRandomIndex = (int)(Math.random() * mInnerDelta.length);
    }

}
