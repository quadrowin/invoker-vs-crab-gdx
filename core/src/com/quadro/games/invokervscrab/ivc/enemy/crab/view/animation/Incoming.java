package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.quadro.games.invokervscrab.ivc.enemy.crab.view.CrabView;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Hand;
import com.quadro.games.invokervscrab.view.AbstractPart;

/**
 * Created by Quadrowin on 31.12.2015.
 */
public class Incoming extends CrabAnimation {

    @Override
    public void actorChanged() {
        super.actorChanged();

        if (mActor == null) {
            return;
        }

        AbstractPart[] parts = ((CrabView)mActor).getParts();
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            if (p instanceof Hand) {
                ((Hand)p).setAttackFrame( 0.5f );
            }
        }
    }

    @Override
    public void act(float delta) {
        if (!actTime(delta)) {
            return;
        }

        if (mTime < 0.5) {
            act1(mTime * 2);
        } else {
            act2((mTime - 0.5f) * 2);
        }
    }

    private void act1(float time) {
        mActor.setScale(0.4f);
        mActor.setPosition(mStartX + 100 * time, mStartY + 50);
    }

    private void act2(float time) {
        mActor.setScale(0.4f + 0.6f * time);
        mActor.setPosition(mStartX + 100 * (1 - time), mStartY + 50 * (1 - time));
    }

}
