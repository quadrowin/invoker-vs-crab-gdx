package com.quadro.games.invokervscrab.ivc.mob;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Crab {

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

    public void resetAttackAnimation() {
        mAttackCurrentTime = 0;
    }

    public void setOnAttackFinish(Runnable callback) {
        mOnAttackFinish = callback;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void update(float delta) {
        mAttackCurrentTime += delta;
        while (mAttackCurrentTime > mAttackOneTime) {
            mAttackCurrentTime -= mAttackOneTime;
            mAttacksCount++;
            mOnAttackFinish.run();
        }
    }

}
