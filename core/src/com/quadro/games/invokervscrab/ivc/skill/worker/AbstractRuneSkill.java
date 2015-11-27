package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.BuffSlot;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;

import java.util.List;

/**
 * Created by Quadrowin on 21.11.2015.
 */
abstract public class AbstractRuneSkill extends AbstractSkill {

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        List<BuffSlot> buffs = game.getBuffs();

        int runeNumber = 0;
        int firstRuneIndex = -1;

        for (int i = 0; i < buffs.size(); i++) {
            if (!(buffs.get(i).getSourceSkill().getWorker() instanceof AbstractRuneSkill)) {
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
        BuffSlot newBuff = null;
        if (runeNumber == 3) {
            // 3 руны, первую убираем
            newBuff = buffs.remove(firstRuneIndex);
        }
        if (newBuff == null || newBuff.getSourceSkill() != skill) {
            newBuff = new BuffSlot(skill);
        }

        buffs.add(newBuff);
    }

}
