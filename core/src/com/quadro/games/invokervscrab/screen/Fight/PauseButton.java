package com.quadro.games.invokervscrab.screen.Fight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.screen.AbstractScreen;
import com.quadro.games.invokervscrab.screen.PauseMenuScreen;

/**
 * Created by Quadrowin on 05.01.2016.
 */
public class PauseButton {

    public PauseButton(final AbstractScreen screen, TextButton.TextButtonStyle textButtonStyle) {
        TextButton btnPause = new TextButton("", textButtonStyle);
        screen.addStageBounds(btnPause, 360, 260, 40, 40);
        btnPause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().switchToScreen(new PauseMenuScreen(screen.getGame(), screen), false);
            }

        });

        Label lblPause = new Label("||", screen.getSkin(), "default", Color.WHITE);
        lblPause.setFontScale(1.5f * screen.getPx());
        lblPause.setBounds(0, 0, btnPause.getWidth(), btnPause.getHeight());
        lblPause.setAlignment(Align.center, Align.center);
        btnPause.addActor(lblPause);
    }

}
