package api.utils;

import io.qameta.allure.Allure;

public class AllureAttachments {

    public static void attachRequest(String method, String baseUrl, String endpoint, String headers, String body) {

        Allure.addAttachment(
                "Request Sent",
                "text/plain",
                "Method: " + method
                        + "\nBase URL: " + emptyIfBlank(baseUrl)
                        + "\nEndpoint: " + endpoint
                        + "\n\nHeaders:\n" + emptyIfBlank(headers)
                        + "\n\nBody:\n" + emptyIfBlank(body)
        );
    }

    public static void attachResponse(String statusLine, String headers, String body) {

        Allure.addAttachment(
                "Response Received",
                "text/plain",
                statusLine
                        + "\n\nHeaders:\n" + emptyIfBlank(headers)
                        + "\n\nBody:\n" + emptyIfBlank(body)
        );
    }

    private static String emptyIfBlank(String value) {

        return value == null || value.isBlank() ? "None" : value;
    }
}
