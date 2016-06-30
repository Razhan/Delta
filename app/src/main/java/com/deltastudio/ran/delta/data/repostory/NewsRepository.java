package com.deltastudio.ran.delta.data.repostory;

import com.deltastudio.ran.deltalibrary.data.repository.Repository;

import javax.inject.Inject;

/**
 * Created by ranzh on 6/30/2016.
 */
public class NewsRepository implements Repository {

    private final RestfulService restfulService;

    @Inject
    public NewsRepository(RestfulService service) {
        restfulService = service;
    }







}
