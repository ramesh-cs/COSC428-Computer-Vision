package nz.govt.stats.mdt.dl.services;

import nz.govt.stats.mdt.dl.handlers.ImageResponseHandler;
import nz.govt.stats.mdt.dl.localrepo.LocalRepository;
import nz.govt.stats.mdt.dl.localrepo.models.Image;
import nz.govt.stats.mdt.dl.setstore.CollectionInstanceRepository;
import nz.govt.stats.mdt.dl.setstore.models.CollectionInstance;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.WinHttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageDownloader {
    Logger logger = LoggerFactory.getLogger(ImageDownloader.class);
    private final LocalRepository localRepository;
    private Environment env;
    private String targetPath;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CloseableHttpClient closeableHttpClient;
    public ImageDownloader(LocalRepository localRepository, CollectionInstanceRepository collectionInstanceRepository, Environment env) {
        this.localRepository = localRepository;
        this.env = env;
        this.targetPath = env.getProperty("samples.download.location");
    }

    public static File createDirectoriesNotPresent(String fileName) throws IOException {
        if (Files.exists(Paths.get(fileName))) {
            return new File(fileName);
        }
        return Files.createDirectories(Paths.get(fileName)).toFile();
    }

    public List<String> downloadImages(int limit, String country, String state) throws Exception {

        List<Image> images = localRepository.getImagesAreNotDownloadedByCountryAndState(limit, country, state);
        for (Image image : images) {
            createDirectoriesNotPresent(Paths.get(targetPath, country, state).toString());
            if (closeableHttpClient == null) {
                closeableHttpClient = getHttpClient();
            }
            try {
                executeRequest(image.getImageUrl(), Paths.get(targetPath, country, state, image.getName()).toString());
                image.setRetrieved(true);
                image.setRetrievedDate(sdf.format(new Date()));
                localRepository.save(image);
            } catch (IOException e) {
                logger.error(e.getMessage() + " URL - " + image.getImageUrl());
                continue;
            }
        }
//        closeHttpClient();
        return images.stream().map(n -> String.join(", ", n.getName())).collect(Collectors.toList());
    }
//
//    private void executeRequest(String url, String imageTargetPath) throws Exception {
//
//        if (!WinHttpClients.isWinAuthAvailable()) {
//            throw new Exception("Windows authentication is not possible");
//        }
//        CloseableHttpClient httpclient = WinHttpClients.custom().build();
//        HttpGet httpGet = new HttpGet(url);
//        try {
//            httpclient.execute(httpGet, new ImageResponseHandler(new File(imageTargetPath)));
//        } catch (IOException e) {
//            throw new Exception("Error occurred while executing https request : " + e.getLocalizedMessage());
//        } finally {
//            httpclient.close();
//        }
//    }


    private void executeRequest(String url, String imageTargetPath) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        closeableHttpClient.execute(httpGet, new ImageResponseHandler(new File(imageTargetPath)));
    }

    private CloseableHttpClient getHttpClient() throws Exception {
        if (!WinHttpClients.isWinAuthAvailable()) {
            throw new Exception("Windows authentication is not possible");
        }
        return closeableHttpClient = WinHttpClients.custom().build();
    }

    private void closeHttpClient() throws IOException {
        closeableHttpClient.close();
    }
}
