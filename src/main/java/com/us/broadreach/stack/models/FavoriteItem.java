package com.us.broadreach.stack.models;

import java.io.Serializable;

public class FavoriteItem implements Serializable {

    private String userEmail;
    private String title;
    private String synopsis;
    private String img;

    //factory method
    // maybe we build favorite item using the json? so when we click add to favoite
    // packages a json to send to mongo, adds
    // then we can fetch favorites
    public static FavoriteItem fromItem(Result result, String  userEmail){
        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setUserEmail(userEmail);

//        StringBuilder stringBuilder = new StringBuilder();

        favoriteItem.setTitle(null == result.getTitle() ? "": result.getTitle());
        favoriteItem.setSynopsis(null == result.getSynopsis() ? "": result.getSynopsis());
        if( null != result.getImg()){
            favoriteItem.setImg(result.getImg());
        } else {
            favoriteItem.setImg("https://picsum.photos/100/300");
        }

//        favoriteItem.setYear(null == result.getYear() ? "" : result.getYear());
        return favoriteItem;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
