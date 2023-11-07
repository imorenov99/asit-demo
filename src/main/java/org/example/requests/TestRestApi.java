package org.example.requests;


import org.example.entites.GetSingleUserResponse;
import org.example.utilits.HttpUtilit;

import static org.example.utilits.PropertyLoader.getProperty;


public class TestRestApi extends HttpUtilit {

    public TestRestApi() {
        this.url = getProperty("testRestApiService");
    }

    public GetSingleUserResponse getSingleUser(String endpoint) {
        return get(endpoint).as(GetSingleUserResponse.class);
    }
}
