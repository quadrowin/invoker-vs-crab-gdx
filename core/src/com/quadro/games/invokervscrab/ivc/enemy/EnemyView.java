package com.quadro.games.invokervscrab.ivc.enemy;

import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.quadro.games.invokervscrab.view.DummyAnimation;
import com.quadro.games.invokervscrab.view.AbstractAnimation;

/**
 * Created by Quadrowin on 02.01.2016.
 */
public class EnemyView extends WidgetGroup {

    private AbstractAnimation mAnimation;

    @Override
    public void act(float delta) {
        if (mAnimation != null) {
            mAnimation.act(delta);
        }
    }

    public void setAnimation(AbstractAnimation animation) {
        if (mAnimation != null) {
            mAnimation.setActor(null);
        }
        mAnimation = animation;
        animation.setActor(this);
    }

    public <T extends AbstractAnimation> AbstractAnimation setNewAnimation(Class<T> animationClass) {
        AbstractAnimation animation;
        try {
            animation = animationClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            animation = new DummyAnimation();
        }
        setAnimation(animation);
        return animation;
    }

}
