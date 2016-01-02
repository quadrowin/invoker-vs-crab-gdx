package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadro.games.invokervscrab.IvcGame;
import com.quadro.games.invokervscrab.screen.UiControl.MenuButton;
import com.quadro.games.invokervscrab.view.ColorDrawable;

/**
 * Created by Quadrowin on 02.01.2016.
 */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(IvcGame game) {
        super(game);

        // Разрешение
        float viewportWidth = 400 * mPx;
        float viewportHeight = 300 * mPx;

        mStage.setViewport(new FitViewport(viewportWidth, viewportHeight));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Texture texture = new Texture(Gdx.files.internal("data/ui/background/footer_lodyas.png"));
        mSkin.add("bg-menu", texture);

        Image bg = new Image();
        bg.setDrawable(mSkin.getDrawable("bg-menu"));

        addStageBounds(bg, 0, 0, 400, 300);

        BitmapFont font = mSkin.getFont("default");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                mSkin.getDrawable("ui-button-up-64-np"),
                mSkin.getDrawable("ui-button-down-64-np"),
                null,
                font
        );
        mSkin.add("text-button-style", textButtonStyle);


        Label.LabelStyle menuButtonLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        menuButtonLabelStyle.background = new ColorDrawable(Color.BLUE);
        mSkin.add("label-style-menu-button", menuButtonLabelStyle, Label.LabelStyle.class);

        MenuButton btnExit = new MenuButton(
                this,
                150, 100,
                new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Gdx.app.exit();
                    }

                }
        );
        btnExit.setText("Exit");

        MenuButton btnStart = new MenuButton(
                this,
                150, 200,
                new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        mGame.switchToScreen(FightScreen.class);
                    }

                }
        );
        btnStart.setText("Start Match");
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {

    }

}
