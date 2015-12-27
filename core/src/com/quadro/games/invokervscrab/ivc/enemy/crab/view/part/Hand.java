package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Hand extends AbstractPart {

    private float mAttackFrame = 0;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch);

        batch.setColor(Color.RED);

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

        float width = getWidth();

        Matrix4 m = batch.getTransformMatrix()
                .rotate(0, 0, 1, 30 * (width > 0 ? 1 : -1))
                .scale((width > 0 ? 1 : -1), getHeight() / width, 1);
        batch.setTransformMatrix(m);

        float[] vertex = arc(0, 0, Math.abs(width), angel, 180);
        batch.draw(mTexture, vertex, 0, vertex.length);
        vertex = arc(0, 0, Math.abs(width), 180, 180);
        batch.draw(mTexture, vertex, 0, vertex.length);

//        if (mRight) {
//            sr.rotate(0, 0, 1, 30);
//            sr.scale(1.3f, 1, 1);
//            sr.arc(-10, 0, 50, angel, 180);
//            sr.arc(0, 0, 70, 180, 180);
//            sr.scale(1 / 1.3f, 1, 1);
//            sr.rotate(0, 0, 1, -30);
//        } else {
//            sr.rotate(0, 0, 1, -30);
//            sr.scale(1.3f, 1, 1);
//            sr.arc(0, 0, 50, -angel, 180);
//            sr.arc(0, 0, 60, 180, 180);
//            sr.scale(1 / 1.3f, 1, 1);
//            sr.rotate(0, 0, 1, 30);
//        }
//        sr.end();




        resetTransform(batch);
    }

    public void setAttackFrame(float frame) {
        mAttackFrame = frame;
    }

}
