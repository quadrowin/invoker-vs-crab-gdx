package com.quadro.games.invokervscrab.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Quadrowin on 29.11.2015.
 */
public class ColorDrawable implements Drawable {

    private Color mColor;

    private static final ShapeRenderer mRenderer = new ShapeRenderer();

    private static Matrix4 mOriginalProjectionMatrix;

    private static Matrix4 mOriginalTransformMatrix;

    public ColorDrawable(Color color) {
        mColor = color;
        if (mOriginalProjectionMatrix == null) {
            mOriginalProjectionMatrix = mRenderer.getProjectionMatrix();
            mOriginalTransformMatrix = mRenderer.getTransformMatrix();
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.end();
        mRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        mRenderer.setTransformMatrix(batch.getTransformMatrix());

        mRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mRenderer.rect(x, y, width, height, mColor, mColor, mColor, mColor);
        mRenderer.end();

        batch.begin();
//        mRenderer.setProjectionMatrix(mOriginalProjectionMatrix);
//        mRenderer.setTransformMatrix(mOriginalTransformMatrix);
    }

    @Override
    public float getLeftWidth() {
        return 0;
    }

    @Override
    public void setLeftWidth(float leftWidth) {

    }

    @Override
    public float getRightWidth() {
        return 0;
    }

    @Override
    public void setRightWidth(float rightWidth) {

    }

    @Override
    public float getTopHeight() {
        return 0;
    }

    @Override
    public void setTopHeight(float topHeight) {

    }

    @Override
    public float getBottomHeight() {
        return 0;
    }

    @Override
    public void setBottomHeight(float bottomHeight) {

    }

    @Override
    public float getMinWidth() {
        return 0;
    }

    @Override
    public void setMinWidth(float minWidth) {

    }

    @Override
    public float getMinHeight() {
        return 0;
    }

    @Override
    public void setMinHeight(float minHeight) {

    }
}
