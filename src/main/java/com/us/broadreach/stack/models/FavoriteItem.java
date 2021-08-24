package com.us.broadreach.stack.models;

import java.io.Serializable;

public class FavoriteItem implements Serializable {

    private String title;
    private String synopsis;
    private String img;
    private String id;

    public static FavoriteItem fromItem(Result result){
        FavoriteItem favoriteItem = new FavoriteItem();

        favoriteItem.setTitle(null == result.getTitle() ? "": result.getTitle());
        favoriteItem.setSynopsis(null == result.getSynopsis() ? "": result.getSynopsis());
        if( null != result.getImg()){
            favoriteItem.setImg(result.getImg());

        } else {
            favoriteItem.setImg("https://picsum.photos/100/300");
        }
        return favoriteItem;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
