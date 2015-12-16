package com.quadro.games.invokervscrab.screen.UiControl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.LevelParams;
import com.quadro.games.invokervscrab.screen.AbstractIvcScreen;

/**
 * Created by Quadrowin on 14.12.2015.
 */
public class ExpBar extends AbstractBar {

    public ExpBar(AbstractIvcScreen screen) {
        Skin skin = screen.getSkin();
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("button-down", Color.DARK_GRAY),
                skin.newDrawable("button-down", Color.GREEN)
        );
        barStyle.knobBefore = barStyle.knob;

        mProgress = new ProgressBar(0, 100, 1, false, barStyle);
        screen.addStageBounds(mProgress, 0, 0, 200, 20);

        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("default"), Color.WHITE);

        mLabel = new Label("100/100", labelStyle);
        mLabel.setAlignment(Align.center, Align.center);
        mLabel.setFontScale(0.4f);
        mLabel.setBounds(
                mProgress.getX(),
                mProgress.getY(),
                mProgress.getWidth(),
                mProgress.getHeight()
        );
        screen.getStage().addActor(mLabel);
    }

    public void update(GameObjectState player) {
        LevelParams level = SL.getGame().getLeveling().getParams(player.mLevel);
        mProgress.setRange(0, level.getExpToUp());
        int currentExp = player.getExperience() - level.getStartExp();
        mProgress.setValue(currentExp);
        mLabel.setText(
                Integer.toString(currentExp)
                        + "/"
                        + Integer.toString(level.getExpToUp())
        );
    }

}
