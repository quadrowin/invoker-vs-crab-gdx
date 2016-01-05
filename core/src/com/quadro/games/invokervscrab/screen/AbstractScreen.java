package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.quadro.games.invokervscrab.IvcGame;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public abstract class AbstractScreen implements Screen {

    protected Stage mStage;

    protected IvcGame mGame;

    protected IvcProcessor mProcessor;

    protected Skin mSkin;

    /**
     * Размер пикселя
     */
    protected float mPx = 2;

    public AbstractScreen(IvcGame game) {
        mGame = game;
        mProcessor = SL.getGame();
        mStage = new Stage();
        mSkin = game.getNewSkin();
        Gdx.input.setInputProcessor(mStage);
    }

    /**
     * Добавление контрола на скрин с учетом размера пикселя
     * @param actor
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void addStageBounds(Actor actor, float x, float y, float width, float height) {
        actor.setBounds(x * mPx, y * mPx, width * mPx, height * mPx);
        mStage.addActor(actor);
    }

    public IvcGame getGame() {
        return mGame;
    }

    public float getPx() {
        return mPx;
    }

    public Skin getSkin() {
        return mSkin;
    }

    public Stage getStage() {
        return mStage;
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
