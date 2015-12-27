package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Foot extends AbstractPart {

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

    @Override
    public void draw (Batch batch, float parentAlpha) {
        float[] innerDelta = mInnerDelta[mRandomIndex];

        float width = getWidth();
        float height = getHeight();

        /*

00              04----1
                 \   / \
                  \ /   \
40                 3-----2

         */

        float[] polygon1 = new float[]{
                +00,
                +00,
                mWhiteColor, 0, 0,

                +20 * width / 40,
                +00,
                mWhiteColor, 0, 0,

                +40 * width / 40,
                -40 * height / 80,
                mWhiteColor, 0, 0,

                +30 * width / 40,
                -40 * height / 80,
                mWhiteColor, 0, 0,



        };

        /*

40                 04----2
                    |   /
                    | /
80                  3

        */

        float[] polygon2 = new float[]{
                +30 * width / 40,
                -40 * height / 80,
                mWhiteColor, 0, 0,

                +40 * width / 40,
                -40 * height / 80,
                mWhiteColor, 0, 0,

                +30 * width / 40,
                -80 * height / 80,
                mWhiteColor, 0, 0,

                +30 * width / 40,
                -40 * height / 80,
                mWhiteColor, 0, 0,
        };

        applyTransform(batch);

        batch.draw(mTexture, polygon1, 0, polygon1.length);
        batch.draw(mTexture, polygon2, 0, polygon2.length);

        resetTransform(batch);
    }

    @Override
    public void randomize() {
        super.randomize();
        mRandomIndex = (int)(Math.random() * mInnerDelta.length);
    }

}
