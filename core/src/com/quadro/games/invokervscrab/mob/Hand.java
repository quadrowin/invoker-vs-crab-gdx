package com.quadro.games.invokervscrab.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Hand extends Part {

    private boolean mRight = false;

    public Hand(float x, float y, float scale, boolean right) {
        super(x, y, scale);
        mRight = right;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 0, 0, 1);
//        RectF r = new RectF(
//                mPosition[0] - 100 * mScale,
//                mPosition[1] - 50 * mScale,
//                mPosition[0] + 100 * mScale,
//                mPosition[1] + 50 * mScale
//        );
//        if (mRight) {
//            RectF sr = new RectF(r);
//            sr.right -= 20 * mScale;
//            cnv.rotate(-30, mPosition[0], mPosition[1]);
//            cnv.drawArc(sr, (float) (Math.random() * 28), 180, false, paint);
//            cnv.drawArc(r, 180, 180, false, paint);
//            sr.rotate(30, mPosition[0], mPosition[1]);
//        } else {
//            RectF sr = new RectF(r);
//            sr.left += 20 * mScale;
//            cnv.rotate(30, mPosition[0], mPosition[1]);
//            cnv.drawArc(sr, -30, 180 + (float) (Math.random() * 28), false, paint);
//            cnv.drawArc(r, 180, 180, false, paint);
//            cnv.rotate(-30, mPosition[0], mPosition[1]);
//        }
    }

}
