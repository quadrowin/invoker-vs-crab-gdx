package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.BuffSlot;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class MixSkill extends AbstractSkill {

    private Map<String, String> mSkillToClass = new HashMap<String, String>();

    private Map<String, Character> mBuffToSeq = new HashMap<String, Character>();

    public MixSkill() {
        mSkillToClass.put("111", Result111.class.getName());

        mSkillToClass.put("112", Result112.class.getName());
        mSkillToClass.put("121", Result112.class.getName());
        mSkillToClass.put("211", Result112.class.getName());

        mSkillToClass.put("113", Result113.class.getName());
        mSkillToClass.put("131", Result113.class.getName());
        mSkillToClass.put("311", Result113.class.getName());

        mSkillToClass.put("122", Result122.class.getName());
        mSkillToClass.put("212", Result122.class.getName());
        mSkillToClass.put("221", Result122.class.getName());

        mSkillToClass.put("123", Result123.class.getName());
        mSkillToClass.put("132", Result123.class.getName());
        mSkillToClass.put("213", Result123.class.getName());
        mSkillToClass.put("231", Result123.class.getName());
        mSkillToClass.put("312", Result123.class.getName());
        mSkillToClass.put("321", Result123.class.getName());

        mSkillToClass.put("133", Result133.class.getName());
        mSkillToClass.put("313", Result133.class.getName());
        mSkillToClass.put("331", Result133.class.getName());

        mSkillToClass.put("222", Result222.class.getName());

        mSkillToClass.put("223", Result223.class.getName());
        mSkillToClass.put("232", Result223.class.getName());
        mSkillToClass.put("322", Result223.class.getName());

        mSkillToClass.put("233", Result233.class.getName());
        mSkillToClass.put("323", Result233.class.getName());
        mSkillToClass.put("332", Result233.class.getName());

        mSkillToClass.put("333", Result333.class.getName());

        mBuffToSeq.put(RuneFirstSkill.class.getName(), '1');
        mBuffToSeq.put(RuneSecondSkill.class.getName(), '2');
        mBuffToSeq.put(RuneThirdSkill.class.getName(), '3');
    }

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        if (!game.tryUseMp(skill.getInfo().getManaCost())) {
            return;
        }

        List<BuffSlot> buffs = game.getBuffs();
        char[] sequence = {'0', '0', '0'};
        int foundBuffs = 0;
        for (int i = 0; i < buffs.size(); i++) {
            String buffSkill = buffs.get(i).getSourceSkill().getWorker().getClass().getName();
            Character buffChar = mBuffToSeq.get(buffSkill);
            if (buffChar != null) {
                sequence[foundBuffs] = buffChar;
                foundBuffs++;
            }
        }

        String resultSkillName = mSkillToClass.get(String.valueOf(sequence));
        SkillItem resultSkill = game.getSkill(resultSkillName);

        SkillItem[] mixed = game.getMixedSkills();

        if (mixed[0] == null) {
            mixed[0] = resultSkill;
        } else if (mixed[0] == resultSkill) {
            // ничего
        } else if (mixed[1] == null) {
            mixed[1] = resultSkill;
        } else if (mixed[1] == resultSkill) {
            // меняем скилы местами
            mixed[1] = mixed[0];
            mixed[0] = resultSkill;
        } else {
            // другой скилл
            mixed[0] = mixed[1];
            mixed[1] = resultSkill;
        }

        game.raiseMixedChange();
    }

}
