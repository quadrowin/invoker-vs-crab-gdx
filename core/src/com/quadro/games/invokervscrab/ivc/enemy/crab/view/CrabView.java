package com.quadro.games.invokervscrab.ivc.enemy.crab.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.quadro.games.invokervscrab.ivc.enemy.crab.Crab;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation.CrabAnimation;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation.Dummy;
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

    private CrabAnimation mAnimation;

    private Crab mCrab;

    private Body mBody;

    private AbstractPart[] mParts;

    private List<float[]> mInitialPositions = new ArrayList<float[]>();

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
            this.addActor(mParts[i]);
            mInitialPositions.add(new float[]{mParts[i].getX(), mParts[i].getY(), mParts[i].getRotation()});
        }

        eyeLeft.setTexture(skin.get("crab-eye-white", Texture.class));
        eyeLeft.setTexturePupil(skin.get("crab-eye-pupil", Texture.class));

        eyeRight.setTexture(skin.get("crab-eye-white", Texture.class));
        eyeRight.setTexturePupil(skin.get("crab-eye-pupil", Texture.class));
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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (mQuestionTexture != null) {
            batch.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, parentAlpha);
            batch.draw(
                    mQuestionTexture,
                    getX() + mBody.getX() + (mBody.getWidth() / 2 - 40) * getScaleX(),
                    getY() + mBody.getY() + (mBody.getHeight() / 2 - 30) * getScaleY(),
                    80 * getScaleX(),
                    60 * getScaleY()
            );
        }
    }

    public Crab getCrab() {
        return mCrab;
    }

    public AbstractPart[] getParts() {
        return mParts;
    }

    public void randomize() {
        for (int i = 0; i < mParts.length; i++) {
            mParts[i].randomize();
        }
    }

    public void resetPartPositions() {
        setScale(1);
        for (int i = 0; i < mParts.length; i++) {
            mParts[i].setPosition(mInitialPositions.get(i)[0], mInitialPositions.get(i)[1]);
            mParts[i].setRotation(mInitialPositions.get(i)[2]);
        }
    }

    public void setAnimation(CrabAnimation animation) {
        if (mAnimation != null) {
            mAnimation.setCrab(null);
        }
        animation.setCrab(this);
        animation.addActor(this);
    }

    public <T extends CrabAnimation> CrabAnimation setNewAnimation(Class<T> animationClass, Group parent) {
        CrabAnimation animation;
        try {
            animation = animationClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            animation = new Dummy();
        }
        setAnimation(animation);
        parent.addActor(animation);
        return animation;
    }

    public <T extends CrabAnimation> CrabAnimation setNewAnimation(Class<T> animationClass, Stage stage) {
        return setNewAnimation(animationClass, stage.getRoot());
    }

    public void setQuestion(Drawable question) {
        mQuestionTexture = question instanceof SpriteDrawable
                ? ((SpriteDrawable)question).getSprite().getTexture()
                : null;
    }

}
