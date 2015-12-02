package com.quadro.games.invokervscrab.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
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
import com.quadro.games.invokervscrab.ivc.mob.Crab;
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
import com.quadro.games.invokervscrab.style.ColorDrawable;
import com.quadro.games.invokervscrab.style.EmptyDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Quadrowin on 27.11.2015.
 */
public class FightScreen extends AbstractIvcScreen {

    private Crab mEnemy = new Crab();

    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin mSkin;
    private TextureAtlas buttonAtlas;

    private final List<ImageButton> mBuffStack = new ArrayList<ImageButton>();

    private final List<ImageButton> mHintBtns = new ArrayList<ImageButton>();

    private ImageButton mHintPanel;

    private final Map<String, ImageButton> mSkillToButton = new HashMap<String, ImageButton>();

    private ProgressBar mProgressHp;

    private ProgressBar mProgressMp;

    private ProgressBar mProgressExp;

    private Label mLabelCurrentHp;

    private Label mLabelRegenHp;

    private Label mLabelCurrentMp;

    private Label mLabelRegenMp;

    private Label mLabelFirstMixedMpCost;

    private Label mLabelSecondMixedMpCost;

    public FightScreen(IvcGame game) {
        super(game);

        int viewportWidth = 400;
        int viewportHeight = 300;

        mStage.setViewport(new FitViewport(viewportWidth, viewportHeight));
        mStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        font = new BitmapFont();
        mSkin = new Skin();
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
        };

        for (int i = 0; i < skillsFiles.length; i += 2) {
            String skillName = skillsFiles[i];

            Texture texture = new Texture(Gdx.files.internal(skillsFiles[i + 1]));

            Sprite sprite = new Sprite(texture);
            mSkin.add(skillName, sprite);

            Drawable drawable = new SpriteDrawable(sprite);
            mSkin.add(skillName, drawable, Drawable.class);
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

        mSkin.getDrawable( RuneQuasEffect.class.getName() );

        Drawable drawQuas = mSkin.getDrawable(RuneFirstSkill.class.getName());
        Drawable drawWex = mSkin.getDrawable(RuneSecondSkill.class.getName());
        Drawable drawExort = mSkin.getDrawable(RuneThirdSkill.class.getName());
        Drawable drawMix = mSkin.getDrawable(MixSkill.class.getName());

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = mSkin.getDrawable("button-up");
        textButtonStyle.down = mSkin.getDrawable("button-down");
//        textButtonStyle.checked = skin.getDrawable("checked-button");

        ClickListener skillClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mHintPanel.setVisible(false);
                SkillItem skill = (SkillItem) event.getListenerActor().getUserObject();

                Gdx.app.log(getClass().getName(), "mana cost " + skill.getInfo().getManaCost());

                skill.use(SL.getGame());

                updateQuestion();
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
        ImageButton btnQuas = new ImageButton(drawQuas);
        btnQuas.setBounds(0, 200, 50, 50);
        mSkillToButton.put(RuneFirstSkill.class.getName(), btnQuas);

        ImageButton btnWex = new ImageButton(drawWex);
        btnWex.setBounds(0, 140, 50, 50);
        mSkillToButton.put(RuneSecondSkill.class.getName(), btnWex);

        ImageButton btnExort = new ImageButton(drawExort);
        btnExort.setBounds(0, 80, 50, 50);
        mSkillToButton.put(RuneThirdSkill.class.getName(), btnExort);

        // бутылка
        ImageButton btnBottle = new ImageButton(mSkin.getDrawable(BottleSkill.class.getName()));
        btnBottle.setBounds(55, 200, 50, 50);
        mSkillToButton.put(BottleSkill.class.getName(), btnBottle);

        // подсказка
        TextButton btnHint = new TextButton("Hint", textButtonStyle);
        btnHint.setBounds(0, 260, 80, 40);
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

        final Drawable transparent = new EmptyDrawable();
        Label.LabelStyle mpCostLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        mpCostLabelStyle.background = new ColorDrawable(Color.BLUE);

        // первый сложный скилл
        ImageButton btnFirstMixed = new ImageButton(transparent);
        btnFirstMixed.setBounds(350, 200, 50, 50);
        mSkillToButton.put(MixedFirstSkill.class.getName(), btnFirstMixed);
        // мана кост первого сложного скила
        mLabelFirstMixedMpCost = newMpCostLabel(0, mpCostLabelStyle, btnFirstMixed);

        // второй сложный скил
        ImageButton btnSecondMixed = new ImageButton(transparent);
        btnSecondMixed.setBounds(350, 140, 50, 50);
        mSkillToButton.put(MixedSecondSkill.class.getName(), btnSecondMixed);
        // мана кост второго сложного скила
        mLabelSecondMixedMpCost = newMpCostLabel(0, mpCostLabelStyle, btnSecondMixed);

        ImageButton btnInvoke = new ImageButton(drawMix);
        btnInvoke.setBounds(350, 80, 50, 50);
        mSkillToButton.put(MixSkill.class.getName(), btnInvoke);
        SkillItem invoke = mProcessor.getSkill(MixSkill.class.getName());

        newMpCostLabel(invoke.getInfo().getManaCost(), mpCostLabelStyle, btnInvoke);

        for (Map.Entry<String, ImageButton> entry : mSkillToButton.entrySet()) {
            String skillClass = entry.getKey();
            ImageButton btn = entry.getValue();
            btn.setUserObject(mProcessor.getSkill(skillClass));
            btn.addListener(skillClickListener);
            mStage.addActor(btn);
        }

        for (int i = 0; i < 7; i++) {
            ImageButton buff = new ImageButton(transparent);
            buff.setBounds(60 + i * 25, 80, 20, 20);
            mBuffStack.add(buff);
            mStage.addActor(buff);
        }

        mHintPanel = new ImageButton(new ColorDrawable(Color.BROWN));
        mHintPanel.setBounds(100, 260, 200, 40);
        mHintPanel.setVisible(false);

        for (int i = 0; i < 3; i++) {
            ImageButton hint = new ImageButton(new ColorDrawable(Color.CORAL));
            hint.setBounds(i * 45, 0, 40, 40);
            mHintBtns.add(hint);
            mHintPanel.addActor(hint);
        }
        mStage.addActor(mHintPanel);

        // Миксованные скилы
        mProcessor.setOnMixedChange(new GameCallback() {

            @Override
            public void run(IvcProcessor game) {
                SkillItem[] mixed = game.getMixedSkills();

                ImageButton btn1 = mSkillToButton.get(MixedFirstSkill.class.getName());
                if (mixed[0] == null) {
                    btn1.getStyle().imageUp = transparent;
                    mLabelFirstMixedMpCost.setVisible(false);
                } else {
                    Drawable dr = mSkin.getDrawable(mixed[0].getWorkerName());
                    btn1.getStyle().imageUp = dr;
                    mLabelFirstMixedMpCost.setText(
                            Integer.toString((int) mixed[0].getInfo().getManaCost())
                    );
                    mLabelFirstMixedMpCost.setVisible(true);
                }

                ImageButton btn2 = mSkillToButton.get(MixedSecondSkill.class.getName());
                if (mixed[1] == null) {
                    btn2.getStyle().imageUp = transparent;
                    mLabelSecondMixedMpCost.setVisible(false);
                } else {
                    Drawable dr = mSkin.getDrawable(mixed[1].getWorkerName());
                    btn2.getStyle().imageUp = dr;
                    mLabelSecondMixedMpCost.setText(
                            Integer.toString((int)mixed[1].getInfo().getManaCost())
                    );
                    mLabelSecondMixedMpCost.setVisible(true);
                }

            }

        });

        mProcessor.resetToStart();
        GameObjectState player = mProcessor.getPlayer();

        // прогресс бар хп
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
                mSkin.newDrawable("button-up", Color.DARK_GRAY),
                mSkin.newDrawable("button-up", Color.RED)
        );
        barStyle.knobBefore = barStyle.knob;

        mProgressHp = new ProgressBar(0, player.mMaxHp, 1, false, barStyle);
        mProgressHp.setBounds(0, 40, viewportWidth, 20);
        mStage.addActor(mProgressHp);

        // Цифры на хп
        Label.LabelStyle hpLabelStyle = new Label.LabelStyle(font, Color.WHITE);
        mLabelCurrentHp = new Label("100/100", hpLabelStyle);
        mLabelCurrentHp.setAlignment(Align.center, Align.center);
        mLabelCurrentHp.setBounds(
                mProgressHp.getX(),
                mProgressHp.getY(),
                mProgressHp.getWidth(),
                mProgressHp.getHeight()
        );
        mLabelCurrentHp.setFontScale(0.4f);
        mStage.addActor(mLabelCurrentHp);

        // Цифры реген хп
        mLabelRegenHp = new Label("+0.3", hpLabelStyle);
        mLabelRegenHp.setAlignment(Align.center, Align.right);
        mLabelRegenHp.setBounds(
                mProgressHp.getX(),
                mProgressHp.getY(),
                mProgressHp.getWidth(),
                mProgressHp.getHeight()
        );
        mLabelRegenHp.setFontScale(0.4f);
        mStage.addActor(mLabelRegenHp);

        // прогресс бар маны
        barStyle = new ProgressBar.ProgressBarStyle(
                mSkin.newDrawable("button-up", Color.DARK_GRAY),
                mSkin.newDrawable("button-up", Color.BLUE)
        );
        barStyle.knobBefore = barStyle.knob;

        mProgressMp = new ProgressBar(0, player.mMaxMp, 1, false, barStyle);
        mProgressMp.setBounds(0, 20, viewportWidth, 20);
        mStage.addActor(mProgressMp);

        barStyle = new ProgressBar.ProgressBarStyle(
                mSkin.newDrawable("button-down", Color.DARK_GRAY),
                mSkin.newDrawable("button-down", Color.GREEN)
        );
        barStyle.knobBefore = barStyle.knob;

        // Цифры на мп
        mLabelCurrentMp = new Label("100/100", hpLabelStyle);
        mLabelCurrentMp.setAlignment(Align.center, Align.center);
        mLabelCurrentMp.setBounds(
                mProgressMp.getX(),
                mProgressMp.getY(),
                mProgressMp.getWidth() * 0.99f,
                mProgressMp.getHeight()
        );
        mLabelCurrentMp.setFontScale(0.4f);
        mStage.addActor(mLabelCurrentMp);

        // Цифры реген мп
        mLabelRegenMp = new Label("+0.3", hpLabelStyle);
        mLabelRegenMp.setAlignment(Align.center, Align.right);
        mLabelRegenMp.setBounds(
                mProgressMp.getX(),
                mProgressMp.getY(),
                mProgressMp.getWidth() * 0.99f,
                mProgressMp.getHeight()
        );
        mLabelRegenMp.setFontScale(0.4f);
        mStage.addActor(mLabelRegenMp);

        // полшоесс бар опыта
        mProgressExp = new ProgressBar(0, 100, 1, false, barStyle);
        mProgressExp.setBounds(0, 00, viewportWidth / 2, 20);
        mStage.addActor(mProgressExp);

        SL.getSounds().loadSounds(new String[]{
                IvcSounds.SKILL_USE_FAIL,
        });


        mProcessor.setOnSkillNotEnoughMp(new GameCallback() {

            @Override
            public void run(IvcProcessor game) {
                SL.getSounds().play(IvcSounds.SKILL_USE_FAIL);
            }

        });
    }

    @Override
    public void draw(float delta) {
        mStage.act(delta);
        mStage.draw();
        mEnemy.draw(mStage.getBatch(), mStage.getCamera().projection);
    }

    private Label newMpCostLabel(float manaCost, Label.LabelStyle style, ImageButton btn) {
        Label lbl = new Label(Integer.toString((int)manaCost), style);
        lbl.setVisible(manaCost > 0);
        lbl.setAlignment(Align.center, Align.center);
        lbl.setBounds(
                btn.getWidth() * 2 / 3,
                0,
                btn.getWidth() / 3,
                btn.getHeight() / 5
        );
        lbl.setFontScale(0.4f);
        btn.addActor(lbl);
        return lbl;
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        GameObjectState player = mProcessor.getPlayer();
        mProcessor.tick(delta);

        player.mCurrentHp = player.mCurrentHp - (float)Math.random() * delta;
        player.mExperience = (int)(player.mExperience + Math.random() * 3) % 100;

        mProgressHp.setRange(0, player.mMaxHp);
        mProgressHp.setValue(player.mCurrentHp);
        mLabelCurrentHp.setText(
                Integer.toString((int) player.mCurrentHp)
                        + "/"
                        + Integer.toString((int) player.mMaxHp)
        );
        updateRegenLabel(mLabelRegenHp, player.mRegenHp / delta, player.mCurrentHp < player.mMaxHp);

        mProgressMp.setRange(0, player.mMaxMp);
        mProgressMp.setValue(player.mCurrentMp);
        mLabelCurrentMp.setText(
                Integer.toString((int) player.mCurrentMp)
                        + "/"
                        + Integer.toString((int) player.mMaxMp)
        );
        updateRegenLabel(mLabelRegenMp, player.mRegenMp / delta, player.mCurrentMp < player.mMaxMp);

        mProgressExp.setRange(0, 100);
        mProgressExp.setValue(player.mExperience);
    }

    private void updateQuestion() {
        Drawable question = mSkin.getDrawable(mProcessor.getCurrentQuestion());
        mEnemy.setQuestion(question);
        mEnemy.randomize();
    }

    private void updateRegenLabel(Label label, float regen, boolean ltMax) {
        if (!ltMax) {
            label.setVisible(false);
            return;
        }
        if (regen < 0.1) {
            label.setText(String.format("+%.2f", regen));
        } else {
            label.setText(String.format("+%.1f", regen));
        }
        label.setVisible(true);
    }

}
