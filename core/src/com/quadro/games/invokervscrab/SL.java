package com.quadro.games.invokervscrab;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.IvcSounds;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class SL {

    private static IvcProcessor mGame;

    private static IvcSounds mSounds = new IvcSounds();

    public static IvcProcessor getGame() {
        if (mGame == null) {
            mGame = new IvcProcessor();
        }
        return mGame;
    }

    public static IvcSounds getSounds() {
        return mSounds;
    }

}
