package com.quadro.games.invokervscrab.ivc.enemy.crab.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.quadro.games.invokervscrab.ivc.enemy.crab.Crab;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Body;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Eye;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Foot;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Hand;
import com.quadro.games.invokervscrab.view.shape.ShapeGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quadrowin on 05.12.2015.
 */
public class CrabView extends WidgetGroup {

    private Crab mCrab;

    private Body mBody;

    private AbstractPart[] mParts;

    private List<float[]> mInitialPositions = new ArrayList<float[]>();

    private ShapeGroup mPartsActor = new ShapeGroup();

    private Texture mQuestionTexture;

    public CrabView(Crab crab) {
        mCrab = crab;

        mBody = createPart(Body.class, 0, 0, 1.5f);

        mParts = new AbstractPart[] {
                createPart(Eye.class, -25, 50, 0.5f),
                createPart(Eye.class, 25, 50, 0.4f),
                mBody,

                createPart(Hand.class, -75, 37, -0.40f),
                createPart(Hand.class, +80, 40, +0.45f),

                createPart(Foot.class, -05, -25, -0.50f),
                createPart(Foot.class, -30, -35, -0.60f),
                createPart(Foot.class, -50, -30, -0.75f),

                createPart(Foot.class, +05, -25, 0.5f),
                createPart(Foot.class, +30, -35, 0.60f),
                createPart(Foot.class, +50, -30, 0.75f),
        };

        for (int i = 0; i < mParts.length; i++) {
            mPartsActor.addShape(mParts[i]);
            mInitialPositions.add(new float[]{mParts[i].getX(), mParts[i].getY(), mParts[i].getRotation()});
        }

        this.addActor(mPartsActor);
    }

    private <T extends AbstractPart> T createPart(Class<T> partClass, float x, float y, float scaleX) {
        return createPart(partClass, x, y, scaleX, Math.abs(scaleX));
    }

    private <T extends AbstractPart> T createPart(Class<T> partClass, float x, float y, float scaleX, float scaleY) {
        T part;
        try {
            part = partClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        part.setPosition(x, y);
        part.setScale(scaleX, scaleY);

        return part;
    }


    @Override
    public void act (float delta) {
        super.act(delta);

        AbstractPart[] parts = mParts;
        float attackAnimationFrame = mCrab.getAttackFrame();
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            if (p instanceof Hand) {
                ((Hand)p).setAttackFrame( attackAnimationFrame );
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (mQuestionTexture != null) {
            Color c = batch.getColor();
            batch.setColor(c.r, c.g, c.b, parentAlpha);
            batch.draw(
                    mQuestionTexture,
                    getX() - 40,
                    getY() - 30,
                    80,
                    60
            );
        }
    }

    public Crab getCrab() {
        return mCrab;
    }

    public AbstractPart[] getParts() {
        return mParts;
    }

    public ShapeGroup getPartsActor() {
        return mPartsActor;
    }

    public void randomize() {
        for (int i = 0; i < mParts.length; i++) {
            mParts[i].randomize();
        }
    }

    public void setQuestion(Drawable question) {
        mQuestionTexture = question instanceof SpriteDrawable
                ? ((SpriteDrawable)question).getSprite().getTexture()
                : null;
    }

}
