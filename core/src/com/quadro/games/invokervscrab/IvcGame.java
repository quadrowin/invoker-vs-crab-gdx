package com.quadro.games.invokervscrab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadro.games.invokervscrab.screen.AbstractScreen;
import com.quadro.games.invokervscrab.screen.MainMenuScreen;

public class IvcGame extends ApplicationAdapter {

    private FPSLogger mFps;

    private Skin mSkin;

    private AbstractScreen mScreen;

	@Override
	public void create () {
        mFps = new FPSLogger();
//        mScreen = new FightScreen(this);
        mScreen = new MainMenuScreen(this);
	}

    public Skin getSkin() {
        if (mSkin != null) {
            return mSkin;
        }
        mSkin = new Skin();

        BitmapFont font = new BitmapFont();
        mSkin.add("default", font, BitmapFont.class);

        SL.getLoaderService().loadTextures(
                mSkin,
                new String[]{
                        "ui-button-hint-text", "data/ui/button-hint-text.png",

                        "ui-button-down-32", "data/ui/button32-down.png",
                        "ui-button-up-32", "data/ui/button32-up.png",

                        "ui-button-down-64", "data/ui/button64-down.png",
                        "ui-button-up-64", "data/ui/button64-up.png",
                }
        );

        NinePatch patchUp = new NinePatch(
                mSkin.get("ui-button-up-64", Texture.class),
                16, 16, 16, 16
        );

        NinePatch patchDown = new NinePatch(
                mSkin.get("ui-button-down-64", Texture.class),
                16, 16, 16, 16
        );

        mSkin.add(
                "ui-button-up-64-np",
                new NinePatchDrawable(patchUp),
                Drawable.class
        );
        mSkin.add(
                "ui-button-down-64-np",
                new NinePatchDrawable(patchDown),
                Drawable.class
        );

        Label.LabelStyle menuButtonLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        mSkin.add("label-style-menu-button", menuButtonLabelStyle, Label.LabelStyle.class);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                mSkin.getDrawable("ui-button-up-64-np"),
                mSkin.getDrawable("ui-button-down-64-np"),
                null,
                font
        );
        mSkin.add("text-button-style-default", textButtonStyle);

        return mSkin;
    }

    /**
     * Создание нового скина с базовыми ресурсами
     * @return
     */
    public Skin getNewSkin() {
        Skin src = getSkin();
        Skin dst = new Skin();

        dst.add("default", src.getFont("default"), BitmapFont.class);

        String[] copyTextures = new String[] {
                "ui-button-hint-text",

                "ui-button-down-32",
                "ui-button-up-32",

                "ui-button-down-64",
                "ui-button-up-64",
        };

        for (int i = 0; i < copyTextures.length; i++) {
            dst.add(
                    copyTextures[i],
                    src.get(copyTextures[i], Texture.class),
                    Texture.class
            );
        }

        String[] copyDrawables = new String[] {
                "ui-button-down-64-np",
                "ui-button-up-64-np",
        };

        for (int i = 0; i < copyDrawables.length; i++) {
            dst.add(
                    copyDrawables[i],
                    src.getDrawable(copyDrawables[i]),
                    Drawable.class
            );
        }

        dst.add(
                "label-style-menu-button",
                src.get("label-style-menu-button", Label.LabelStyle.class),
                Label.LabelStyle.class
        );

        dst.add(
                "text-button-style-default",
                src.get("text-button-style-default", TextButton.TextButtonStyle.class),
                TextButton.TextButtonStyle.class
        );

        return dst;
    }

    public AbstractScreen getScreen() {
        return mScreen;
    }

    @Override
    public void pause() {
        if (mScreen != null) {
            mScreen.pause();
        }
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

        // update the screen
        mScreen.render(Gdx.graphics.getDeltaTime());

        mFps.log();
	}

    @Override
    public void resize (int width, int height) {
        if (mScreen != null) {
            mScreen.resize(width, height);
        }
    }

    @Override
    public void resume() {
        if (mScreen != null) {
            mScreen.resume();
        }
    }

    public void switchToScreen(Class<? extends AbstractScreen> screenClass) {
        try {
            AbstractScreen screen = screenClass.getConstructor(this.getClass()).newInstance(this);
            switchToScreen(screen, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToScreen(AbstractScreen newScreen, boolean dispose) {
        if (dispose && mScreen != null) {
            mScreen.dispose();
        }
        mScreen = newScreen;
        if (mScreen != null) {
            Gdx.input.setInputProcessor(mScreen.getStage());
        }
    }

}
