package com.us.broadreach.stack.views.books;

import com.us.broadreach.stack.cache.Cache;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.models.VolumesResponse;
import com.us.broadreach.stack.service.GoogleBooksService;
import com.us.broadreach.stack.views.main.MainView;
import com.us.broadreach.stack.views.shared.SharedViews;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import elemental.json.JsonObject;

import java.util.stream.Collectors;

@Route(value = "card-list", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Book Search")
@CssImport("./views/generic-list.css")
@PreserveOnRefresh
public class BooksView extends Div implements AfterNavigationObserver {
    public static int MAX_RESULTS = 20;
    private GoogleBooksService googleBooksService;
    private Grid<FavoriteItem> grid = new Grid<>();
    private boolean isLoading = false;
    private TextField keyWord;
    private Notification loading = new Notification("Loading...", 1000, Notification.Position.BOTTOM_CENTER);

    public BooksView(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;

        keyWord = new TextField();
        keyWord.setLabel("Search Term");
        keyWord.setPlaceholder("type search-term, then press [ENTER]");
        keyWord.setAutofocus(true);
        keyWord.addKeyDownListener(keyDownEvent -> {
                    String keyStroke = keyDownEvent.getKey().getKeys().toString();
                    if (keyStroke.equals("[Enter]") && !isLoading && !keyWord.getValue().equals("")) {
                        Cache.getInstance().setKeyword(keyWord.getValue());
                        executeSearch(Cache.getInstance().getKeyword());
                    }
                }
        );

        addClassName("generic-list");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(favoriteItem -> SharedViews.getCard(favoriteItem, false));
        grid.addItemClickListener(
                event -> grid.getUI().ifPresent(ui -> {

                            Cache.getInstance().setDetailItem(event.getItem());
                            Cache.getInstance().setFavMode(false);
                            ui.navigate("detail-view");

                        }
                ));

        add(keyWord, withClientsideScrollListener(grid));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        if (Cache.getInstance().itemsSize() > 0)
            grid.setItems(Cache.getInstance().streamItems());

        keyWord.setValue(Cache.getInstance().getKeyword());

    }

    private void getPlaces(String searchTerm) {

        if (null == searchTerm || searchTerm.equals("")) return;

        isLoading = true;
        googleBooksService.getBooksPaged((VolumesResponse volumesResponse) -> {
            getUI().get().access(() -> {

                Cache.getInstance().addItems(volumesResponse.getItems()
                        .stream()
                        .map( item -> FavoriteItem.fromItem(item, Cache.getInstance().getEmail()))
                        .collect(Collectors.toList())
                );
                grid.setItems(Cache.getInstance().streamItems());
                Cache.getInstance().setOffset(Cache.getInstance().getOffset() + MAX_RESULTS);
                isLoading = false;
                getUI().get().push();

            });
        }, searchTerm, MAX_RESULTS, Cache.getInstance().getOffset());
    }



    private Grid<FavoriteItem> withClientsideScrollListener(Grid<FavoriteItem> grid) {
        grid.getElement().executeJs(
                "this.$.scroller.addEventListener('scroll', (scrollEvent) => " +
                        "{requestAnimationFrame(" +
                        "() => $0.$server.onGridScroll({sh: this.$.table.scrollHeight, " +
                        "ch: this.$.table.clientHeight, " +
                        "st: this.$.table.scrollTop}))},true)",
                getElement());
        return grid;
    }

    @ClientCallable
    public void onGridScroll(JsonObject scrollEvent) {
        int scrollHeight = (int) scrollEvent.getNumber("sh");
        int clientHeight = (int) scrollEvent.getNumber("ch");
        int scrollTop = (int) scrollEvent.getNumber("st");

        double percentage = (double) scrollTop / (scrollHeight - clientHeight);
        System.out.println("scroll percentage " + percentage);
        //reached almost the bottom of the scroll
        if (percentage >= 0.95) {
            System.out.println("Reached bottom");
            if (!isLoading) {
                System.out.println("Paging...");
                loading.open();
                getPlaces(Cache.getInstance().getKeyword());
            }
        }

    }

    public void executeSearch(String searchFor) {

        Cache.getInstance().setKeyword(searchFor);
        Cache.getInstance().setOffset(0);
        Cache.getInstance().clearItems();
        getPlaces(searchFor);
    }



}
