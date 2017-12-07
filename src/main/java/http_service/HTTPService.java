package http_service;

import exception.UnsuccessfulApiConnectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPService {

    private HttpURLConnection connection;

    public void createConnectionFromURL(String url) throws IOException, UnsuccessfulApiConnectionException {
        this.connection = (HttpURLConnection) (new URL(url).openConnection());

        this.connection.setConnectTimeout(5000);
        this.connection.setReadTimeout(10000);
        this.connection.connect();

        if (this.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Connection established...");
        } else {
            throw new UnsuccessfulApiConnectionException();
        }
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }

    public void close() {
        connection.disconnect();
    }

    public String getJsonFromConnection() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while (stream.ready()) {
            builder.append(stream.readLine());
        }
        System.out.println("Data obtained...");
        return builder.toString();
//        try {
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    StringBuilder builder = new StringBuilder();
//                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    while (stream.ready()) {
//                        builder.append(stream.readLine());
//                    }
//                    System.out.println("Data obtained...");
//                    return builder.toString();
//            }
//        } catch (IOException e) {
//            System.err.println("Something is wrong with reading from the buffer...");
//        }
//        return "Connection has not been established.";
    }
}
