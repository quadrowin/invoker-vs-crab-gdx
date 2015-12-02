package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;
import com.quadro.games.invokervscrab.ivc.effect.worker.AbstractEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneExortEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneQuasEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneWexEffect;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Quadrowin on 21.11.2015.
 */
abstract public class AbstractRuneSkill extends AbstractSkill {

    private static HashMap<String, String> mSkillsToEffects = new HashMap<String, String>();
    private static HashMap<String, String> mEffectsToSkills = new HashMap<String, String>();

    {
        mSkillsToEffects.put(RuneFirstSkill.class.getName(),    RuneQuasEffect.class.getName());
        mSkillsToEffects.put(RuneSecondSkill.class.getName(),   RuneWexEffect.class.getName());
        mSkillsToEffects.put(RuneThirdSkill.class.getName(),    RuneExortEffect.class.getName());

        mEffectsToSkills.put(RuneQuasEffect.class.getName(),    RuneFirstSkill.class.getName());
        mEffectsToSkills.put(RuneWexEffect.class.getName(),     RuneSecondSkill.class.getName());
        mEffectsToSkills.put(RuneExortEffect.class.getName(),   RuneThirdSkill.class.getName());
    }

    abstract protected AbstractEffect newEffect();

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        List<EffectItem> buffs = game.getPlayer().mEffects;

        int runeNumber = 0;
        int firstRuneIndex = -1;

        for (int i = 0; i < buffs.size(); i++) {
            if (!(mEffectsToSkills.containsKey(buffs.get(i).getWorkerName()))) {
                // это не эффект руны
                continue;
            }
            if (firstRuneIndex < 0) {
                firstRuneIndex = i;
            }
            runeNumber++;
            if (runeNumber == 3) {
                // Найдено 3 руны
                break;
            }
        }
        EffectItem newBuff = null;
        if (runeNumber == 3) {
            // 3 руны, первую убираем
            newBuff = buffs.remove(firstRuneIndex);
        }
        if (newBuff == null || newBuff.getInfo() != skill.getInfo()) {
            newBuff = new EffectItem(skill.getInfo(), newEffect());
        }

        game.getPlayer().addEffect(newBuff);
    }

}
