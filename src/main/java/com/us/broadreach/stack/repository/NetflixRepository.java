package com.us.broadreach.stack.repository;

import com.us.broadreach.stack.models.NetflixModel;
import com.us.broadreach.stack.models.Result;
import com.us.broadreach.stack.service.ResponseCallback;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class NetflixRepository {

    private final String BASE = "https://unogsng.p.rapidapi.com/search?offset=%d&limit=%d&query=%s";

    public void getNetflix(ResponseCallback<List<Result>> callback, int offset, int limit, String search) {

        String formatted = String.format(BASE, offset, limit, search);
        WebClient.RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri(formatted)
                .header("x-rapidapi-key", "a9abe16e01msh26d9439e9eb0634p10387fjsnab19efc4fc67")
                .header("x-rapidapi-host", "unogsng.p.rapidapi.com");

        spec.retrieve().toEntity(NetflixModel.class).subscribe(movieResponse -> {

            callback.operationFinished(movieResponse.getBody().getResults());

        });

    }
}
