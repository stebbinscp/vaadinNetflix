package com.us.broadreach.stack.repository;

import com.us.broadreach.stack.cache.Cache;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.service.AsyncRestCallback;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class FavoritesRepository {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private final String BASE = "https://container-service-22.9r4o895c5nvr8.us-east-2.cs.amazonlightsail.com";

    public void getFavoritesPaged(AsyncRestCallback<List<FavoriteItem>> callback, int page) {

        String email = Cache.getInstance().getEmail();

        String raw = BASE + "/wish/paged/" + email + "/%d";
        String formatted = String.format(raw, page);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri(formatted);

        spec.retrieve().bodyToMono(new ParameterizedTypeReference<List<FavoriteItem>>() {
        }).publishOn(Schedulers.fromExecutor(executorService)).subscribe(callback::operationFinished);
    }


    public void deleteFavoriteById(UI ui, AsyncRestCallback<FavoriteItem> callback, String id) {

        String email = Cache.getInstance().getEmail();
        String raw = BASE + "/wish/" + email + "/%s";
        String formatted = String.format(raw, id);
        Mono<FavoriteItem> mono = WebClient.create().delete()
                .uri(formatted)
                .retrieve()
                .bodyToMono(FavoriteItem.class);

        mono
                .doOnError(throwable -> ui.access(() -> {
                    Notification.show("Unable to delete favorite: " + throwable.getMessage(), 2000,
                            Notification.Position.BOTTOM_CENTER);
                    ui.navigate("favorites");
                }))
                .publishOn(Schedulers.fromExecutor(executorService))
                .subscribe(callback::operationFinished);

    }


    public void addFavorite(UI ui, AsyncRestCallback<FavoriteItem> callback, FavoriteItem favoriteAdd) {

        String formatted = BASE + "/wish";
        Mono<FavoriteItem> mono = WebClient.create().post()

                .uri(formatted)
                .body(Mono.just(favoriteAdd), FavoriteItem.class)
                .retrieve()
                .bodyToMono(FavoriteItem.class);

        mono
                .doOnError(throwable -> {
                    String message = "";
                    switch (((WebClientResponseException.UnsupportedMediaType) throwable).getStatusCode().value()){
                        case 415:
                            message = "This book is already in your favorites.";
                        break;
                        default:
                            message = "There was an error: " + throwable.getMessage();

                    }
                    final String finalMessage = message;
                    ui.access(() -> {
                        Notification.show(finalMessage , 2000,
                                Notification.Position.BOTTOM_CENTER);
                        ui.navigate("favorites");
                    });

                })
                .publishOn(Schedulers.fromExecutor(executorService))
                .subscribe(callback::operationFinished);

    }



}
