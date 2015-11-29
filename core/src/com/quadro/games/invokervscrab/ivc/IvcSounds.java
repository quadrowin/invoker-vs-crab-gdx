package com.quadro.games.invokervscrab.ivc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class IvcSounds {

    private String mSoundsPath = "data/sounds/";

    public static final String SKILL_USE_FAIL = "skill_use_fail.ogg";

    private HashMap<String, Sound> mLoaded = new HashMap<String, Sound>();

    public void loadSounds(String[] names) {
        for (int i = 0; i < names.length; i++) {
            mLoaded.put(names[i], Gdx.audio.newSound(Gdx.files.internal(mSoundsPath + names[i])));
        }
    }

    public long play(String name) {
        return mLoaded.get(name).play();
    }

}
