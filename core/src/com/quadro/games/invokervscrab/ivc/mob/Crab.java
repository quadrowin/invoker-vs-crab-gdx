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

    private ShapeRenderer mRenderer = new ShapeRenderer();

    private final Matrix4 mTransformMatrixBuffer = new Matrix4();

    private final Matrix4 mRenderTransformMatrix = new Matrix4();

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

    public void draw(Batch batch, Matrix4 projection) {
        mRenderer.setProjectionMatrix(projection);
        mRenderer.setTransformMatrix(mRenderTransformMatrix);
        mRenderer.translate(mLeft, mBottom, 0);
        mTransformMatrixBuffer.set(mRenderer.getTransformMatrix());
//        Matrix4 startPartPr = mRenderer.getProjectionMatrix().cpy();
        for (int i = 0; i < parts.length; i++) {
            Part p = parts[i];
            mRenderer.translate(p.getX(), p.getY(), 0);
            mRenderer.scale(p.getScale(), p.getScale(), 1);
            p.draw(mRenderer);
            mRenderer.setTransformMatrix(mTransformMatrixBuffer);
//            mRenderer.setProjectionMatrix(startPartPr);
        }

        if (mQuestion != null) {
            Sprite spr = ((SpriteDrawable)mQuestion).getSprite();
            batch.begin();
            batch.setProjectionMatrix(projection);
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
