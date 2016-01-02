package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.quadro.games.invokervscrab.ivc.enemy.crab.view.CrabView;
import com.quadro.games.invokervscrab.view.AbstractAnimation;

/**
 * Created by Quadrowin on 31.12.2015.
 */
abstract public class CrabAnimation extends AbstractAnimation {

    protected CrabView mCrab;

    @Override
    public void actorChanged() {
        super.actorChanged();
        mCrab = (CrabView)mActor;
    }

}
