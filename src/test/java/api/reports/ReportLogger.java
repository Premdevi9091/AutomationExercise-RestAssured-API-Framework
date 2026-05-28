package api.reports;

public class ReportLogger {

    public static void logInfo(String message) {

        if (TestListener.getTest() != null) {
            TestListener.getTest().info(message);
        }
    }

    public static void logPass(String message) {

        if (TestListener.getTest() != null) {
            TestListener.getTest().pass(message);
        }
    }

    public static void logFail(String message) {

        if (TestListener.getTest() != null) {
            TestListener.getTest().fail(message);
        }
    }

    public static void logRequest(String method, String baseUrl, String endpoint, String headers, String body) {

        logCollapsible(
                "Request Sent",
                "Method: " + method
                        + "\nBase URL: " + emptyIfBlank(baseUrl)
                        + "\nEndpoint: " + endpoint
                        + "\n\nHeaders:\n" + emptyIfBlank(headers)
                        + "\n\nBody:\n" + emptyIfBlank(body)
        );
    }

    public static void logResponse(String statusLine, String headers, String body) {

        logCollapsible(
                "Response Received",
                statusLine
                        + "\n\nHeaders:\n" + emptyIfBlank(headers)
                        + "\n\nBody:\n" + emptyIfBlank(body)
        );
    }

    private static void logCollapsible(String title, String content) {

        if (TestListener.getTest() != null) {
            TestListener.getTest()
                    .info(
                            "<details><summary><b>" + title + "</b></summary>"
                                    + "<pre>" + escapeHtml(content) + "</pre>"
                                    + "</details>"
                    );
        }
    }

    private static String emptyIfBlank(String value) {

        return value == null || value.isBlank() ? "None" : value;
    }

    private static String escapeHtml(String value) {

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
