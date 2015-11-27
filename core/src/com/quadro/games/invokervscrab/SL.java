package com.quadro.games.invokervscrab;

import com.quadro.games.invokervscrab.ivc.IvcProcessor;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class SL {

    private static IvcProcessor mGame;

    public static IvcProcessor getGame() {
        if (mGame == null) {
            mGame = new IvcProcessor();
        }
        return mGame;
    }

}
