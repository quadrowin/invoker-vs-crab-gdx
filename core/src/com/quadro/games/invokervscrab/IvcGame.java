package com.quadro.games.invokervscrab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.quadro.games.invokervscrab.screen.AbstractIvcScreen;
import com.quadro.games.invokervscrab.screen.FightScreen;

public class IvcGame extends ApplicationAdapter {

	SpriteBatch batch;

	Texture img;

    private FPSLogger mFps;

    private AbstractIvcScreen mScreen;

    private Class<AbstractIvcScreen> mSwitchToScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        mFps = new FPSLogger();
        mScreen = new FightScreen(this);
	}

    public AbstractIvcScreen getScreen() {
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

        AbstractIvcScreen currentScreen = getScreen();

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

    public void switchToScreen(Class<AbstractIvcScreen> screenClass) {
        mSwitchToScreen = screenClass;
    }

}
