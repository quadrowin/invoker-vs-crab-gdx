package com.quadro.games.invokervscrab.ivc.skill;

import com.quadro.games.invokervscrab.ivc.skill.thing.BottleSkill;
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

/**
 * Created by Quadrowin on 01.12.2015.
 */
public class SkillLoader {

    public SkillItem[] loadSkills() {
        return new SkillItem[] {
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

                // Итемы
                new SkillItem(
                        new SkillInfo("Bottle", "Regen HP and MP", 0),
                        new BottleSkill()
                ),
        };
    }

}
