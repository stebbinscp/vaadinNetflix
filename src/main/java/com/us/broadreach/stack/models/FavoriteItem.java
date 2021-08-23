package com.us.broadreach.stack.models;

import java.io.Serializable;

public class FavoriteItem implements Serializable {

    private String userEmail;
    private String title;
    private String synopsis;
    private String img;

    public static FavoriteItem fromItem(Result result, String  userEmail){
        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setUserEmail(userEmail);

        favoriteItem.setTitle(null == result.getTitle() ? "": result.getTitle());
        favoriteItem.setSynopsis(null == result.getSynopsis() ? "": result.getSynopsis());
        if( null != result.getImg()){
            favoriteItem.setImg(result.getImg());

        } else {
            favoriteItem.setImg("https://picsum.photos/100/300");
        }
        System.out.println(favoriteItem.img);
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
}
