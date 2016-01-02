package com.quadro.games.invokervscrab.screen.UiControl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.screen.AbstractScreen;

/**
 * Created by Quadrowin on 14.12.2015.
 */
public class HpBar extends AbstractBar {

    private Label mLabelRegen;

    public HpBar (AbstractScreen screen) {
        Skin skin = screen.getSkin();

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("button-up", Color.DARK_GRAY),
                skin.newDrawable("button-up", Color.RED)
        );
        barStyle.knobBefore = barStyle.knob;

        mProgress = new ProgressBar(0, 100, 1, false, barStyle);
        screen.addStageBounds(mProgress, 0, 40, 400, 20);

        // Цифры на хп
        Label.LabelStyle hpLabelStyle = new Label.LabelStyle(skin.getFont("default"), Color.WHITE);

        mLabel = new Label("100/100", hpLabelStyle);
        mLabel.setAlignment(Align.center, Align.center);
        mLabel.setFontScale(0.4f * screen.getPx());
        mLabel.setBounds(
                mProgress.getX(),
                mProgress.getY(),
                mProgress.getWidth(),
                mProgress.getHeight()
        );
        screen.getStage().addActor(mLabel);

        // Цифры реген хп
        mLabelRegen = new Label("+0.3", hpLabelStyle);
        mLabelRegen.setAlignment(Align.center, Align.right);
        mLabelRegen.setBounds(
                mProgress.getX(),
                mProgress.getY(),
                mProgress.getWidth(),
                mProgress.getHeight()
        );
        mLabelRegen.setFontScale(0.4f * screen.getPx());
        screen.getStage().addActor(mLabelRegen);
    }

    public void update(GameObjectState player, float delta) {
        mProgress.setRange(0, player.mMaxHp);
        mProgress.setValue(player.mCurrentHp);
        mLabel.setText(
                Integer.toString((int) player.mCurrentHp)
                        + "/"
                        + Integer.toString((int) player.mMaxHp)
        );
        updateRegenLabel(mLabelRegen, player.mRegenHp / delta, player.mCurrentHp < player.mMaxHp);
    }

}
