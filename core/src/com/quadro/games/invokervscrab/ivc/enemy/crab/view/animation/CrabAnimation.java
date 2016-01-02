package com.quadro.games.invokervscrab.ivc.enemy.crab.view.animation;

import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.quadro.games.invokervscrab.ivc.enemy.crab.AnimationCallback;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.CrabView;

/**
 * Created by Quadrowin on 31.12.2015.
 */
abstract public class CrabAnimation extends WidgetGroup {

    protected static final float STOP_TIME = Float.MAX_VALUE;

    protected CrabView mCrab;

    private AnimationCallback mOnFinish;

    private boolean mRemoveAfterFinish = true;

    protected float mTime = STOP_TIME;

    protected boolean actTime(float delta) {
        if (mTime > getDuration()) {
            if (mTime < STOP_TIME) {
                mTime = STOP_TIME;
                doOnFinish();
            }
            return false;
        }
        mTime += delta;
        return true;
    }

    protected void doOnFinish() {
        if (mOnFinish != null) {
            mOnFinish.run(this);
        }
        if (mRemoveAfterFinish) {
            remove();
        }
    }

    /**
     * @return Продолжительность анимации
     */
    public float getDuration() {
        return 1;
    }

    public void setCrab(CrabView crab) {
        mCrab = crab;
        mTime = 0;
    }

    public void setOnFinish(AnimationCallback callback) {
        mOnFinish = callback;
    }

    public void setRemoveAfterFinish(boolean remove) {
        mRemoveAfterFinish = remove;
    }

}
