package nz.govt.stats.mdt.dl.handlers;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageResponseHandler extends ResponseHandlerBase implements ResponseHandler<File> {

    private final File target;
    public ImageResponseHandler(File target) {
        this.target = target;
    }

    @Override
    public File handleResponse(HttpResponse httpResponse) throws IOException {
        validateResponse(httpResponse);
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new IOException("Received " + httpResponse.getStatusLine().getStatusCode() + " when trying to retrieve image, message - " + httpResponse.getStatusLine().getReasonPhrase());
        }
        InputStream source = httpResponse.getEntity().getContent();
        FileUtils.copyInputStreamToFile(source, target);
        return target;
    }
}
