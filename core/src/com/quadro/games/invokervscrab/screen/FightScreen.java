package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadro.games.invokervscrab.IvcGame;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.BuffSlot;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneFirstSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneSecondSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneThirdSkill;
import com.quadro.games.invokervscrab.style.EmptyDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public class FightScreen extends AbstractIvcScreen {

    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;

    private final List<ImageButton> mBuffStack = new ArrayList<ImageButton>();

    private final Map<String, ImageButton> mSkillToButton = new HashMap<String, ImageButton>();

    public FightScreen(IvcGame game) {
        super(game);

        mStage.setViewport(new FitViewport(400, 300));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/ui/button.pack"));
        skin.addRegions(buttonAtlas);

        skin.add("skill.quas", new Sprite(new Texture(Gdx.files.internal("data/skill/quas.png"))));
        skin.add("skill.wex", new Sprite(new Texture(Gdx.files.internal("data/skill/wex.png"))));
        skin.add("skill.exort", new Sprite(new Texture(Gdx.files.internal("data/skill/exort.png"))));

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
//        textButtonStyle.checked = skin.getDrawable("checked-button");

        Drawable quasUp = new SpriteDrawable(skin.getSprite("skill.quas"));
        Drawable quasWex = new SpriteDrawable(skin.getSprite("skill.wex"));
        Drawable quasExort = new SpriteDrawable(skin.getSprite("skill.exort"));

        ClickListener skillClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                SkillItem skill = (SkillItem) event.getListenerActor().getUserObject();
                skill.use(SL.getGame());

                for (int i = 0; i < mBuffStack.size(); i++) {
                    ImageButton btn = mBuffStack.get(i);
                    BuffSlot buff = SL.getGame().getBuff(i);

                    if (buff == null) {
                        btn.setVisible(false);
                        continue;
                    }

                    ImageButton btnSourceSkill = mSkillToButton.get(buff.getSourceSkill().getWorkerName());
                    btn.setStyle(btnSourceSkill.getStyle());
                    btn.setVisible(true);
                }

//                updateQuestion();
            }

        };

        ImageButton btnQuas = new ImageButton(quasUp, quasUp);
        btnQuas.setBounds(0, 200, 50, 50);

        SkillItem si = SL.getGame().getSkill(RuneFirstSkill.class.getName());
        if (si == null) {
            throw new RuntimeException("not found!");
        }

        btnQuas.setUserObject(SL.getGame().getSkill(RuneFirstSkill.class.getName()));
        btnQuas.addListener(skillClickListener);
        mStage.addActor(btnQuas);

        ImageButton btnWex = new ImageButton(quasWex);
        btnWex.setBounds(0, 140, 50, 50);
        btnWex.setUserObject(SL.getGame().getSkill(RuneSecondSkill.class.getName()));
        btnWex.addListener(skillClickListener);
        mStage.addActor(btnWex);

        ImageButton btnExort = new ImageButton(quasExort);
        btnExort.setBounds(0, 80, 50, 50);
        btnExort.setUserObject(SL.getGame().getSkill(RuneThirdSkill.class.getName()));
        btnExort.addListener(skillClickListener);
        mStage.addActor(btnExort);

        TextButton btnHint = new TextButton("Hint", textButtonStyle);
        btnHint.setBounds(0, 260, 80, 40);
        mStage.addActor(btnHint);

        mSkillToButton.put(RuneFirstSkill.class.getName(), btnQuas);
        mSkillToButton.put(RuneSecondSkill.class.getName(), btnWex);
        mSkillToButton.put(RuneThirdSkill.class.getName(), btnExort);

        Drawable transparent = new EmptyDrawable();

        for (int i = 0; i < 3; i++) {
            ImageButton buff = new ImageButton(transparent);
            buff.setBounds(60 + i * 25, 80, 20, 20);
            mBuffStack.add(buff);
            mStage.addActor(buff);
        }
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {

    }

}
