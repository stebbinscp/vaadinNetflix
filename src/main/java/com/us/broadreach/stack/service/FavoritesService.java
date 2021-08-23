package com.us.broadreach.stack.service;

import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.repository.FavoritesRepository;
import com.vaadin.flow.component.UI;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;

@Service
public class FavoritesService extends ResponseEntityExceptionHandler {
    private FavoritesRepository favoritesRepository;



    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }


    public void getFavoritesPaged(ResponseCallback<List<FavoriteItem>> callback, int page){
        favoritesRepository.getFavoritesPaged(callback, page);
    }



    public void addFavorite(UI ui, ResponseCallback<FavoriteItem> callback,
                            FavoriteItem favorite)  {
        favoritesRepository.addFavorite(ui, callback, favorite);
    }

    public void deleteFavoriteById(UI ui, ResponseCallback<FavoriteItem> callback,
                                   String id) {
        favoritesRepository.deleteFavoriteById(ui, callback, id);
    }



}
