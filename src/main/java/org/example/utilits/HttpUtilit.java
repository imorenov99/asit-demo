package org.example.utilits;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;


public class HttpUtilit {

    public String url;

    public Response get(String path) {
        return buildRequestSpecification().get(path);
    }

    protected RequestSpecification buildRequestSpecification() {
        return given(new RequestSpecBuilder().setBaseUri(url)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build());
    }
}
