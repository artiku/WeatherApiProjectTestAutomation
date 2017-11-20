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
                System.err.println("Maybe city input was wrong?");
                throw new IOException();
            }
        } catch (SocketTimeoutException e) {
            System.err.println("Connection Timeout!");
        } catch (IOException e) {
            System.err.println("Something is wrong with the connection...");
        }
    }

    public String bufferDataFromConnection() {
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    StringBuilder builder = new StringBuilder();
                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while (stream.ready()) {
                        builder.append(stream.readLine());
                    }
                    System.out.println("Data obtained...");
                    return builder.toString();
            }
        } catch (IOException e) {
            System.err.println("Something is wrong with reading from the buffer...");
        }
        return "Connection has not been established.";
    }
}
