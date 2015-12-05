package com.quadro.games.invokervscrab.view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.quadro.games.invokervscrab.ivc.mob.Crab;
import com.quadro.games.invokervscrab.view.CrabAnimation.AbstractAnimation;
import com.quadro.games.invokervscrab.view.CrabAnimation.Attack;
import com.quadro.games.invokervscrab.view.CrabAnimation.Death;
import com.quadro.games.invokervscrab.view.CrabPart.AbstractPart;
import com.quadro.games.invokervscrab.view.CrabPart.Body;
import com.quadro.games.invokervscrab.view.CrabPart.Eye;
import com.quadro.games.invokervscrab.view.CrabPart.Foot;
import com.quadro.games.invokervscrab.view.CrabPart.Hand;

/**
 * Created by Quadrowin on 05.12.2015.
 */
public class CrabView {

    private AbstractAnimation mAnimationAttack = new Attack();

    private AbstractAnimation mAnimationDeath = new Death();

    private Crab mCrab;

    private CrabContext mContext = new CrabContext();

    public CrabView(Crab crab) {
        mCrab = crab;

        mContext.shapeRenderer = new ShapeRenderer();
        mContext.left = 10;
        mContext.bottom = 20;
        mContext.body = new Body(0, 0, 1.5f);

        mContext.parts = new AbstractPart[] {
                new Eye(-25, 50, 0.5f),
                new Eye(25, 50, 0.4f),
                mContext.body,

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


    public void draw(Stage stage, float delta) {
        mContext.batch = stage.getBatch();
        mContext.projection = stage.getCamera().projection;
        mContext.delta = delta;
        mAnimationDeath.draw(mContext, mCrab);
        mAnimationAttack.draw(mContext, mCrab);
    }

    public void randomize() {
        for (int i = 0; i < mContext.parts.length; i++) {
            mContext.parts[i].randomize();
        }
    }

    public void startDeathAnimation() {
        mAnimationDeath.restart();
    }

    public void setQuestion(Drawable question) {
        mContext.questionTexture = question instanceof SpriteDrawable
                ? ((SpriteDrawable)question).getSprite().getTexture()
                : null;
    }

}
