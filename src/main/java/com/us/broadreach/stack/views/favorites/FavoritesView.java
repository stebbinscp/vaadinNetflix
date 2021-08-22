package com.us.broadreach.stack.views.favorites;


import com.us.broadreach.stack.cache.Cache;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.service.FavoritesService;
import com.us.broadreach.stack.views.shared.SharedViews;
import com.us.broadreach.stack.views.main.MainView;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import elemental.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@Route(value = "favorites", layout = MainView.class)
@PageTitle("Favorites")
@CssImport("./views/generic-list.css")
public class FavoritesView extends Div implements AfterNavigationObserver {
    public static int MAX_RESULTS = 20;
    private FavoritesService favoritesService;
    private Grid<FavoriteItem> grid = new Grid<>();
    private int page;
    private boolean isLoading = false;
    private boolean isEnd = false;

    //no need to cache these. We will simply refetch them each time the user returns to this page
//    private List<FavoriteItem> favoriteItems = new ArrayList<>();

    public FavoritesView(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
        System.out.println("favorites vuew");

//        addClassName("generic-list");
//        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
//        grid.addComponentColumn(item -> SharedViews.getCard(item, true));
//        grid.addItemClickListener(
//                event -> grid.getUI().ifPresent(ui -> {
//                    System.out.println("clicked");
//                            Cache.getInstance().setDetailItem(event.getItem());
//                    System.out.println("caching");
//                            Cache.getInstance().setFavMode(true);
//                    System.out.println("fav mode is true");
//                            ui.navigate("detail-view");
//
//                        }
//                ));

//        add(withClientsideScrollListener(grid));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent navigationEvent) {
        page = 1;
        getFavoritesPaged();
    }

    private void getFavoritesPaged() {
        System.out.println("get favorites list from mongo");
//        if (isEnd) return;
//
//        isLoading = true;
//        favoritesService.getFavoritesPaged(favoriteResponse -> {
//            getUI().get().access(() -> {
//
//                favoriteItems.addAll(favoriteResponse);
//                grid.setItems(favoriteItems);
//
//                if (favoriteResponse.size() < MAX_RESULTS){
//                    isEnd = true;
//                } else {
//                    page++;
//                }
//
//                isLoading = false;
//                getUI().get().push();
//            });
//        }, page);
    }

//    private Grid<FavoriteItem> withClientsideScrollListener(Grid<FavoriteItem> grid) {
//        grid.getElement().executeJs(
//                "this.$.scroller.addEventListener('scroll', (scrollEvent) => " +
//                        "{requestAnimationFrame(" +
//                        "() => $0.$server.onGridScroll({sh: this.$.table.scrollHeight, " +
//                        "ch: this.$.table.clientHeight, " +
//                        "st: this.$.table.scrollTop}))},true)",
//                getElement());
//        // this, should be ok once we build favorite item with the mongo db?
//        return grid;
//    }
//
//    @ClientCallable
//    public void onGridScroll(JsonObject scrollEvent) {
//        int scrollHeight = (int) scrollEvent.getNumber("sh");
//        int clientHeight = (int) scrollEvent.getNumber("ch");
//        int scrollTop = (int) scrollEvent.getNumber("st");
//        double percentage = (double) scrollTop / (scrollHeight - clientHeight);
////        System.out.println("scroll percentage " + percentage);
//        //reached almost the bottom of the scroll
//        if (percentage >= 0.95) {
//            System.out.println("Reached bottom");
//            if (!isLoading) {
//                System.out.println("Paging...");
//                getFavoritesPaged();
//            }
//        }
//
//    }



}
