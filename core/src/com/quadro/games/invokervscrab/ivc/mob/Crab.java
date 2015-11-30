package com.quadro.games.invokervscrab.ivc.mob;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

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
        Matrix4 startPartTr = sr.getTransformMatrix().cpy();
//        Matrix4 startPartPr = sr.getProjectionMatrix().cpy();
        for (int i = 0; i < parts.length; i++) {
            Part p = parts[i];
            sr.translate(p.getX(), p.getY(), 0);
            sr.scale(p.getScale(), p.getScale(), 1);
            p.draw(sr);
            sr.setTransformMatrix(startPartTr);
//            sr.setProjectionMatrix(startPartPr);
        }

        if (mQuestion != null) {
            Sprite spr = ((SpriteDrawable)mQuestion).getSprite();
            batch.begin();
            batch.setProjectionMatrix(m);
            float w = mBody.getWidth();
            float h = mBody.getHeight();
            batch.draw(
                    spr.getTexture(),
                    mLeft + mBody.getLeft() + w * 0.15f,
                    mBottom + mBody.getBottom() + w * 0.1f,
                    w * 0.7f,
                    h * 0.6f
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
