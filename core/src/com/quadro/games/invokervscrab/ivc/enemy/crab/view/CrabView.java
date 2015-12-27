package com.quadro.games.invokervscrab.ivc.enemy.crab.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.quadro.games.invokervscrab.ivc.enemy.crab.Crab;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Body;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Eye;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Foot;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Hand;

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

    private WidgetGroup mPartsActor = new WidgetGroup();

    private Texture mQuestionTexture;

    public CrabView(Crab crab, Skin skin) {
        setWidth(200);
        setHeight(200);
        mCrab = crab;

        mBody = createPart(Body.class, 0, 0, 150, 100);

        Eye eyeLeft = createPart(Eye.class, 20, 95, 50);
        Eye eyeRight = createPart(Eye.class, 75, 95, 40);

        mParts = new AbstractPart[] {
                eyeLeft,
                eyeRight,
                mBody,

                createPart(Hand.class, +00,  100, -30, -20),
                createPart(Hand.class, +150, 100, +40, +30),

                // left foots
                createPart(Foot.class, +30, 40, -40, 80),
                createPart(Foot.class, +50, 35, -40, 80),
                createPart(Foot.class, +70, 30, -30, 70),

                // right foots
                createPart(Foot.class, +80,  30,  30, 70),
                createPart(Foot.class, +100, 35,  40, 80),
                createPart(Foot.class, +120, 40,  40, 80),
        };

        for (int i = 0; i < mParts.length; i++) {
            mParts[i].setTexture(skin.get("crab-skin", Texture.class));
            mPartsActor.addActor(mParts[i]);
            mInitialPositions.add(new float[]{mParts[i].getX(), mParts[i].getY(), mParts[i].getRotation()});
        }

        eyeLeft.setTexture(skin.get("crab-eye-white", Texture.class));
        eyeLeft.setTexturePupil(skin.get("crab-eye-pupil", Texture.class));

        eyeRight.setTexture(skin.get("crab-eye-white", Texture.class));
        eyeRight.setTexturePupil(skin.get("crab-eye-pupil", Texture.class));

        this.addActor(mPartsActor);
    }

    private <T extends AbstractPart> T createPart(Class<T> partClass, float x, float y, float width) {
        return createPart(partClass, x, y, width, Math.abs(width));
    }

    private <T extends AbstractPart> T createPart(Class<T> partClass, float x, float y, float width, float height) {
        T part;
        try {
            part = partClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        part.setPosition(x, y);
        part.setSize(width, height);

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

        if (mQuestionTexture != null && false) {
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

    public WidgetGroup getPartsActor() {
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
