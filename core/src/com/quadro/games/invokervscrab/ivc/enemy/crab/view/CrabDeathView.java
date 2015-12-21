package com.quadro.games.invokervscrab.ivc.enemy.crab.view;

import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.AbstractPart;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.part.Body;
import com.quadro.games.invokervscrab.view.shape.ShapeGroup;

/**
 * Created by Quadrowin on 21.12.2015.
 */
public class CrabDeathView extends WidgetGroup {

    private AbstractPart[] mParts;

    private ShapeGroup mPartsActor = new ShapeGroup();

    private float mTime = 9999;

    @Override
    public void act(float delta) {
        if (mTime > 1) {
            return;
        }
        mTime += delta;

        AbstractPart[] parts = mParts;
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

    public void assignFrom(CrabView view) {
        mTime = 0;
        mParts = view.getParts();
        mPartsActor = view.getPartsActor();
        mPartsActor.remove();
        this.addActor(mPartsActor);
        setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        view.getParent().addActor(this);
        view.remove();
    }

}
