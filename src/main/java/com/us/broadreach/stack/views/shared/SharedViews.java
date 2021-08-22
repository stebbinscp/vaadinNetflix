package com.us.broadreach.stack.views.shared;


import com.us.broadreach.stack.models.FavoriteItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./views/shared-views.css")
public class SharedViews {

    public static VerticalLayout getDetail(FavoriteItem favorite, boolean favMode) {
        VerticalLayout detail = new VerticalLayout();
        detail.setSpacing(false);
        detail.setPadding(false);

        detail.addClassName("detail");
        Div tab = new Div();
        tab.addClassName("tab");

        Image image = new Image();

        image.setSrc(null == favorite.getImg() ? "https://picsum.photos/200/300" : favorite.getImg());
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("vertical-layout");


        Span title = new Span(null == favorite.getTitle() ? "" : favorite.getTitle());
        title.addClassNames("text", "title");
        Span synopsis = new Span(null == favorite.getSynopsis() ? "" : favorite.getSynopsis());
        synopsis.addClassName("text");
        Span email = new Span(null == favorite.getUserEmail() ? "" : favorite.getUserEmail());
        email.addClassName("text");

        if (favMode) {
            detail.addClassName("fav-mode");
            verticalLayout.add(title, email, synopsis);
        } else {
            verticalLayout.add(title, synopsis);
        }
        detail.add(tab, image, verticalLayout);

        return detail;
    }


    public static HorizontalLayout getCard(FavoriteItem favorite, boolean favMode) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.setPadding(false);

        Div tab = new Div();
        tab.addClassName("tab");
        Image image = new Image();

        image.setSrc(null == favorite.getImg() ? "https://picsum.photos/200/300" : favorite.getImg());
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("vertical-layout");
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        Span title = new Span(null == favorite.getTitle() ? "" : favorite.getTitle());
        title.addClassNames("text", "title");
        Span synopsis = new Span(null == favorite.getSynopsis() ? "" : favorite.getSynopsis());
        synopsis.addClassName("text");
        Span email = new Span(null == favorite.getUserEmail() ? "" : favorite.getUserEmail());
        email.addClassName("text");


        if (favMode) {
            verticalLayout.add(title, synopsis, email);
            card.add(tab, image, verticalLayout);
        } else {
            verticalLayout.add(title, synopsis);
            card.add(image, verticalLayout);
        }

        return card;
    }


}
