package com.quadro.games.invokervscrab.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Quadrowin on 24.11.2015.
 */
abstract public class AbstractPart extends Actor {

    final private Matrix4 mOriginalTransform = new Matrix4();

    final private Matrix4 mComputedTransform = new Matrix4();

    protected float[] mRandom;

    protected Texture mTexture;

    protected float mWhiteColor = Color.WHITE.toFloatBits();

    public AbstractPart () {
        mRandom = new float[] {0, 0};
    }

    protected void applyTransform(Batch batch)
    {
        mOriginalTransform.set(batch.getTransformMatrix());
        mComputedTransform.set(mOriginalTransform);
        mComputedTransform.translate(getX(), getY(), 0);
        mComputedTransform.rotate(Vector3.Z, getRotation());
        mComputedTransform.scale(getScaleX(), getScaleY(), 1);
        batch.setTransformMatrix(mComputedTransform);
    }

    protected float[] arc (float x, float y, float radius, float start, float degrees) {
        return arc(x, y, radius, start, degrees, Math.max(1, (int)(6 * (float)Math.cbrt(radius) * (degrees / 360.0f))));
    }

    protected float[] arc (float x, float y, float radius, float start, float degrees, int segments) {
        if (segments <= 0) throw new IllegalArgumentException("segments must be > 0.");
        float colorBits = getColor().toFloatBits();
        float theta = (2 * MathUtils.PI * (degrees / 360.0f)) / segments;
        float cos = MathUtils.cos(theta);
        float sin = MathUtils.sin(theta);
        float cx = radius * MathUtils.cos(start * MathUtils.degreesToRadians);
        float cy = radius * MathUtils.sin(start * MathUtils.degreesToRadians);

        float[] vertex = new float[segments * 4 * 5];
        int index = 0;

        for (int i = 0; i < segments; i++) {
            vertex[index++] = x;
            vertex[index++] = y;
            vertex[index++] = colorBits;
            vertex[index++] = 0;
            vertex[index++] = 0;

            vertex[index++] = x + cx;
            vertex[index++] = y + cy;
            vertex[index++] = colorBits;
            vertex[index++] = 0;
            vertex[index++] = 1;

            float temp = cx;
            cx = cos * cx - sin * cy;
            cy = sin * temp + cos * cy;

            vertex[index++] = x + cx;
            vertex[index++] = y + cy;
            vertex[index++] = colorBits;
            vertex[index++] = 1;
            vertex[index++] = 1;

            vertex[index++] = x;
            vertex[index++] = y;
            vertex[index++] = colorBits;
            vertex[index++] = 0;
            vertex[index++] = 0;
        }

        return vertex;
    }

    protected float[] ellipse (float x, float y, float width, float height) {
        return ellipse(x, y, width, height, Math.max(1, (int)(12 * (float)Math.cbrt(Math.max(width * 0.5f, height * 0.5f)))));
    }

    protected float[] ellipse (float x, float y, float width, float height, int segments) {
        if (segments <= 0) throw new IllegalArgumentException("segments must be > 0.");

        float angle = 2 * MathUtils.PI / segments;
        float half_width = width * 0.5f;
        float half_height = height * 0.5f;
        float cx = x + half_width;
        float cy = y + half_height;
        float colorBits = getColor().toFloatBits();

        float[] vertex = new float[segments * 2 * 5];
        int index = 0;
        for (int i = 0; i < segments; i += 2) {
            // x, y, color, u, v
            vertex[index++] = cx;
            vertex[index++] = cy;
            vertex[index++] = colorBits;
            vertex[index++] = 0;
            vertex[index++] = 0;

            vertex[index++] = cx + (half_width * MathUtils.cos(i * angle));
            vertex[index++] = cy + (half_height * MathUtils.sin(i * angle));
            vertex[index++] = colorBits;
            vertex[index++] = 0;
            vertex[index++] = 1;

            vertex[index++] = cx + (half_width * MathUtils.cos((i + 1) * angle));
            vertex[index++] = cy + (half_height * MathUtils.sin((i + 1) * angle));
            vertex[index++] = colorBits;
            vertex[index++] = 1;
            vertex[index++] = 1;

            vertex[index++] = cx + (half_width * MathUtils.cos((i + 2) * angle));
            vertex[index++] = cy + (half_height * MathUtils.sin((i + 2) * angle));
            vertex[index++] = colorBits;
            vertex[index++] = 1;
            vertex[index++] = 0;
        }

        return vertex;
    }


    public void randomize() {
        mRandom[0] = (float)Math.random();
        mRandom[1] = (float)Math.random();
    }

    protected void resetTransform(Batch batch)
    {
        batch.setTransformMatrix(mOriginalTransform);
    }

    public void setTexture(Texture t) {
        mTexture = t;
    }

}
