package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.quadro.games.invokervscrab.IvcGame;
import com.quadro.games.invokervscrab.SL;
import com.quadro.games.invokervscrab.ivc.GameCallback;
import com.quadro.games.invokervscrab.ivc.GameObjectCallback;
import com.quadro.games.invokervscrab.ivc.GameObjectState;
import com.quadro.games.invokervscrab.ivc.IvcProcessor;
import com.quadro.games.invokervscrab.ivc.IvcSounds;
import com.quadro.games.invokervscrab.ivc.effect.EffectItem;
import com.quadro.games.invokervscrab.ivc.effect.worker.BottleEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneExortEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneQuasEffect;
import com.quadro.games.invokervscrab.ivc.effect.worker.RuneWexEffect;
import com.quadro.games.invokervscrab.ivc.enemy.crab.Crab;
import com.quadro.games.invokervscrab.ivc.enemy.crab.CrabCallback;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.CrabDeathView;
import com.quadro.games.invokervscrab.ivc.enemy.crab.view.CrabView;
import com.quadro.games.invokervscrab.ivc.enemy.tower.TowerView;
import com.quadro.games.invokervscrab.ivc.skill.SkillItem;
import com.quadro.games.invokervscrab.ivc.skill.thing.BottleSkill;
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
import com.quadro.games.invokervscrab.screen.UiControl.ExpBar;
import com.quadro.games.invokervscrab.screen.UiControl.HpBar;
import com.quadro.games.invokervscrab.screen.UiControl.MpBar;
import com.quadro.games.invokervscrab.screen.UiControl.SkillButton;
import com.quadro.games.invokervscrab.view.ColorDrawable;
import com.quadro.games.invokervscrab.view.EmptyDrawable;

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
    private TextureAtlas buttonAtlas;

    private final List<ImageButton> mBuffStack = new ArrayList<ImageButton>();

    private TowerView mTowerView;

    private final List<ImageButton> mHintBtns = new ArrayList<ImageButton>();

    private ImageButton mHintPanel;

    private HpBar mHpBar;
    private MpBar mMpBar;
    private ExpBar mExperienceBar;

    private Map<Object, CrabView> mEnemyViews = new HashMap<Object, CrabView>();

    public FightScreen(IvcGame game) {
        super(game);

        // Разрешение
        float viewportWidth = 400 * mPx;
        float viewportHeight = 300 * mPx;

        mStage.setViewport(new FitViewport(viewportWidth, viewportHeight));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        mSkin = new Skin();

        font = new BitmapFont();
        mSkin.add("default", font, BitmapFont.class);

        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/ui/button.pack"));
        mSkin.addRegions(buttonAtlas);

        String[] skillsFiles = new String[] {
                RuneFirstSkill.class.getName(),     "data/skill/quas.png",
                RuneSecondSkill.class.getName(),    "data/skill/wex.png",
                RuneThirdSkill.class.getName(),     "data/skill/exort.png",
                MixSkill.class.getName(),           "data/skill/invoke.png",

                Result111.class.getName(),          "data/skill/cold_snap.png",
                Result112.class.getName(),          "data/skill/ghost_walk.png",
                Result113.class.getName(),          "data/skill/ice_wall.png",

                Result122.class.getName(),          "data/skill/tornado.png",
                Result123.class.getName(),          "data/skill/deafening_blast.png",
                Result133.class.getName(),          "data/skill/chaos_meteor.png",

                Result222.class.getName(),          "data/skill/emp.png",
                Result223.class.getName(),          "data/skill/alacrity.png",
                Result233.class.getName(),          "data/skill/forge_spirit.png",

                Result333.class.getName(),          "data/skill/sun_strike.png",

                // итемы
                BottleSkill.class.getName(),        "data/thing/bottle_3.png",

                // мобы
                "unit/tower",                       "data/units/tower.png",
        };

        for (int i = 0; i < skillsFiles.length; i += 2) {
            String skillName = skillsFiles[i];

            Texture texture = new Texture(Gdx.files.internal(skillsFiles[i + 1]));

            Sprite sprite = new Sprite(texture);
            mSkin.add(skillName, sprite);

            Drawable drawable = new SpriteDrawable(sprite);
            mSkin.add(skillName, drawable, Drawable.class);

            if (!skillName.equals("unit/tower")) {
                SL.getSounds().loadSound(mProcessor.getSkill(skillName).getWorker().getSound());
            }
        }

        String[] uiTextures = new String[] {
                // ui
                "ui-button-down-32",               "data/ui/button32-down.png",
                "ui-button-up-32",                 "data/ui/button32-up.png",

                "ui-button-down-64",                "data/ui/button64-down.png",
                "ui-button-up-64",                  "data/ui/button64-up.png",

                "ui-button-hint-text",              "data/ui/button-hint-text.png",
        };
        for (int i = 0; i < uiTextures.length; i += 2) {
            Texture texture = new Texture(Gdx.files.internal(uiTextures[i + 1]));
            mSkin.add(uiTextures[i], texture);
        }

        String[] drawableSpriteCopy = new String[] {
                // бафы
                BottleEffect.class.getName(),       BottleSkill.class.getName(),
                RuneQuasEffect.class.getName(),     RuneFirstSkill.class.getName(),
                RuneWexEffect.class.getName(),      RuneSecondSkill.class.getName(),
                RuneExortEffect.class.getName(),    RuneThirdSkill.class.getName(),
        };
        for (int i = 0; i < drawableSpriteCopy.length; i += 2) {
            SpriteDrawable dr = (SpriteDrawable)mSkin.getDrawable(drawableSpriteCopy[i + 1]);
            mSkin.add(drawableSpriteCopy[i], new SpriteDrawable(dr), Drawable.class);
        }

        NinePatch patchUp = new NinePatch(
                mSkin.get("ui-button-up-64", Texture.class),
                16, 16, 16, 16
        );

        NinePatch patchDown = new NinePatch(
                mSkin.get("ui-button-down-64", Texture.class),
                16, 16, 16, 16
        );

        mSkin.add(
                "ui-button-up-64",
                new NinePatchDrawable(patchUp),
                Drawable.class
        );
        mSkin.add(
                "ui-button-down-64",
                new NinePatchDrawable(patchDown),
                Drawable.class
        );

        textButtonStyle = new TextButton.TextButtonStyle(
                mSkin.getDrawable("ui-button-up-64"),
                mSkin.getDrawable("ui-button-down-64"),
                null,
                font
        );
//        textButtonStyle.font = font;
//        textButtonStyle.up = mSkin.getDrawable("ui-button-up");
//        textButtonStyle.down = mSkin.getDrawable("ui-button-down");
//        textButtonStyle.checked = skin.getDrawable("checked-button");

        final Drawable transparent = new EmptyDrawable();
        mSkin.add("transparent", transparent, Drawable.class);
        mSkin.add(MixedFirstSkill.class.getName(), transparent, Drawable.class);
        mSkin.add(MixedSecondSkill.class.getName(), transparent, Drawable.class);
        Label.LabelStyle mpCostLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        mpCostLabelStyle.background = new ColorDrawable(Color.BLUE);
        mSkin.add("label-style-mp-cost", mpCostLabelStyle, Label.LabelStyle.class);

        ClickListener skillClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mHintPanel.setVisible(false);
                SkillItem skill = (SkillItem) event.getListenerActor().getUserObject();

                Gdx.app.log(getClass().getName(), "mana cost " + skill.getInfo().getManaCost());

                SL.getGame().useSkill(skill);
            }

        };

        mProcessor.getPlayer().setOnEffectChange(new GameObjectCallback() {

            @Override
            public void run(GameObjectState player) {
                for (int i = 0; i < mBuffStack.size(); i++) {
                    ImageButton btn = mBuffStack.get(i);
                    EffectItem buff = i < player.mEffects.size()
                            ? player.mEffects.get(i)
                            : null;

                    if (buff == null) {
                        btn.setVisible(false);
                        continue;
                    }

                    Drawable drawable = mSkin.getDrawable(buff.getWorkerName());
                    btn.getStyle().imageUp = drawable;
                    btn.setVisible(true);
                }
            }

        });

        // кнопки рун
        new SkillButton(
                this,
                mProcessor.getSkill(RuneFirstSkill.class),
                skillClickListener,
                0, 200
        );

        new SkillButton(
                this,
                mProcessor.getSkill(RuneSecondSkill.class),
                skillClickListener,
                0, 140
        );

        new SkillButton(
                this,
                mProcessor.getSkill(RuneThirdSkill.class),
                skillClickListener,
                0, 80
        );

        // бутылка
        new SkillButton(
                this,
                mProcessor.getSkill(BottleSkill.class),
                skillClickListener,
                55, 200
        );

        // подсказка
        TextButton btnHint = new TextButton("", textButtonStyle);
        initBounds(btnHint, 0, 260, 80, 40);

        // текст на подсказке
        Image imgHintLabel = new Image(mSkin.get("ui-button-hint-text", Texture.class));
        initBounds(imgHintLabel, 10, 10, 60, 20);
        btnHint.addActor(imgHintLabel);

        mStage.addActor(btnHint);
        btnHint.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(getClass().getName(), "hint click");
                String[] hints = mProcessor.getRuneHint();
                for (int i = 0; i < hints.length; i++) {
                    Drawable dr = mSkin.getDrawable(hints[i]);
                    mHintBtns.get(i).getStyle().imageUp = dr;
                }
                mHintPanel.setVisible(true);
            }

        });

        // первый сложный скилл
        final SkillButton sbFirstMixed = new SkillButton(
                this,
                mProcessor.getSkill(MixedFirstSkill.class),
                skillClickListener,
                350,
                200
        );

        // второй сложный скил
        final SkillButton sbSecondMixed = new SkillButton(
                this,
                mProcessor.getSkill(MixedSecondSkill.class),
                skillClickListener,
                350,
                140
        );

        // invoke
        new SkillButton(
                this,
                mProcessor.getSkill(MixSkill.class),
                skillClickListener,
                350, 80
        );

        for (int i = 0; i < 7; i++) {
            ImageButton buff = new ImageButton(transparent);
            initBounds(buff, 60 + i * 25, 80, 20, 20);
            mBuffStack.add(buff);
            mStage.addActor(buff);
        }

        mHintPanel = new ImageButton(new ColorDrawable(Color.BROWN));
        initBounds(mHintPanel, 100, 260, 200, 40);
        mHintPanel.setVisible(false);

        for (int i = 0; i < 3; i++) {
            ImageButton hint = new ImageButton(new ColorDrawable(Color.CORAL));
            initBounds(hint, i * 45, 0, 40, 40);
            mHintBtns.add(hint);
            mHintPanel.addActor(hint);
        }
        mStage.addActor(mHintPanel);

        final AbstractIvcScreen screen = this;

        // Миксованные скилы
        mProcessor.setOnMixedChange(new GameCallback() {

            @Override
            public void run(IvcProcessor game) {
                SkillItem[] mixed = game.getMixedSkills();

                sbFirstMixed.setSkill(screen, mixed[0]);
                sbSecondMixed.setSkill(screen, mixed[1]);
            }

        });

        mProcessor.resetToStart();
        GameObjectState player = mProcessor.getPlayer();
        player.setOnLevelUp(new GameObjectCallback() {

            @Override
            public void run(GameObjectState object) {
                SL.getSounds().play(IvcSounds.LEVEL_UP);
            }

        });

        // прогресс бар хп
        mHpBar = new HpBar(this);

        // прогресс бар маны
        mMpBar = new MpBar(this);

        // прогресс бар опыта
        mExperienceBar = new ExpBar(this);

        SL.getSounds().loadSounds(new String[]{
                IvcSounds.SKILL_USE_FAIL,
                IvcSounds.LEVEL_UP,
        });

        mProcessor.setOnSkillNotEnoughMp(new GameCallback() {

            @Override
            public void run(IvcProcessor game) {
                SL.getSounds().play(IvcSounds.SKILL_USE_FAIL);
            }

        });

        mTowerView = new TowerView(this);

        mProcessor.setOnCrabCreate(new CrabCallback() {

            @Override
            public void run(Crab crab) {
                CrabView view = new CrabView(crab);
                Drawable question = mSkin.getDrawable(crab.getQuestion());
                view.setQuestion(question);
                view.randomize();
                addStageBounds(view, 200, 150, 100, 100);
                mEnemyViews.put(crab, view);
            }

        });

        mProcessor.setOnCrabDeath(new CrabCallback() {

            @Override
            public void run(Crab crab) {
                IvcProcessor game = SL.getGame();
                game.getPlayer().incExperience(game.getLeveling().getParams(1).getExpForUnit() * 5);

                CrabView liveView = mEnemyViews.remove(crab);
                CrabDeathView deathView = new CrabDeathView();
                deathView.assignFrom(liveView);
            }

        });

        mProcessor.randomizeQuestion();
//        mStage.setDebugAll(true);
    }

    /**
     * Установка размеров актора с учетом разрешения
     * @param actor
     * @param left
     * @param bottom
     * @param width
     * @param height
     */
    private void initBounds(Actor actor, float left, float bottom, float width, float height) {
        actor.setBounds(left * mPx, bottom * mPx, width * mPx, height * mPx);
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
        mProcessor.update(delta);

        GameObjectState player = mProcessor.getPlayer();
        mHpBar.update(player, delta);
        mMpBar.update(player, delta);
        mExperienceBar.update(player);
    }

}
