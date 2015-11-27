package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.quadro.games.invokervscrab.IvcGame;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public abstract class AbstractIvcScreen implements Screen {

    protected Stage mStage;

    protected IvcGame mGame;

    public AbstractIvcScreen(IvcGame game) {
        mGame = game;
        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);
    }

    /** Called when a screen should render itself */
    public abstract void draw (float delta);

    /** Called when the screen should update itself, e.g. continue a simulation etc. */
    public abstract void update (float delta);

    @Override
    public void render (float delta) {
        update(delta);
        draw(delta);
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }

}
