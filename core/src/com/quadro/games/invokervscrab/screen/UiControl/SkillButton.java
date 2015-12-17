package com.quadro.games.invokervscrab.screen.UiControl;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.screen.AbstractIvcScreen;

/**
 * Created by Quadrowin on 16.12.2015.
 */
public class SkillButton {

    private static final float BUTTON_HEIGHT = 50;

    private static final float BUTTON_WIDTH = 50;

    private ImageButton mButton;

    private ImageButton.ImageButtonStyle mButtonStyle;

    private Label mMpLabel;

    public SkillButton(AbstractIvcScreen screen, SkillItem skill, ClickListener clickListener, float x, float y) {
        Drawable dr = screen.getSkin().getDrawable(skill.getWorkerName());

        mButtonStyle = new ImageButton.ImageButtonStyle(null, null, null, dr, null, null);
        mButton = new ImageButton(mButtonStyle);
        mButton.setUserObject(skill);
        screen.addStageBounds(mButton, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);

        Label.LabelStyle mpCostLabelStyle = screen.getSkin().get("label-style-mp-cost", Label.LabelStyle.class);
        int manaCost = (int)skill.getInfo().getManaCost();
        mMpLabel = new Label(Integer.toString(manaCost), mpCostLabelStyle);
        mMpLabel.setVisible(manaCost > 0);
        mMpLabel.setAlignment(Align.center, Align.center);
        mMpLabel.setBounds(
                mButton.getWidth() * 2 / 3,
                0,
                mButton.getWidth() / 3,
                mButton.getHeight() / 5
        );
        mMpLabel.setFontScale(0.4f * screen.getPx());
        mButton.addActor(mMpLabel);

        mButton.addListener(clickListener);
    }

    public void setSkill(AbstractIvcScreen screen, SkillItem skill) {
        if (skill == null) {
            mButtonStyle.imageUp = screen.getSkin().getDrawable("transparent");
            mMpLabel.setVisible(false);
            return;
        }

        Drawable dr = screen.getSkin().getDrawable(skill.getWorkerName());
        mButtonStyle.imageUp = dr;

        int manaCost = (int)skill.getInfo().getManaCost();
        mMpLabel.setVisible(manaCost > 0);
        mMpLabel.setText(Integer.toString(manaCost));
    }

}
