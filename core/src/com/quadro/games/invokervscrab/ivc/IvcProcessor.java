package com.quadro.games.invokervscrab.ivc;

import com.badlogic.gdx.Gdx;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;
import com.quadro.games.invokervscrab.ivc.enemy.crab.Crab;
import com.quadro.games.invokervscrab.ivc.enemy.crab.CrabCallback;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.SkillLoader;
import com.quadro.games.invokervscrab.ivc.skill.worker.AbstractSkill;
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

    private Leveling mLeveling = new Leveling();

    private SkillItem[] mSkills;

    private List<Crab> mCrabs = new ArrayList<Crab>();

    private Crab mTargetEnemy;

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

    private SkillItem[] mMixedSkills = new SkillItem[2];

    private GameObjectState mPlayer = new GameObjectState();

    private String[] mMixedCodeSolver = new String[] {
            null,
            RuneFirstSkill.class.getName(),
            RuneSecondSkill.class.getName(),
            RuneThirdSkill.class.getName(),
    };

    private CrabCallback mOnCrabCreate;

    private CrabCallback mOnCrabDeath;

    private GameCallback mOnMixedChange;

    private GameCallback mOnSkillNotEnoughMp;

    public IvcProcessor() {
        SkillLoader loader = new SkillLoader();
        mSkills = loader.loadSkills();
        for (int i = 0; i < mSkills.length; i++) {
            mSkillsMap.put(
                    mSkills[i].getWorker().getClass().getName(),
                    mSkills[i]
            );
        }
    }

    public List<Crab> getCrabs() {
        return mCrabs;
    }

    public Leveling getLeveling() {
        return mLeveling;
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
        String code = mTargetEnemy.getQuestion().replaceFirst("^.*Result([1-3]{3}).*$", "$1");
//        Log.d(getClass().getName(), "Hint code " + code);
        return new String[] {
                mMixedCodeSolver[Character.getNumericValue(code.charAt(0))],
                mMixedCodeSolver[Character.getNumericValue(code.charAt(1))],
                mMixedCodeSolver[Character.getNumericValue(code.charAt(2))],
        };
    }

    public <T extends AbstractSkill> SkillItem getSkill(Class<T> worker) {
        return mSkillsMap.get(worker.getName());
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
        Crab crab = new Crab();
        mTargetEnemy = crab;
        crab.setOnAttackFinish(new Runnable() {

            @Override
            public void run() {
                mPlayer.takeDamage(50);
            }

        });
        crab.setQuestion(mAllQuestions[ind]);
        mCrabs.add(crab);
        if (mOnCrabCreate != null) {
            mOnCrabCreate.run(crab);
        }
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
        mPlayer.setExperience(0);
    }

    public void setOnCrabCreate(CrabCallback cb) {
        mOnCrabCreate = cb;
    }

    public void setOnCrabDeath(CrabCallback cb) {
        mOnCrabDeath = cb;
    }

    public void setOnMixedChange(GameCallback callback) {
        mOnMixedChange = callback;
    }

    public void setOnSkillNotEnoughMp(GameCallback callback) {
        mOnSkillNotEnoughMp = callback;
    }

    public void update(float delta) {
        for (int i = 0; i < mCrabs.size(); i++) {
            mCrabs.get(i).update(delta);
        }
        mPlayer.mRegenHp = mPlayer.mBaseHpRegen * delta;
        mPlayer.mRegenMp = mPlayer.mBaseMpRegen * delta;

        boolean effects_changed = false;
        for (int i = 0; i < mPlayer.mEffects.size(); i++) {
            EffectItem effect = mPlayer.mEffects.get(i);
            float time_limit = effect.getTimeMax();
            float time_delta = delta;
            boolean effect_finish = false;
            if (time_limit > 0) {
                // временный баф
                float time_left = Math.max(0, time_limit - effect.getTimeExists());
                if (time_left < time_delta) {
                    time_delta = time_left;
                    effect_finish = true;
                }
            }
            effect.tick(mPlayer, time_delta);
            if (effect_finish) {
                mPlayer.mEffects.remove(i);
                i--;
                effects_changed = true;
            } else if (time_limit > 0) {
                effect.incTimeExists(time_delta);
            }
        }

        if (effects_changed) {
            mPlayer.raiseEffectChange();
            Gdx.app.log("effects count", mPlayer.mEffects.size() + "");
        }

        mPlayer.mCurrentHp = Math.min(mPlayer.mMaxHp, mPlayer.mCurrentHp + mPlayer.mRegenHp);
        mPlayer.mCurrentMp = Math.min(mPlayer.mMaxMp, mPlayer.mCurrentMp + mPlayer.mRegenMp);
    }

    public void tryUseMixed(int index) {
        if (mMixedSkills[index] == null) {
            return ;
        }
        if (!tryUseMp(mMixedSkills[index].getInfo().getManaCost())) {
            return ;
        }
        SL.getSounds().play(mMixedSkills[index].getWorker().getSound());
        if (mMixedSkills[index].getWorkerName().equals(mTargetEnemy.getQuestion())) {
            mOnCrabDeath.run(mTargetEnemy);
            mCrabs.remove(mTargetEnemy);
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

    public void useSkill(SkillItem skill) {
        if (!this.tryUseMp(skill.getInfo().getManaCost())) {
            return;
        }
        SL.getSounds().play(skill.getWorker().getSound());
        skill.getWorker().useSkill(this, skill);
    }

}
