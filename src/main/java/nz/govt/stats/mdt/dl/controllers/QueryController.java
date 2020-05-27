package nz.govt.stats.mdt.dl.controllers;

import nz.govt.stats.mdt.dl.common.model.Country;
import nz.govt.stats.mdt.dl.exceptions.InvalidInputException;
import nz.govt.stats.mdt.dl.localrepo.WorkItemMetaRepository;
import nz.govt.stats.mdt.dl.services.ImageDownloader;
import nz.govt.stats.mdt.dl.setstore.WorkItemFixRepository;
import nz.govt.stats.mdt.dl.setstore.WorkItemFixRepositoryBase;
import nz.govt.stats.mdt.dl.services.SamplesService;
import nz.govt.stats.mdt.dl.localrepo.LocalRepository;
import nz.govt.stats.mdt.dl.setstore.models.WorkItemFix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryController {

    private final SamplesService samplesService;
    private final WorkItemFixRepository workItemRepository;
    private final WorkItemFixRepositoryBase workItemFixRepositoryBase;
    private final LocalRepository localRepository;
    private final WorkItemMetaRepository workItemMetaRepository;
    private final ImageDownloader imageDownloader;

//    public QueryController(SqliteRepository sqliteRepository) {
//        this.sqliteRepository = sqliteRepository;
//    }

    public QueryController(WorkItemFixRepository workItemRepository, SamplesService samplesService, WorkItemFixRepositoryBase workItemFixRepositoryBase, LocalRepository localRepository, WorkItemMetaRepository workItemMetaRepository, ImageDownloader imageDownloader) {
        this.samplesService = samplesService;
        this.workItemRepository = workItemRepository;
        this.workItemFixRepositoryBase = workItemFixRepositoryBase;
        this.localRepository = localRepository;
        this.workItemMetaRepository = workItemMetaRepository;
        this.imageDownloader = imageDownloader;
    }

//    @GetMapping("/images")
//    List<Image> all() {
//        return imageRepository.findAll();
//    }

//    @GetMapping("/workitem")
//    List<WorkItemFix> getN(int limit) {
//        String au = "{\"code\":\"AU007%";
////        return workItemRepository.findByQuery(45, "overseas_state", au);
////        return workItemRepository.findTop10By();
//        return workItemFixRepositoryBase.findWorkitemsByLimit(limit);
//    }

//    @GetMapping("/workitem/count")
//    long getCount() {
//        return workItemRepository.count();
//    }

    @GetMapping("/samples/country/state")
    String getSamplesForCountryState(String country, String state, Integer numberOfSamples) {
        String result ="%d work items retrieved";
        if (!validateCountry(country)) {
            throw new InvalidInputException("Country");
        }
        if (!validateState(country, state)) {

            throw new InvalidInputException("State");
        }

        if (numberOfSamples != null) {
            // Return that many samples
            List<WorkItemFix> fixes = samplesService.getSamplesForCountryState(country, state, numberOfSamples);
            return String.format(result, fixes.size()) ;
        }
        List<WorkItemFix> fixes =samplesService.getSamplesForCountryState(country, state);
        return String.format(result, fixes.size());
    }

//    @GetMapping("/sqlite")
//    List<Image> getSqlite() {
//        return localRepository.findAll(Sort.by("name"));
//    }

    @GetMapping("/sqlite/download")
    List<String> downloadSamples(int numberOfSamples, String country, String state) throws Exception {
        return imageDownloader.downloadImages(numberOfSamples, country, state);
    }

//    @GetMapping("/sqlite/delete/image")
//    String getSqliteDelTest(int id) {
//        workItemMetaRepository.deleteById (id);
//        return id + " is deleted from Image table";
//    }

//    @PostMapping("/sqlite/image")
//    String postImage() {
//        localRepository.save(new Image("image2", "url 2", "2020-05-11 16:26:38", ));
//        return "image saved";
//    }

    private boolean validateState(String country, String state) {
        List<String> states = Country.getCountryStateCode().get(country.toLowerCase());
        return states.stream().anyMatch(c -> c.equalsIgnoreCase(state));
    }

    private boolean validateCountry(String country) {
        return Country.getCountryStateCode().containsKey(country.toLowerCase());
    }

}
