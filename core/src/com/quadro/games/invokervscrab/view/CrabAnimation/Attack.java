package com.quadro.games.invokervscrab.view.CrabAnimation;

import com.badlogic.gdx.math.Matrix4;
import com.quadro.games.invokervscrab.ivc.mob.Crab;
import com.quadro.games.invokervscrab.view.CrabPart.AbstractPart;
import com.quadro.games.invokervscrab.view.CrabPart.Hand;
import com.quadro.games.invokervscrab.view.CrabContext;

/**
 * Created by Quadrowin on 05.12.2015.
 */
public class Attack extends AbstractAnimation {

    private final Matrix4 mTransformMatrixBuffer = new Matrix4();

    private final Matrix4 mRenderTransformMatrix = new Matrix4();

    @Override
    public void draw(CrabContext context, Crab crab) {
        context.shapeRenderer.setProjectionMatrix(context.projection);
        context.shapeRenderer.setTransformMatrix(mRenderTransformMatrix);
        context.shapeRenderer.translate(context.left, context.bottom, 0);
        mTransformMatrixBuffer.set(context.shapeRenderer.getTransformMatrix());
//        Matrix4 startPartPr = mRenderer.getProjectionMatrix().cpy();
        float attackAnimationFrame = crab.getAttackFrame();
        AbstractPart[] parts = context.parts;
        for (int i = 0; i < parts.length; i++) {
            AbstractPart p = parts[i];
            context.shapeRenderer.translate(p.getX(), p.getY(), 0);
            context.shapeRenderer.scale(p.getScale(), p.getScale(), 1);
            if (p instanceof Hand) {
                ((Hand)p).setAttackFrame( attackAnimationFrame );
            }
            p.draw(context.shapeRenderer);
            context.shapeRenderer.setTransformMatrix(mTransformMatrixBuffer);
//            mRenderer.setProjectionMatrix(startPartPr);
        }

        if (context.questionTexture != null) {
            context.batch.begin();
            context.batch.setProjectionMatrix(context.projection);
            float w = context.body.getWidth();
            float h = context.body.getHeight();
            float size = h * 0.7f;
            context.batch.draw(
                    context.questionTexture,
                    context.left + context.body.getLeft() + w / 2 - size / 2,
                    context.bottom + context.body.getBottom() + h / 2 - size / 2,
                    size,
                    size
            );
            context.batch.end();
        }
    }

    public void restart() {

    }

}
