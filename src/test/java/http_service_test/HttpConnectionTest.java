package http_service_test;

import http_service.HTTPService;
import org.junit.Test;

import java.net.ConnectException;

public class HttpConnectionTest {

    @Test //TODO: (expected = IllegalUrlException.class)
    public void illegalUrlConnectionExceptionTest() {
        String url = "smth.wrong.with.it";
        //TODO
    }

    @Test (expected = ConnectException.class)
    public void connectionTimeOutException() {
        //TODO
    }

    @Test
    public void connectionToSampleApiUrl() {
        // Sample. Is it mocking?
        String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
        new HTTPService(url);
    }
}
