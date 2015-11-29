package com.quadro.games.invokervscrab.ivc.mob;

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
        sr.begin(ShapeRenderer.ShapeType.Filled);
        if (mRight) {
//            RectF sr = new RectF(r);
//            sr.right -= 20 * mScale;
//            cnv.rotate(-30, mPosition[0], mPosition[1]);
//            cnv.drawArc(sr, mRandom[0] * 28, 180, false, paint);
//            cnv.drawArc(r, 180, 180, false, paint);
//            sr.rotate(30, mPosition[0], mPosition[1]);
            sr.rotate(0, 0, 1, 30);
            sr.scale(1.3f, 1, 1);
            sr.arc(-10, 0, 50, mRandom[0] * 28, 180);
            sr.arc(0, 0, 70, 180, 180);
            sr.scale(1 / 1.3f, 1, 1);
            sr.rotate(0, 0, 1, -30);
        } else {
//            RectF sr = new RectF(r);
//            sr.left += 20 * mScale;
//            cnv.rotate(30, mPosition[0], mPosition[1]);
//            cnv.drawArc(sr, -30, 180 + mRandom[0] * 28, false, paint);
//            cnv.drawArc(r, 180, 180, false, paint);
//            cnv.rotate(-30, mPosition[0], mPosition[1]);


            sr.rotate(0, 0, 1, -30);
            sr.scale(1.3f, 1, 1);
            sr.arc(0, 0, 50, -mRandom[0] * 28, 180);
            sr.arc(0, 0, 60, 180, 180);
            sr.scale(1 / 1.3f, 1, 1);
            sr.rotate(0, 0, 1, 30);
        }
        sr.end();
    }

}
