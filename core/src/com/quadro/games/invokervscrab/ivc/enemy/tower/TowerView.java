package com.quadro.games.invokervscrab.ivc.enemy.tower;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.quadro.games.invokervscrab.screen.AbstractScreen;

/**
 * Created by Quadrowin on 18.12.2015.
 */
public class TowerView {

    ImageButton mButton;

    public TowerView(AbstractScreen screen) {
        Drawable dr = screen.getSkin().getDrawable("unit/tower");
        mButton = new ImageButton(dr);
        mButton.getColor().a = 0.5f;
        screen.addStageBounds(mButton, 100, 65, 200, 200);
    }

}
