package http;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HttpUtilityTest {

    @Test
    public void testHttpGoogleConnection() {
        fail();
    }

    @Test
    public void testHttpConnectionToExampleApi() {

        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getResponseCode(), 200);
        } catch (IOException ioq) {
            fail("HTTP connection threw error:" + ioq.getLocalizedMessage());
        }
    }

    @Test
    public void testSecuredConnection() {
        fail();
    }

    @Test
    public void testCheckBandwidth() {
        fail();
    }

}
