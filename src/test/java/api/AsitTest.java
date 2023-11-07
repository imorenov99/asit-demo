package api;


import org.assertj.core.api.SoftAssertions;
import org.example.requests.TestRestApi;
import org.junit.jupiter.api.AfterAll;

import static org.example.utilits.ExcelConverter.parseExcelDataToAllureReport;

public class AsitTest {

    private final static TestRestApi REST_API = new TestRestApi();
    private SoftAssertions softAssertions = new SoftAssertions();

    @org.junit.jupiter.api.Test
    public void checkResponse() {
        var response = REST_API.getSingleUser("/api/users/2");
        softAssertions.assertThat(response.getData().getId()).isEqualTo(2);
    }

    @AfterAll
    public static void addXlsxToAllure() {
        parseExcelDataToAllureReport();
    }
}
