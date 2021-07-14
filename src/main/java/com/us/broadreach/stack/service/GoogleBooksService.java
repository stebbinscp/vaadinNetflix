package com.us.broadreach.stack.service;


import com.us.broadreach.stack.models.VolumesResponse;
import com.us.broadreach.stack.repository.GoogleBooksRepository;
import org.springframework.stereotype.Service;


@Service
public class GoogleBooksService {

    public static final int MAX_RESULTS = 20;
    private GoogleBooksRepository googleBooksRepository;

    public GoogleBooksService(GoogleBooksRepository googleBooksRepository) {
        this.googleBooksRepository = googleBooksRepository;
    }


    public void getBooksPaged(AsyncRestCallback<VolumesResponse> callback, String search, int maxResults,
                              int startIndex) {

        System.out.println("fetching books -> " + startIndex + " to "
                + (startIndex + MAX_RESULTS - 1));

        googleBooksRepository.getBooksPaged(callback, search, maxResults, startIndex);


    }

}
