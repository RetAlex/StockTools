package stock.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class RequestHelper {
    /**
     * Method makes http request to indicated resource with query parameters included in url string
     *
     * @param url     url to send get request. Should already contain any query parameters for request
     * @param headers additional headers should be used for request
     * @return raw string response from requested resource
     */
    public static String getHttp(String url, List<NameValuePair> headers){
        try {
            HttpRequestBase request = new HttpGet(url);

            if (headers != null) {
                for (NameValuePair header : headers) {
                    request.addHeader(header.getName(), header.getValue());
                }
            }

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(request);
            checkStatus(response);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }catch (Exception e){
            throw new RuntimeException("Can't read answer due to exception: "+e.getClass()+" ["+e.getMessage()+"]");
        }
    }

    private static void checkStatus(HttpResponse response){
        if (response == null || response.getStatusLine().getStatusCode() == 502) throw new RuntimeException("502 error");
        if (response.getStatusLine().getStatusCode() == 401)
            throw new RuntimeException("Non authorized");
        if (response.getStatusLine().getStatusCode() == 400)
            throw new RuntimeException("Bad request");
        if (response.getStatusLine().getStatusCode() == 403)
            throw new RuntimeException("Not authenticated");
        if (response.getStatusLine().getStatusCode() == 405) throw new RuntimeException("Method not allowed");
        if (response.getStatusLine().getStatusCode() >= 300) {
            throw new RuntimeException("Unhandled error happened");
        }
    }
}
