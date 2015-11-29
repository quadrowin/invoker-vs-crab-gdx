package com.quadro.games.invokervscrab.ivc.mob;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Crab {

    private Part[] parts;

    private Body mBody;

    private Drawable mQuestion;

    private float mLeft = 0;

    private float mBottom = 20;

    public Crab() {

        mBody = new Body(0, 0, 1.5f);

        parts = new Part[] {
                new Eye(-25, 50, 0.5f),
                new Eye(25, 50, 0.4f),
                mBody,

                new Hand(-75, 37, 0.40f, false),
                new Hand(+80, 40, 0.45f, true),

                new Foot(-05, -25, 0.50f, false),
                new Foot(-30, -35, 0.60f, false),
                new Foot(-50, -30, 0.75f, false),

                new Foot(+05, -25, 0.5f, true),
                new Foot(+30, -35, 0.60f, true),
                new Foot(+50, -30, 0.75f, true),
        };
    }

    public void draw(Batch batch, Matrix4 m) {
        ShapeRenderer sr = new ShapeRenderer();
        sr.setProjectionMatrix(m);
        sr.translate(mLeft, mBottom, 0);
        for (int i = 0; i < parts.length; i++) {
            com.quadro.games.invokervscrab.ivc.mob.Part p = parts[i];
            sr.translate(p.getX(), p.getY(), 0);
            sr.scale(p.getScale(), p.getScale(), 1);
            p.draw(sr);
            sr.scale(1 / p.getScale(), 1 / p.getScale(), 1);
            sr.translate(-p.getX(), -p.getY(), 0);
        }

        if (mQuestion != null) {
            batch.begin();
            batch.setProjectionMatrix(m);
            float w = mBody.getWidth();
            float h = mBody.getHeight();
            mQuestion.draw(
                    batch,
                    mLeft + mBody.getLeft() + w * 0.1f,
                    mBottom + mBody.getBottom() + w * 0.1f,
                    w - w * 0.2f,
                    h - w * 0.2f
            );
            batch.end();
        }
    }

    public void randomize() {
        for (int i = 0; i < parts.length; i++) {
            parts[i].randomize();
        }
    }

    public void setQuestion(Drawable question) {
        mQuestion = question;
    }

}
