package http_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HTTPService {

    private HttpURLConnection connection;

    public HTTPService(String url) {
        try {
            this.connection = (HttpURLConnection) (new URL(url).openConnection());
            this.connection.setConnectTimeout(5000);
            this.connection.setReadTimeout(10000);

            this.connection.connect();
            if (this.connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Connection established...");
            } else {
                System.out.println("Something is wrong with the connection...");
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String bufferDataFromConnection() {
        if (connection != null) {
            try {
                StringBuilder builder = new StringBuilder();
                BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while (stream.ready()) {
                    builder.append(stream.readLine());
                }
                System.out.println("Data obtained...");
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Connection has not been established.";
    }
}
