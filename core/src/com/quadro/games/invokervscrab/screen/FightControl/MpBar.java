package com.quadro.games.invokervscrab.screen.FightControl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.screen.AbstractIvcScreen;

/**
 * Created by Quadrowin on 14.12.2015.
 */
public class MpBar extends AbstractBar {

    private Label mLabelRegen;

    public MpBar (AbstractIvcScreen screen) {
        Skin skin = screen.getSkin();
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("button-up", Color.DARK_GRAY),
                skin.newDrawable("button-up", Color.BLUE)
        );
        barStyle.knobBefore = barStyle.knob;

        mProgress = new ProgressBar(0, 100, 1, false, barStyle);
        screen.addStageBounds(mProgress, 0, 20, 400, 20);

        barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("button-down", Color.DARK_GRAY),
                skin.newDrawable("button-down", Color.GREEN)
        );
        barStyle.knobBefore = barStyle.knob;

        // Цифры на мп
        Label.LabelStyle mpLabelStyle = new Label.LabelStyle(skin.getFont("default"), Color.WHITE);
        mLabel = new Label("100/100", mpLabelStyle);
        mLabel.setAlignment(Align.center, Align.center);
        mLabel.setBounds(
                mProgress.getX(),
                mProgress.getY(),
                mProgress.getWidth(),
                mProgress.getHeight()
        );
        mLabel.setFontScale(0.4f);
        screen.getStage().addActor(mLabel);

        // Цифры реген мп
        mLabelRegen = new Label("+0.3", mpLabelStyle);
        mLabelRegen.setAlignment(Align.center, Align.right);
        mLabelRegen.setBounds(
                mProgress.getX(),
                mProgress.getY(),
                mProgress.getWidth(),
                mProgress.getHeight()
        );
        mLabelRegen.setFontScale(0.4f);
        screen.getStage().addActor(mLabelRegen);
    }

    public void update(GameObjectState player, float delta) {
        mProgress.setRange(0, player.mMaxMp);
        mProgress.setValue(player.mCurrentMp);
        mLabel.setText(
                Integer.toString((int) player.mCurrentMp)
                        + "/"
                        + Integer.toString((int) player.mMaxMp)
        );
        updateRegenLabel(mLabelRegen, player.mRegenMp / delta, player.mCurrentMp < player.mMaxMp);
    }

}
