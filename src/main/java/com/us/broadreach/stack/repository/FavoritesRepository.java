package com.us.broadreach.stack.repository;

import com.us.broadreach.stack.cache.Cache;
import com.us.broadreach.stack.models.FavoriteItem;
import com.us.broadreach.stack.service.ResponseCallback;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import net.minidev.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class FavoritesRepository {

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private final String BASE = "http://localhost:8080/netflix/";

    public void getFavoritesPaged(ResponseCallback<List<FavoriteItem>> callback, int page) {

        String formatted = String.format(BASE, page);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri(formatted);

        spec.retrieve().bodyToMono(new ParameterizedTypeReference<List<FavoriteItem>>() {
        }).publishOn(Schedulers.fromExecutor(executorService)).subscribe(callback::operationFinished);

    }

//
    public void deleteFavoriteById(UI ui, ResponseCallback<FavoriteItem> callback, String id) {
        System.out.println("delete from mongo");
//        String email = Cache.getInstance().getEmail();
//        String raw = BASE + "/wish/" + email + "/%s";
//        String formatted = String.format(raw, id);
//        Mono<FavoriteItem> mono = WebClient.create().delete()
//                .uri(formatted)
//                .retrieve()
//                .bodyToMono(FavoriteItem.class);
//
//        mono
//                .doOnError(throwable -> ui.access(() -> {
//                    Notification.show("Unable to delete favorite: " + throwable.getMessage(), 2000,
//                            Notification.Position.BOTTOM_CENTER);
//                    ui.navigate("favorites");
//                }))
//                .publishOn(Schedulers.fromExecutor(executorService))
//                .subscribe(callback::operationFinished);
//
    }
//
//
    public void addFavorite(UI ui, ResponseCallback<FavoriteItem> callback, FavoriteItem favoriteAdd) {
        System.out.println("update mongo");
//        String formatted = BASE + "/wish";
//        Mono<FavoriteItem> mono = WebClient.create().post()
//
//                .uri(formatted)
//                .body(Mono.just(favoriteAdd), FavoriteItem.class)
//                .retrieve()
//                .bodyToMono(FavoriteItem.class);
//
//        mono
//                .doOnError(throwable -> {
//                    String message = "";
//                    switch (((WebClientResponseException.UnsupportedMediaType) throwable).getStatusCode().value()){
//                        case 415:
//                            message = "This book is already in your favorites.";
//                        break;
//                        default:
//                            message = "There was an error: " + throwable.getMessage();
//
//                    }
//                    final String finalMessage = message;
//                    ui.access(() -> {
//                        Notification.show(finalMessage , 2000,
//                                Notification.Position.BOTTOM_CENTER);
//                        ui.navigate("favorites");
//                    });
//
//                })
//                .publishOn(Schedulers.fromExecutor(executorService))
//                .subscribe(callback::operationFinished);
//
    }
//

    // this is where i will input the mongo stuff
    // will need to have it from my qurkus
    // and print out the response is the intent
    // then like company item, when i am grabbing the item, I will want to
    // to string and print the favoriteItem, fetchAll functionality

}
