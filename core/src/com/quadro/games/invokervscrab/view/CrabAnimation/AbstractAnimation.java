package com.quadro.games.invokervscrab.view.CrabAnimation;

import com.quadro.games.invokervscrab.ivc.mob.Crab;
import com.quadro.games.invokervscrab.view.CrabContext;

/**
 * Created by Quadrowin on 05.12.2015.
 */
abstract public class AbstractAnimation {

    abstract public void draw(CrabContext context, Crab crab);

    abstract public void restart();

}
