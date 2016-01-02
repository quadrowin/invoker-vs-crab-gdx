package com.quadro.games.invokervscrab.screen.UiControl;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quadro.games.invokervscrab.screen.AbstractScreen;

/**
 * Created by Quadrowin on 02.01.2016.
 */
public class MenuButton {

    private static final float BUTTON_HEIGHT = 50;

    private static final float BUTTON_WIDTH = 100;

    private TextButton mButton;

//    private Label mLabel;

    public MenuButton(AbstractScreen screen, float x, float y, ClickListener clickListener) {
        TextButton.TextButtonStyle tbs = screen.getSkin().get("text-button-style", TextButton.TextButtonStyle.class);
        mButton = new TextButton("", tbs);
        screen.addStageBounds(mButton, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);

//        Label.LabelStyle labelStyle = screen.getSkin().get("label-style-menu-button", Label.LabelStyle.class);
//        mLabel = new Label("Button", labelStyle);
//        mLabel.setAlignment(Align.center, Align.center);
//        mLabel.setBounds(
//                0,
//                0,
//                mButton.getWidth(),
//                mButton.getHeight()
//        );
//        mLabel.setFontScale(mButton.getHeight());
//        mButton.addActor(mLabel);

        mButton.addListener(clickListener);
    }

    public void setText(String text) {
        mButton.setText(text);
    }

}
