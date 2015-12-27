package com.quadro.games.invokervscrab.ivc.enemy.crab.view.part;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Body extends AbstractPart {

    public Body() {
        setWidth(100);
        setHeight(50);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        applyTransform(batch);

        batch.setColor(Color.RED);
        float[] vertex = ellipse(0, 0, getWidth(), getHeight());
        batch.draw(mTexture, vertex, 0, vertex.length);

        resetTransform(batch);
    }

}
