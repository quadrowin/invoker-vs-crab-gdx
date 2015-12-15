package com.quadro.games.invokervscrab.ivc.skill.worker;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneExortEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneQuasEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneWexEffect;
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

    private Map<String, String> mCodeToSkill = new HashMap<String, String>();

//    private Map<String, String> mSkillToCode = new HashMap<String, String>();

    private Map<String, Character> mBuffToSeq = new HashMap<String, Character>();

    public MixSkill() {
        mCodeToSkill.put("QQQ", Result111.class.getName());

        mCodeToSkill.put("QQW", Result112.class.getName());
        mCodeToSkill.put("QWQ", Result112.class.getName());
        mCodeToSkill.put("WQQ", Result112.class.getName());

        mCodeToSkill.put("QQE", Result113.class.getName());
        mCodeToSkill.put("QEQ", Result113.class.getName());
        mCodeToSkill.put("EQQ", Result113.class.getName());

        mCodeToSkill.put("QWW", Result122.class.getName());
        mCodeToSkill.put("WQW", Result122.class.getName());
        mCodeToSkill.put("WWQ", Result122.class.getName());

        mCodeToSkill.put("QWE", Result123.class.getName());
        mCodeToSkill.put("QEW", Result123.class.getName());
        mCodeToSkill.put("WQE", Result123.class.getName());
        mCodeToSkill.put("WEQ", Result123.class.getName());
        mCodeToSkill.put("EQW", Result123.class.getName());
        mCodeToSkill.put("EWQ", Result123.class.getName());

        mCodeToSkill.put("QEE", Result133.class.getName());
        mCodeToSkill.put("EQE", Result133.class.getName());
        mCodeToSkill.put("EEQ", Result133.class.getName());

        mCodeToSkill.put("WWW", Result222.class.getName());

        mCodeToSkill.put("WWE", Result223.class.getName());
        mCodeToSkill.put("WEW", Result223.class.getName());
        mCodeToSkill.put("EWW", Result223.class.getName());

        mCodeToSkill.put("WEE", Result233.class.getName());
        mCodeToSkill.put("EWE", Result233.class.getName());
        mCodeToSkill.put("EEW", Result233.class.getName());

        mCodeToSkill.put("EEE", Result333.class.getName());

//        for (Map.Entry<String, String> entry : mCodeToSkill.entrySet()) {
//            mSkillToCode.put(entry.getValue(), entry.getKey());
//        }

        mBuffToSeq.put(RuneQuasEffect.class.getName(), 'Q');
        mBuffToSeq.put(RuneWexEffect.class.getName(), 'W');
        mBuffToSeq.put(RuneExortEffect.class.getName(), 'E');
    }

//    public String convertCodeToSkill(String code) {
//        return mCodeToSkill.get(code);
//    }
//
//    public String convertSkillToCode(String skill) {
//        return mSkillToCode.get(skill);
//    }

    @Override
    public void useSkill(IvcProcessor game, SkillItem skill) {
        List<EffectItem> buffs = game.getPlayer().mEffects;
        char[] sequence = {'0', '0', '0'};
        int foundBuffs = 0;
        for (int i = 0; i < buffs.size(); i++) {
            String buffSkill = buffs.get(i).getWorkerName();
            Character buffChar = mBuffToSeq.get(buffSkill);
            if (buffChar != null) {
                sequence[foundBuffs] = buffChar;
                foundBuffs++;
            }
        }

        String resultSkillName = mCodeToSkill.get(String.valueOf(sequence));
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
