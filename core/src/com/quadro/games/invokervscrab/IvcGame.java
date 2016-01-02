package com.quadro.games.invokervscrab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.quadro.games.invokervscrab.screen.AbstractScreen;
import com.quadro.games.invokervscrab.screen.MainMenuScreen;

public class IvcGame extends ApplicationAdapter {

	private SpriteBatch batch;

	private Texture img;

    private FPSLogger mFps;

    private Skin mSkin;

    private AbstractScreen mScreen;

    private Class<? extends AbstractScreen> mSwitchToScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
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

        String[] uiTextures = new String[] {
                "ui-button-hint-text",              "data/ui/button-hint-text.png",

                "ui-button-down-32",                "data/ui/button32-down.png",
                "ui-button-up-32",                  "data/ui/button32-up.png",

                "ui-button-down-64",                "data/ui/button64-down.png",
                "ui-button-up-64",                  "data/ui/button64-up.png",
        };
        for (int i = 0; i < uiTextures.length; i += 2) {
            Texture texture = new Texture(Gdx.files.internal(uiTextures[i + 1]));
            mSkin.add(uiTextures[i], texture);
        }

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

        AbstractScreen currentScreen = getScreen();

        // update the screen
        currentScreen.render(Gdx.graphics.getDeltaTime());

        // if the current screen is a main menu screen we switch to
        // the game loop
        if (mSwitchToScreen != null) {
            // dispose the resources of the current screen
            currentScreen.dispose();
            try {
                mScreen = mSwitchToScreen.getConstructor(this.getClass()).newInstance(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mSwitchToScreen = null;
        }

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
        mSwitchToScreen = screenClass;
    }

}
