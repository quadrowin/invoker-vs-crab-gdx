package com.quadro.games.invokervscrab.ivc.skill.thing;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.IvcSounds;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;
import com.quadro.games.invokervscrab.ivc.effect.worker.BottleEffect;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.worker.AbstractSkill;

import java.util.List;

/**
 * Created by Quadrowin on 01.12.2015.
 */
public class BottleSkill extends AbstractSkill {

    private int mCharge = 3;

    /**
     * Бутылка в руках - можно пить.
     * Может быть в осле.
     */
    private boolean mInHand = true;

    @Override
    public String getSound() {
        return IvcSounds.THING_BOTTLE;
    }

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        if (!mInHand) {
            return;
        }

        List<EffectItem> buffs = game.getPlayer().mEffects;

        int bottleEffectIndex = -1;

        for (int i = 0; i < buffs.size(); i++) {
            if (!buffs.get(i).getWorkerName().equals(BottleEffect.class.getName())) {
                // это не эффект от ботла
                continue;
            }
            bottleEffectIndex = i;
            break;
        }

        EffectItem newBuff = null;
        if (bottleEffectIndex >= 0) {
            // 3 руны, первую убираем
            newBuff = buffs.remove(bottleEffectIndex);
        }
        if (newBuff == null) {
            newBuff = new EffectItem(skill.getInfo(), new BottleEffect());
        } else {
            newBuff.setTimeExists(0);
        }

        game.getPlayer().addEffect(newBuff);
    }

}
