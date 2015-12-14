package com.quadro.games.invokervscrab.screen.FightControl;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

/**
 * Created by Quadrowin on 14.12.2015.
 */
public class AbstractBar {

    protected ProgressBar mProgress;

    protected Label mLabel;

    protected void updateRegenLabel(Label label, float regen, boolean ltMax) {
        if (!ltMax) {
            label.setVisible(false);
            return;
        }
        if (regen < 0.1) {
            label.setText(String.format("+%.2f", regen));
        } else {
            label.setText(String.format("+%.1f", regen));
        }
        label.setVisible(true);
    }

}
