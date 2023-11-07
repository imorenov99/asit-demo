package org.example.entites;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSingleUserResponse {

    public Data data;
    public Support support;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Support {
        public String url;
        public String text;
    }
}
