package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.quadro.games.invokervscrab.view.AbstractPart;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Eye extends AbstractPart {

    private Texture mTexturePupil;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        float radiusInner = getWidth() / 2;

        batch.setColor(Color.WHITE);
        batch.draw(
                mTexture,
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
        batch.draw(
                mTexturePupil,
                getX() + mRandom[0] * radiusInner,
                getY() + mRandom[1] * radiusInner,
                radiusInner,
                radiusInner
        );
    }

    public void setTexturePupil(Texture t) {
        mTexturePupil = t;
    }

}
