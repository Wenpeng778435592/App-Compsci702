package com.compsci702.compsci702app.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class Phrase implements Parcelable {
    private String phrase;
    private String meaning;
    private String example;
    private int favourited;

    protected Phrase(Parcel in) {
        phrase = in.readString();
        meaning = in.readString();
        example = in.readString();
        favourited = in.readInt();
    }

    public Phrase(){

    }

    public static final Creator<Phrase> CREATOR = new Creator<Phrase>() {
        @Override
        public Phrase createFromParcel(Parcel in) {
            return new Phrase(in);
        }

        @Override
        public Phrase[] newArray(int size) {
            return new Phrase[size];
        }
    };

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getFavourited() {
        return favourited;
    }

    public void setFavourited(int favourited) {
        this.favourited = favourited;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phrase);
        dest.writeString(meaning);
        dest.writeString(example);
        dest.writeInt(favourited);
    }
}
