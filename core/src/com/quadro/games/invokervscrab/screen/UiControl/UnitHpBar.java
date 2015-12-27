package com.quadro.games.invokervscrab.screen.UiControl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.view.ColorDrawable;

/**
 * Полоска HP юнитов
 */
public class UnitHpBar extends Actor {

    private float mHeight = 10;

    private GameObjectState mModel;

    private Button mBackground = new Button();

    private Button mValue = new Button();

    public UnitHpBar(GameObjectState model, Group view) {
        mModel = model;

        mBackground.setBackground(new ColorDrawable(Color.BLACK));
        mValue.setBackground(new ColorDrawable(Color.RED));

        mBackground.setBounds(0, view.getHeight() - mHeight, view.getWidth(), mHeight);
        mValue.setBounds(1, 1, mBackground.getWidth() - 2, mBackground.getHeight() - 2);
        mBackground.add(mValue);

        view.addActor(mBackground);
    }

    @Override
    public void act(float delta) {
        float maxWidth = mBackground.getWidth() - 2;
        mValue.setWidth(maxWidth * mModel.mCurrentHp / mModel.mMaxHp);
    }

}
