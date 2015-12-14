package com.quadro.games.invokervscrab.ivc;

import java.util.HashMap;

/**
 * Сервис прокачки
 * Created by Quadrowin on 14.12.2015.
 */
public class Leveling {

    /**
     * Отображение количества опыта в уровень
     */
    private HashMap<Integer, LevelParams> mLevelsExp = new HashMap<Integer, LevelParams>();

    /**
     * по номеру уровня
     */
    private LevelParams[] mLevelsNumber;

    public Leveling() {
        mLevelsNumber = new LevelParams[] {
                new LevelParams(0,       0),     // lvl 0
                new LevelParams(200,     25),    // lvl 1
                new LevelParams(300,     41),
                new LevelParams(400,     62),
                new LevelParams(500,     88),
                new LevelParams(600,     119),
                new LevelParams(600,     155),
                new LevelParams(600,     196),
                new LevelParams(1200,    242),
                new LevelParams(1000,    293),   // lvl 9

                new LevelParams(600,     349),   // 10
                new LevelParams(2200,    410),   // 11
                new LevelParams(800,     476),
                new LevelParams(1400,    547),
                new LevelParams(1500,    623),
                new LevelParams(1600,    704),
                new LevelParams(1700,    790),
                new LevelParams(1800,    881),
                new LevelParams(1900,    977),
                new LevelParams(2000,    1078),  // 19

                new LevelParams(2100,    1184),  // 20
                new LevelParams(2200,    1295),
                new LevelParams(2300,    1411),
                new LevelParams(2400,    1532),
                new LevelParams(2500,    1658),

                new LevelParams(9999,    1789),  // lvl 24
        };

        int startExp = 0;
        for (int i = 0; i < mLevelsNumber.length; i++) {
            mLevelsNumber[i].setStartExp(startExp);
            startExp += mLevelsNumber[i].getExpToUp();
        }
    }

    public LevelParams getParams(int lvl) {
        return mLevelsNumber[lvl];
    }

}
