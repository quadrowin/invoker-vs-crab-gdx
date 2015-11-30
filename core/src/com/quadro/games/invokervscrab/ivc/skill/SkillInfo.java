package com.quadro.games.invokervscrab.ivc.skill;

/**
 * Created by Quadrowin on 21.11.2015.
 */
public class SkillInfo {

    private String mDescription;

    private String mTitle;

    private float mManaCost;

    public SkillInfo(String title, String description, float manaCost) {
        mTitle = title;
        mDescription = description;
        mManaCost = manaCost;
    }

    public String getDescription () {
        return mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getManaCost() {
        return mManaCost;
    }

}
