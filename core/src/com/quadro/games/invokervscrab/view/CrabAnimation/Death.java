package com.quadro.games.invokervscrab.view.CrabAnimation;

import com.badlogic.gdx.math.Matrix4;
import com.quadro.games.invokervscrab.ivc.mob.Crab;
import com.quadro.games.invokervscrab.view.CrabContext;
import com.quadro.games.invokervscrab.view.CrabPart.AbstractPart;
import com.quadro.games.invokervscrab.view.CrabPart.Body;

/**
 * Created by Quadrowin on 05.12.2015.
 */
public class Death extends AbstractAnimation {

    private float mTime = 9999;

    private final Matrix4 mTransformMatrixBuffer = new Matrix4();

    private final Matrix4 mRenderTransformMatrix = new Matrix4();

    @Override
    public void draw(CrabContext context, Crab crab) {
        if (mTime > 10) {
            return;
        }
        mTime += context.delta;
        context.shapeRenderer.setProjectionMatrix(context.projection);
        context.shapeRenderer.setTransformMatrix(mRenderTransformMatrix);
        context.shapeRenderer.translate(context.left, context.bottom, 0);
        mTransformMatrixBuffer.set(context.shapeRenderer.getTransformMatrix());
//        Matrix4 startPartPr = mRenderer.getProjectionMatrix().cpy();
        float attackAnimationFrame = crab.getAttackFrame();
        AbstractPart[] parts = context.parts;
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            context.shapeRenderer.translate(p.getX() * (1 + mTime), p.getY() * (1 + mTime), 0);
            if (p instanceof Body) {
                context.shapeRenderer.scale(p.getScale() / (1 + mTime), p.getScale() / (1 + mTime), 1);
            } else {
                context.shapeRenderer.scale(p.getScale(), p.getScale(), 1);
            }
            context.shapeRenderer.rotate(0, 0, 1, mTime * (i % 3 + 1) * 50);
            p.draw(context.shapeRenderer);
            context.shapeRenderer.setTransformMatrix(mTransformMatrixBuffer);
//            mRenderer.setProjectionMatrix(startPartPr);
        }
    }

    @Override
    public void restart() {
        mTime = 0;
    }

}
