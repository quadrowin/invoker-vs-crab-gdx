package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Body;

/**
 * Created by Quadrowin on 21.12.2015.
 */
public class Death extends CrabAnimation {

    @Override
    public void act(float delta) {
        if (!actTime(delta)) {
            return;
        }

        AbstractPart[] parts = mCrab.getParts();
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            p.setPosition(
                    p.getX() * (1 + mTime * 0.1f),
                    p.getY() * (1 + mTime * 0.1f)
            );

            if (p instanceof Body) {
                p.setScale(p.getScaleX() / (1 + mTime), p.getScaleY() / (1 + mTime));
            } else {
                p.setScale(p.getScaleX(), p.getScaleY());
            }
            p.setRotation(mTime * (i % 3 + 1) * 50);
        }
    }

}
