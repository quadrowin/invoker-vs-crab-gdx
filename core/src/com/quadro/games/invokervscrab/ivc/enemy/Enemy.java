package com.quadro.games.invokervscrab.ivc.enemy;

/**
 * Created by Quadrowin on 02.01.2016.
 */
public class Enemy {

    /**
     * Активна ли атака сейчас
     */
    private boolean mAttackActive = false;

    /**
     * Время на одну атаку
     */
    private float mAttackOneTime = 2;

    /**
     * Время текущей атаки
     */
    private float mAttackCurrentTime;

    /**
     * Количество атак, нанесенных этим мобом
     */
    private long mAttacksCount = 0;

    private Runnable mOnAttackFinish;

    public float getAttackFrame() {
        return mAttackCurrentTime / mAttackOneTime;
    }

    public long getAttacksCount() {
        return mAttacksCount;
    }

    public void setAttackActive(boolean active) {
        mAttackActive = active;
        mAttackCurrentTime = 0;
    }

    public void setOnAttackFinish(Runnable callback) {
        mOnAttackFinish = callback;
    }

    public void update(float delta) {
        if (!mAttackActive) {
            return;
        }
        mAttackCurrentTime += delta;
        while (mAttackCurrentTime > mAttackOneTime) {
            mAttackCurrentTime -= mAttackOneTime;
            mAttacksCount++;
            mOnAttackFinish.run();
        }
    }

}
