package http_service_test;

import http_service.HTTPService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class HttpConnectionTest {

    private HTTPService httpService;

    @Before
    public void setHttpService() {
        this.httpService = new HTTPService();
    }

    @Test (expected = MalformedURLException.class)
    public void malformedUrlConnectionExceptionTest() throws IOException {
        String url = "smth.wrong.with.it";
        httpService.createConnectionFromURL(url);
    }

    @Test (expected = SocketTimeoutException.class)
    public void connectionTimeOutException() throws IOException {
        String link = "http://www.google.com:81";
        httpService.createConnectionFromURL(link);
    }

    @Test
    public void connectionNoFailureCheck() {
        String link = "http://www.google.com";
        try {
            httpService.createConnectionFromURL(link);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test
    public void connectToSampleApiUrlWithoutExceptions() {
        String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
        try {
            httpService.createConnectionFromURL(url);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }


    @Test
    public void connectToSampleApiSuccessfully() {
        String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
        try {
            httpService.createConnectionFromURL(url);
            int responseCode = httpService.getResponseCode();

            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test
    public void mockHttpServiceGetJsonTest() {
        try {
            HTTPService mockedHttpService = mock(HTTPService.class);
            mockedHttpService.getJsonFromConnection();
            verify(mockedHttpService).getJsonFromConnection();
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

}
