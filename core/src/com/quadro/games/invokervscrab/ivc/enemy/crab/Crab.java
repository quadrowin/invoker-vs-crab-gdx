package com.quadro.games.invokervscrab.ivc.enemy.crab;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Crab {

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

    private String mQuestion;

    public Crab() {

    }

    public float getAttackFrame() {
        return mAttackCurrentTime / mAttackOneTime;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setAttackActive(boolean active) {
        mAttackActive = active;
        mAttackCurrentTime = 0;
    }

    public void setOnAttackFinish(Runnable callback) {
        mOnAttackFinish = callback;
    }

    public void setQuestion(String question) {
        mQuestion = question;
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
