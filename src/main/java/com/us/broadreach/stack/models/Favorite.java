package com.us.broadreach.stack.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("img")
    @Expose
    private String img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
