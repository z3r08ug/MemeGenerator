package com.example.chris.memegenerator.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SyntaxBuilder implements Parcelable{
    private boolean hasCelebrity;
    private boolean hasVerb;
    private boolean hasNoun;
    private boolean hasName; //a name that is a proper name but not a celebrity
    private List<String> celebrities;
    private List<String> nouns;
    private List<String> verbs;
    private List<String> names;
    private List<String> activities;
    private boolean hasActivity;
    boolean[] bools = {hasActivity, hasName, hasVerb, hasNoun, hasCelebrity};

    public void addCelebrity(String celeb)
    {
        if (celebrities == null)
        {
            celebrities = new ArrayList<String>();
            hasCelebrity = true;
        }
        celebrities.add(celeb);
    }
    public void addNoun(String noun)
    {
        if (nouns == null)
        {
            nouns = new ArrayList<String>();
            hasNoun = true;
        }
        nouns.add(noun);
    }
    public void addVerb(String verb)
    {
        if (verb.endsWith("ing"))
        {
            if (activities == null)
            {
                activities = new ArrayList<String>();
                hasActivity = true;
            }
            activities.add(verb);

        }
        else
        {
            if (verbs == null)
            {
                verbs = new ArrayList<String>();
                hasVerb = true;
            }
            verbs.add(verb);
        }

    }
    public void addName(String name)
    {
        if (names == null)
        {
            names = new ArrayList<String>();
            hasName = true;
        }
        names.add(name);
    }
    public boolean isHasCelebrity() {
        return hasCelebrity;
    }

    public void setHasCelebrity(boolean hasCelebrity) {
        this.hasCelebrity = hasCelebrity;
    }

    public boolean isHasVerb() {
        return hasVerb;
    }

    public void setHasVerb(boolean hasVerb) {
        this.hasVerb = hasVerb;
    }

    public boolean isHasNoun() {
        return hasNoun;
    }

    public void setHasNoun(boolean hasNoun) {
        this.hasNoun = hasNoun;
    }

    public boolean isHasName() {
        return hasName;
    }

    public void setHasName(boolean hasName) {
        this.hasName = hasName;
    }

    public List<String> getCelebrities() {
        return celebrities;
    }

    public void setCelebrities(List<String> celebrities) {
        this.celebrities = celebrities;
    }

    public List<String> getNouns() {
        return nouns;
    }

    public void setNouns(List<String> nouns) {
        this.nouns = nouns;
    }

    public List<String> getVerbs() {
        return verbs;
    }

    public void setVerbs(List<String> verbs) {
        this.verbs = verbs;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public SyntaxBuilder() {

    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    protected SyntaxBuilder(Parcel in) {
        in.readStringList(getCelebrities());
        in.readStringList(getNouns());
        in.readStringList(getVerbs());
        in.readStringList(getNames());
        in.readStringList(getActivities());
        in.readBooleanArray(bools);

    }
    public static final Creator<SyntaxBuilder> CREATOR = new Creator<SyntaxBuilder>() {
        @Override
        public SyntaxBuilder createFromParcel(Parcel in) {
            return new SyntaxBuilder(in);
        }

        @Override
        public SyntaxBuilder[] newArray(int size) {
            return new SyntaxBuilder[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(celebrities);
        parcel.writeStringList(nouns);
        parcel.writeStringList(verbs);
        parcel.writeStringList(names);
        parcel.writeStringList(activities);
        parcel.writeBooleanArray(bools);

    }
}
