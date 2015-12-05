package com.quadro.games.invokervscrab.view.CrabPart;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Hand extends AbstractPart {

    private float mAttackFrame = 0;

    private boolean mRight = false;

    public Hand(float x, float y, float scale, boolean right) {
        super(x, y, scale);
        mRight = right;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 0, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        final float maxAngel = 30;
        final float borderTime1 = 0.7f;
        final float borderTime2 = 0.9f;

        float angel = 0;
        if (mAttackFrame < borderTime2) {
            angel = mAttackFrame < borderTime1
                    ? mAttackFrame / borderTime1
                    : 1 - (mAttackFrame - borderTime1) / (borderTime2 - borderTime1);
            angel *= maxAngel;
        }

        if (mRight) {
            sr.rotate(0, 0, 1, 30);
            sr.scale(1.3f, 1, 1);
            sr.arc(-10, 0, 50, angel, 180);
            sr.arc(0, 0, 70, 180, 180);
            sr.scale(1 / 1.3f, 1, 1);
            sr.rotate(0, 0, 1, -30);
        } else {
            sr.rotate(0, 0, 1, -30);
            sr.scale(1.3f, 1, 1);
            sr.arc(0, 0, 50, -angel, 180);
            sr.arc(0, 0, 60, 180, 180);
            sr.scale(1 / 1.3f, 1, 1);
            sr.rotate(0, 0, 1, 30);
        }
        sr.end();
    }

    public void setAttackFrame(float frame) {
        mAttackFrame = frame;
    }

}
