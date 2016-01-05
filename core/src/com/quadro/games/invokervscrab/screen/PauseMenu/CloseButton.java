package com.quadro.games.invokervscrab.screen.PauseMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.quadro.games.invokervscrab.screen.PauseMenuScreen;

/**
 * Created by Quadrowin on 05.01.2016.
 */
public class CloseButton {

    public CloseButton(final PauseMenuScreen screen, Group panel) {
        float scale = screen.getPx();
        TextButton btnPause = new TextButton("", screen.getSkin().get("text-button-style-default", TextButton.TextButtonStyle.class));
        btnPause.setBounds(
                panel.getWidth() - 30 * scale,
                panel.getHeight() - 30 * scale,
                40 * scale,
                40 * scale
        );
        panel.addActor(btnPause);
        btnPause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGame().switchToScreen(screen.getPausedScreen(), true);
            }

        });

        Label lblPause = new Label("F9", screen.getSkin(), "default", Color.WHITE);
        lblPause.setFontScale(scale);
        lblPause.setBounds(0, 0, btnPause.getWidth(), btnPause.getHeight());
        lblPause.setAlignment(Align.center, Align.center);
        btnPause.addActor(lblPause);
    }

}
