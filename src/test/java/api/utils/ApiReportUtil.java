package api.utils;

import api.reports.ReportLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class ApiReportUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ApiReportUtil() {
    }

    public static void logApiTransaction(
            String method,
            String endpoint,
            String requestHeaders,
            String requestBody,
            Response response
    ) {

        String baseUrl = RestAssured.baseURI;
        String responseHeaders = formatHeaders(response);
        String statusLine = "Status Code: " + response.getStatusCode()
                + "\nStatus Line: " + response.getStatusLine()
                + "\nResponse Time: " + response.getTime() + " ms";
        String responseBody = response.asPrettyString();

        ReportLogger.logRequest(method, baseUrl, endpoint, requestHeaders, requestBody);
        ReportLogger.logResponse(statusLine, responseHeaders, responseBody);
        AllureAttachments.attachRequest(method, baseUrl, endpoint, requestHeaders, requestBody);
        AllureAttachments.attachResponse(statusLine, responseHeaders, responseBody);
    }

    public static String formatBody(Object payload) {

        if (payload == null) {
            return null;
        }

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return String.valueOf(payload);
        }
    }

    private static String formatHeaders(Response response) {

        StringBuilder headers = new StringBuilder();

        for (Header header : response.getHeaders()) {
            headers.append(header.getName())
                    .append(": ")
                    .append(header.getValue())
                    .append(System.lineSeparator());
        }

        return headers.toString().trim();
    }
}
