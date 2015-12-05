package com.quadro.games.invokervscrab.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.quadro.games.invokervscrab.view.CrabPart.AbstractPart;
import com.quadro.games.invokervscrab.view.CrabPart.Body;

/**
 * Created by Quadrowin on 05.12.2015.
 */
public class CrabContext {

    public Batch batch;

    public Body body;

    public float delta;

    public AbstractPart[] parts;

    public Matrix4 projection;

    public ShapeRenderer shapeRenderer;

    public float left;

    public float bottom;

    public Texture questionTexture;

}
