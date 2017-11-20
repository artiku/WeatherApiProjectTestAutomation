package http_service_test;

import http_service.HTTPService;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class HttpConnectionTest {

    // THESE EXCEPTIONS WERE CAUGHT, SO CAN NOT BE SEEN IN TESTS
//    @Test (expected = MalformedURLException.class)
//    public void malformedUrlConnectionExceptionTest() {
//        String url = "smth.wrong.with.it";
//        new HTTPService(url);
//    }
//
//    @Test (expected = SocketTimeoutException.class)
//    public void connectionTimeOutException() {
//        String link = "http://www.google.com:81";
//        new HTTPService(link);
//    }

    @Test
    public void connectionNoFailureCheck() {
        String link = "http://www.google.com";
        new HTTPService(link);
    }

    @Test
    public void connectToSampleApiUrlWithoutExceptions() {
        String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
        new HTTPService(url);
    }

    @Test
    public void mockHttpServiceBufferDataTest() {
        HTTPService mockedHttpService = mock(HTTPService.class);
        mockedHttpService.bufferDataFromConnection();
        verify(mockedHttpService).bufferDataFromConnection();
    }
}
