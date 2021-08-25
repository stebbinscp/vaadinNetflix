package com.us.broadreach.stack.service;


import com.us.broadreach.stack.models.Result;
import com.us.broadreach.stack.repository.NetflixRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NeftlixService {

    public static final int MAX_RESULTS = 20;
    private NetflixRepository netflixRepository;

    public NeftlixService(NetflixRepository netflixRepository) {
        this.netflixRepository = netflixRepository;
    }


    public void getNetlixPaged(ResponseCallback<List<Result>> callback, String search, int maxResults,
                               int startIndex) {

        System.out.println("fetching netflix -> " + startIndex + " to "
                + (startIndex + MAX_RESULTS - 1));

        netflixRepository.getNetflix(callback, startIndex, maxResults, search);


    }

}


