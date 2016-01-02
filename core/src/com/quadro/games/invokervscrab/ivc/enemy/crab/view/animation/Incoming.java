package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Hand;

/**
 * Created by Quadrowin on 31.12.2015.
 */
public class Incoming extends CrabAnimation {

    @Override
    public void act(float delta) {
        if (!actTime(delta)) {
            return;
        }

        mCrab.setScale(0.5f + 0.5f * mTime);

        AbstractPart[] parts = mCrab.getParts();
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            if (p instanceof Hand) {
                ((Hand)p).setAttackFrame( 0.5f );
            }
        }
    }

}
