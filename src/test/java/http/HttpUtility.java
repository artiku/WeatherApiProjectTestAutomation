package http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

/**
 * Created by HP on 11.09.2017.
 */
public class HttpUtility {

    public static HttpURLConnection makeHttpGetRequest (String requestUrl) throws IOException {
        return (HttpURLConnection) new URL(requestUrl).openConnection();
    }
}
