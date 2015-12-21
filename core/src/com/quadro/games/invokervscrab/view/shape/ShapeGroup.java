package com.quadro.games.invokervscrab.view.shape;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quadrowin on 19.12.2015.
 */
public class ShapeGroup extends WidgetGroup {

    public ShapeRenderer mShapeRenderer;

    private final Matrix4 mBatchProjectionMatrix = new Matrix4();
    private final Matrix4 mBatchTransformMatrix = new Matrix4();

    private final List<AbstractShape> mShapes = new ArrayList<AbstractShape>();

    private final Matrix4 mTransformMatrixBuffer = new Matrix4();

    public ShapeGroup() {
        mShapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        for (int i = 0; i < mShapes.size(); i++) {
            mShapes.get(i).act(delta);
        }
    }

    public void addShape(AbstractShape shape) {
        mShapes.add(shape);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        mBatchProjectionMatrix.set(batch.getProjectionMatrix());// stage.getCamera().projection;
        mBatchTransformMatrix.set(batch.getTransformMatrix());
        super.draw(batch, parentAlpha);

        ShapeRenderer renderer = mShapeRenderer;
        renderer.setProjectionMatrix(mBatchProjectionMatrix);
        renderer.setTransformMatrix(mBatchTransformMatrix);
        renderer.translate(getX(), getY(), 0);
        mTransformMatrixBuffer.set(renderer.getTransformMatrix());
//        Matrix4 startPartPr = mRenderer.getProjectionMatrix().cpy();

        batch.end();

        for (int i = 0; i < mShapes.size(); i++) {
            AbstractShape shape = mShapes.get(i);
            renderer.translate(shape.getX(), shape.getY(), 0);
            renderer.rotate(0, 0, 1, shape.getRotation());
            renderer.scale(shape.getScaleX(), shape.getScaleY(), 1);
            shape.draw(renderer);
            renderer.setProjectionMatrix(mBatchProjectionMatrix);
            renderer.setTransformMatrix(mTransformMatrixBuffer);
//            mRenderer.setProjectionMatrix(startPartPr);
        }
        batch.begin();

    }

}
