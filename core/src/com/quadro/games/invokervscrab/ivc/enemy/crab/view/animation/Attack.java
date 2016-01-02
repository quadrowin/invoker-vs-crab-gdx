package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.quadro.games.invokervscrab.view.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Hand;

/**
 * Created by Quadrowin on 02.01.2016.
 */
public class Attack extends CrabAnimation {

    @Override
    public void act (float delta) {
        AbstractPart[] parts = mCrab.getParts();
        float attackAnimationFrame = mCrab.getCrab().getAttackFrame();
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            if (p instanceof Hand) {
                ((Hand)p).setAttackFrame( attackAnimationFrame );
            }
        }
    }

}
