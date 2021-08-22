package com.us.broadreach.stack.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite {

//    @SerializedName("id")
//    @Expose
//    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
//    @SerializedName("rating")
//    @Expose
//    private String rating;
//    @SerializedName("phone")
//    @Expose
//    private String phone;
//    @SerializedName("price")
//    @Expose
//    private String price;
    @SerializedName("img")
    @Expose
    private String img;
//    @SerializedName("address")
//    @Expose
//    private String address;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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
