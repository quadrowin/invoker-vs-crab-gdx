package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Body extends AbstractPart {

    public Body() {
        mSizeX = 100;
        mSizeY = 50;
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1, 0, 0, 1);
        sr.ellipse(
                -0.5f * mSizeX,
                -0.5f * mSizeY,
                mSizeX,
                mSizeY
        );
        sr.end();
    }

}
