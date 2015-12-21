package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Eye extends AbstractPart {

    private int mRandomIndex = 0;

    public Eye () {
        mSizeX = 40;
        mSizeY = 40;
    }

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

    public void draw(ShapeRenderer sr) {
        float radiusOuter = mSizeX;
        float radiusInner = mSizeX / 2;

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 0, 0, 1);

        float x = mRandom[0] * 10 - 5;
        float y = mRandom[1] * 10 - 5;
        sr.translate(x, y, 0);

        sr.circle(
                0,
                0,
                radiusOuter
        );

        float[] innerDelta = mInnerDelta[ mRandomIndex ];

        sr.setColor(1, 1, 0.5843f, 1);
        sr.circle(
                0,
                0,
                (radiusOuter - 2)
        );

        sr.setColor(0, 0, 0, 1);
        sr.circle(
                innerDelta[0] * radiusInner,
                innerDelta[1] * radiusInner,
                radiusInner
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
