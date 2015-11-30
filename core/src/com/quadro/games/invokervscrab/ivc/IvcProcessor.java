package com.quadro.games.invokervscrab.ivc;

import com.quadro.games.invokervscrab.ivc.skill.SkillInfo;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixedFirstSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixedSecondSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneFirstSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneSecondSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneThirdSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result111;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result112;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result113;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result122;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result123;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result133;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result222;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result223;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result233;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result333;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class IvcProcessor {

    private SkillItem[] mSkills;

    private Map<String, SkillItem> mSkillsMap = new HashMap<String, SkillItem>();

    private String[] mAllQuestions = new String[] {
            Result111.class.getName(),
            Result112.class.getName(),
            Result113.class.getName(),

            Result122.class.getName(),
            Result123.class.getName(),
            Result133.class.getName(),

            Result222.class.getName(),
            Result223.class.getName(),
            Result233.class.getName(),

            Result333.class.getName(),
    };

    private List<BuffSlot> mBuffs = new ArrayList<BuffSlot>();

    private String mCurrentQuestion = Result111.class.getName();

    private SkillItem[] mMixedSkills = new SkillItem[2];

    private GameObjectState mPlayer = new GameObjectState();

    private String[] mMixedCodeSolver = new String[] {
            null,
            RuneFirstSkill.class.getName(),
            RuneSecondSkill.class.getName(),
            RuneThirdSkill.class.getName(),
    };

    private GameCallback mOnMixedChange;

    private GameCallback mOnSkillNotEnoughMp;

    public IvcProcessor() {

        mSkills = new SkillItem[] {
                // Руны
                new SkillItem(
                        new SkillInfo("Rune Quas", "Use rune 1", 0),
                        new RuneFirstSkill()
                ),
                new SkillItem(
                        new SkillInfo("Rune Wex", "Use rune 2", 0),
                        new RuneSecondSkill()
                ),
                new SkillItem(
                        new SkillInfo("Rune Exort", "Use rune 3", 0),
                        new RuneThirdSkill()
                ),
                // Запуск замиксованных скилов
                new SkillItem(
                        new SkillInfo("Mixed 1", "Use mixed skill 1", 0),
                        new MixedFirstSkill()
                ),
                new SkillItem(
                        new SkillInfo("Mixed 2", "Use mixed skill 2", 0),
                        new MixedSecondSkill()
                ),
                // Миксование
                new SkillItem(
                        new SkillInfo("Mix", "Mix runes", 20),
                        new MixSkill()
                ),
                // Результаты миксования
                new SkillItem(
                        new SkillInfo("Cold Snap", "Use 111 mixed", 100),
                        new Result111()
                ),
                new SkillItem(
                        new SkillInfo("Ghost Walk", "Use 112 mixed", 200),
                        new Result112()
                ),
                new SkillItem(
                        new SkillInfo("Ice Wall", "Use 113 mixed", 175),
                        new Result113()
                ),
                new SkillItem(
                        new SkillInfo("Tornado", "Use 122 mixed", 150),
                        new Result122()
                ),
                new SkillItem(
                        new SkillInfo("Deafening Blast", "Use 123 mixed", 200),
                        new Result123()
                ),
                new SkillItem(
                        new SkillInfo("Forge Spirit", "Use 133 mixed", 75),
                        new Result133()
                ),
                new SkillItem(
                        new SkillInfo("EMP", "Use 222 mixed", 125),
                        new Result222()
                ),
                new SkillItem(
                        new SkillInfo("Alacrity", "Use 223 mixed", 45),
                        new Result223()
                ),
                new SkillItem(
                        new SkillInfo("Chaos Meteor", "Use 233 mixed", 200),
                        new Result233()
                ),
                new SkillItem(
                        new SkillInfo("Sun Strike", "Use 333 mixed", 175),
                        new Result333()
                ),
        };
        for (int i = 0; i < mSkills.length; i++) {
            mSkillsMap.put(
                    mSkills[i].getWorker().getClass().getName(),
                    mSkills[i]
            );
        }
    }

    public BuffSlot getBuff(int index) {
        return index < mBuffs.size() ? mBuffs.get(index) : null;
    }

    public List<BuffSlot> getBuffs() {
        return mBuffs;
    }

    public String getCurrentQuestion() {
        return mCurrentQuestion;
    }

    /**
     * Слоты со смиксованными скиллами
     * @return
     */
    public SkillItem[] getMixedSkills() {
        return mMixedSkills;
    }

    public GameObjectState getPlayer() {
        return mPlayer;
    }

    public String[] getRuneHint() {
        // "package.skill.mixed.Result111" -> "111"
        String code = getCurrentQuestion().replaceFirst("^.*Result([1-3]{3}).*$", "$1");
//        Log.d(getClass().getName(), "Hint code " + code);
        return new String[] {
                mMixedCodeSolver[Character.getNumericValue(code.charAt(0))],
                mMixedCodeSolver[Character.getNumericValue(code.charAt(1))],
                mMixedCodeSolver[Character.getNumericValue(code.charAt(2))],
        };
    }

    public SkillItem getSkill(String worker) {
        return mSkillsMap.get(worker);
    }

    /**
     * Вызов колбэка при изменении миксованных скилов
     */
    public void raiseMixedChange() {
        mOnMixedChange.run(this);
    }

    public void randomizeQuestion() {
        int ind = (int) Math.floor(Math.random() * mAllQuestions.length);
        mCurrentQuestion = mAllQuestions[ind];
    }

    /**
     * Сброс до 1 лвла и начального состояния
     */
    public void resetToStart() {
        mPlayer.mCurrentHp = mPlayer.mMaxHp = 511;
        mPlayer.mRegenHp = 0.25f;
        mPlayer.mCurrentMp = mPlayer.mMaxMp = 286;
        mPlayer.mRegenMp = 0.01f;
        mPlayer.mLevel = 1;
        mPlayer.mExperience = 0;
    }

    public void setOnMixedChange(GameCallback callback) {
        mOnMixedChange = callback;
    }

    public void setOnSkillNotEnoughMp(GameCallback callback) {
        mOnSkillNotEnoughMp = callback;
    }

    public void tryUseMixed(int index) {
        if (mMixedSkills[index] == null) {
            return ;
        }
        if (mMixedSkills[index].getWorker().getClass().getName() == mCurrentQuestion) {
            randomizeQuestion();
        }
    }

    public boolean tryUseMp(float mp) {
        if (mPlayer.mCurrentMp < mp) {
            mOnSkillNotEnoughMp.run(this);
            return false;
        }
        mPlayer.mCurrentMp -= mp;
        return true;
    }

}
