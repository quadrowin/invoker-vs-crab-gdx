package com.quadro.games.invokervscrab.mob;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Quadrowin on 24.11.2015.
 */
public class Crab {

    private Part[] parts;

    public Crab() {

        parts = new Part[] {
                new Eye(-40, 50, 0.5f),
                new Eye(40, 50, 0.4f),
                new Body(0, 0, 1.5f),

                new Hand(-100, 50, 0.80f, false),
                new Hand(-100, 50, 1.00f, true),

                new Foot(-05, -25, 0.50f, false),
                new Foot(-30, -35, 0.60f, false),
                new Foot(-50, -30, 0.75f, false),

                new Foot(+05, -25, 0.5f, true),
                new Foot(+30, -35, 0.60f, true),
                new Foot(+50, -30, 0.75f, true),
        };
    }

    public void draw(Matrix4 m) {
        ShapeRenderer sr = new ShapeRenderer();
        sr.setProjectionMatrix(m);
        sr.translate(0, 20, 0);
        for (int i = 0; i < parts.length; i++) {
            Part p = parts[i];
            sr.translate(p.getX(), p.getY(), 0);
            p.draw(sr);
            sr.translate(-p.getX(), -p.getY(), 0);
        }
    }

}
