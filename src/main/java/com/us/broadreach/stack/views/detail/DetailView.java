package com.us.broadreach.stack.views.detail;

import com.us.broadreach.stack.cache.Cache;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.service.FavoritesService;
import com.us.broadreach.stack.views.main.MainView;
import com.us.broadreach.stack.views.shared.SharedViews;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "detail-view", layout = MainView.class)
@PageTitle("Book Detail")
public class DetailView extends Div  {

    private Button favoriteAction = new Button();
    private Button goBack = new Button();
    private FavoritesService favoritesService;

    private Notification noticeAdd = new Notification("Favorite ADDED", 1000, Notification.Position.BOTTOM_CENTER);
    private Notification noticeDeleted = new Notification("Favorite DELETED", 1000,
            Notification.Position.BOTTOM_CENTER);


    public DetailView(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;

        addClassName("detail-wrap");
        add(SharedViews.getDetail(Cache.getInstance().getDetailItem(), Cache.getInstance().isFavMode()));
        add(createButtonLayout());

        favoriteAction.setText(!Cache.getInstance().isFavMode() ? "ADD to Favorites" : "DELETE Favorite");

        if (Cache.getInstance().isFavMode())
            favoriteAction.setClassName("red-button");
        else
            favoriteAction.setClassName("green-button");


        goBack.addClickListener(
                e -> goBack.getUI().ifPresent(ui -> {
                            if (!Cache.getInstance().isFavMode())
                                ui.navigate("card-list");
                            else
                                ui.navigate("favorites");
                        }
                ));

        favoriteAction.addClickListener( e -> favoriteAction.getUI().ifPresent(ui -> {
                    if (!Cache.getInstance().isFavMode())
                        addFavorite(Cache.getInstance().getDetailItem());
                    else
                        deleteFavorite(Cache.getInstance().getDetailItem().getId());
                        System.out.println("delete");
                        getUI().get().navigate("favorites");
                }
        ));

        goBack.setText(!Cache.getInstance().isFavMode() ? "Return to Netflix Search" : "Return to Favorites");


    }

    public void addFavorite(FavoriteItem favorite) {
        System.out.println(favorite);
        System.out.println("adding favorite");
        favoritesService.addFavorite(UI.getCurrent(), favoriteAdd -> {
            getUI().get().access(() -> {
                noticeAdd.open();
            });
        }, favorite);
            };




    public void deleteFavorite(String alias) {
        favoritesService.deleteFavoriteById(UI.getCurrent(),favoriteDelete -> {
            getUI().get().access(() -> {
                System.out.println("Deleted favorite");
                noticeDeleted.open();
                getUI().get().navigate("favorites");
            });
        }, alias);


    }


    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        favoriteAction.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(favoriteAction);
        buttonLayout.add(goBack);
        return buttonLayout;
    }


}
