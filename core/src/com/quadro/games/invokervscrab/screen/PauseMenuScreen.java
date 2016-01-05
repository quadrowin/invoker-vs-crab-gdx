package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadro.games.invokervscrab.IvcGame;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.screen.PauseMenu.CloseButton;
import com.quadro.games.invokervscrab.screen.PauseMenu.LeaveMatchButton;

/**
 * Created by Quadrowin on 04.01.2016.
 */
public class PauseMenuScreen extends AbstractScreen {

    private AbstractScreen mPausedScreen;

    public PauseMenuScreen(IvcGame game, AbstractScreen pausedScreen) {
        super(game);
        mPausedScreen = pausedScreen;

        mStage.setViewport(new FitViewport(400 * mPx, 300 * mPx));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        SL.getLoaderService().loadTextures(
                mSkin,
                new String[]{
                        "ui-panel-64",              "data/ui/panel-64.png",
                }
        );

        NinePatch npPanel = new NinePatch(
                mSkin.get("ui-panel-64", Texture.class),
                16, 16, 16, 16
        );

        Drawable npdPanel = new NinePatchDrawable(npPanel);

        TextButton background = new TextButton(
                "",
                new TextButton.TextButtonStyle(
                        npdPanel,
                        npdPanel,
                        null,
                        mSkin.getFont("default")
                )
        );
        addStageBounds(background, 50, 50, 300, 200);

        Label lblTitle = new Label("GAME IS PAUSED", mSkin.get("label-style-menu-button", Label.LabelStyle.class));
        lblTitle.setAlignment(Align.center, Align.center);
        lblTitle.setBounds(0, background.getHeight() - 50, background.getWidth(), 30);
        lblTitle.setFontScale(getPx());
        background.addActor(lblTitle);

        new CloseButton(this, background);
        new LeaveMatchButton(this, background);
    }

    @Override
    public void draw(float delta) {
        mPausedScreen.draw(0);
        mStage.draw();
    }

    public AbstractScreen getPausedScreen() {
        return mPausedScreen;
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        mStage.act(delta);
    }

}
