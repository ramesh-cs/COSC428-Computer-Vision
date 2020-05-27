package nz.govt.stats.mdt.dl.handlers;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

public class ResponseHandlerBase {

    public void validateResponse(HttpResponse httpResponse) throws IOException {

        HttpEntity entity = httpResponse.getEntity();
        final int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
        if (responseStatusCode < HttpStatus.SC_OK && responseStatusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
            throw new ClientProtocolException("Response status is " + responseStatusCode);
        }
    }

    public String[] getContentHeader(HttpResponse httpResponse) throws IOException {
//        Optional<Header> header = Arrays.stream(httpResponse.getAllHeaders()).filter(h -> h.getName().equalsIgnoreCase("Content-Type")).findFirst();
        Header header = httpResponse.getEntity().getContentType();
//        if (!header. isPresent()) {
//            throw new IOException("Unable to find Content-Type from response");
//        }
        return header.getValue().split("/");
    }
}
