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
import com.quadro.games.invokervscrab.ivc.GameCallback;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixedFirstSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.MixedSecondSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneFirstSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneSecondSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.RuneThirdSkill;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result111;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result112;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result113;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result122;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result123;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result133;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result222;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result223;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result233;
import com.quadro.games.invokervscrab.ivc.skill.worker.mixed.Result333;
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
    private Skin mSkin;
    private TextureAtlas buttonAtlas;

    private final List<ImageButton> mBuffStack = new ArrayList<ImageButton>();

    private final Map<String, ImageButton> mSkillToButton = new HashMap<String, ImageButton>();

    public FightScreen(IvcGame game) {
        super(game);

        mStage.setViewport(new FitViewport(400, 300));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        font = new BitmapFont();
        mSkin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/ui/button.pack"));
        mSkin.addRegions(buttonAtlas);

        mSkin.add(RuneFirstSkill.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/quas.png"))));
        mSkin.add(RuneSecondSkill.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/wex.png"))));
        mSkin.add(RuneThirdSkill.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/exort.png"))));
        mSkin.add(MixSkill.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/invoke.png"))));

        mSkin.add(Result111.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/cold_snap.png"))));
        mSkin.add(Result112.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/ghost_walk.png"))));
        mSkin.add(Result113.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/ice_wall.png"))));

        mSkin.add(Result122.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/tornado.png"))));
        mSkin.add(Result123.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/deafening_blast.png"))));
        mSkin.add(Result133.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/chaos_meteor.png"))));

        mSkin.add(Result222.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/emp.png"))));
        mSkin.add(Result223.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/alacrity.png"))));
        mSkin.add(Result233.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/forge_spirit.png"))));

        mSkin.add(Result333.class.getName(), new Sprite(new Texture(Gdx.files.internal("data/skill/sun_strike.png"))));

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = mSkin.getDrawable("button-up");
        textButtonStyle.down = mSkin.getDrawable("button-down");
//        textButtonStyle.checked = skin.getDrawable("checked-button");

        Drawable drawUp = new SpriteDrawable(mSkin.getSprite(RuneFirstSkill.class.getName()));
        Drawable drawWex = new SpriteDrawable(mSkin.getSprite(RuneSecondSkill.class.getName()));
        Drawable drawExort = new SpriteDrawable(mSkin.getSprite(RuneThirdSkill.class.getName()));
        Drawable drawMix = new SpriteDrawable(mSkin.getSprite(MixSkill.class.getName()));

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

        ImageButton btnQuas = new ImageButton(drawUp, drawUp);
        btnQuas.setBounds(0, 200, 50, 50);
        mSkillToButton.put(RuneFirstSkill.class.getName(), btnQuas);

        ImageButton btnWex = new ImageButton(drawWex);
        btnWex.setBounds(0, 140, 50, 50);
        mSkillToButton.put(RuneSecondSkill.class.getName(), btnWex);

        ImageButton btnExort = new ImageButton(drawExort);
        btnExort.setBounds(0, 80, 50, 50);
        mSkillToButton.put(RuneThirdSkill.class.getName(), btnExort);

        TextButton btnHint = new TextButton("Hint", textButtonStyle);
        btnHint.setBounds(0, 260, 80, 40);
        mStage.addActor(btnHint);

        final Drawable transparent = new EmptyDrawable();

        ImageButton btnFirstMixed = new ImageButton(transparent);
        btnFirstMixed.setBounds(350, 200, 50, 50);
        mSkillToButton.put(MixedFirstSkill.class.getName(), btnFirstMixed);

        ImageButton btnSecondMixed = new ImageButton(transparent);
        btnSecondMixed.setBounds(350, 140, 50, 50);
        mSkillToButton.put(MixedSecondSkill.class.getName(), btnSecondMixed);

        ImageButton btnInvoke = new ImageButton(drawMix);
        btnInvoke.setBounds(350, 80, 50, 50);
        mSkillToButton.put(MixSkill.class.getName(), btnInvoke);

        for (Map.Entry<String, ImageButton> entry : mSkillToButton.entrySet()) {
            String skillClass = entry.getKey();
            ImageButton btn = entry.getValue();
            btn.setUserObject(SL.getGame().getSkill(skillClass));
            btn.addListener(skillClickListener);
            mStage.addActor(btn);
        }

        for (int i = 0; i < 3; i++) {
            ImageButton buff = new ImageButton(transparent);
            buff.setBounds(60 + i * 25, 80, 20, 20);
            mBuffStack.add(buff);
            mStage.addActor(buff);
        }

        // Миксованные скилы
        SL.getGame().setOnMixedChange(new GameCallback() {

            @Override
            public void run(IvcProcessor game) {
                SkillItem[] mixed = game.getMixedSkills();

                ImageButton btn1 = mSkillToButton.get(MixedFirstSkill.class.getName());
                if (mixed[0] == null) {
                    btn1.getStyle().imageUp = transparent;
                } else {
                    Drawable dr = new SpriteDrawable(mSkin.getSprite(mixed[0].getWorkerName()));
                    btn1.getStyle().imageUp = dr;
                }

                ImageButton btn2 = mSkillToButton.get(MixedSecondSkill.class.getName());
                if (mixed[1] == null) {
                    btn2.getStyle().imageUp = transparent;
                } else {
                    Drawable dr = new SpriteDrawable(mSkin.getSprite(mixed[1].getWorkerName()));
                    btn2.getStyle().imageUp = dr;
                }

            }

        });
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
