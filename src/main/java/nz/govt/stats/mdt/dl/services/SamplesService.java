package nz.govt.stats.mdt.dl.services;

import nz.govt.stats.mdt.dl.localrepo.LocalRepository;
import nz.govt.stats.mdt.dl.localrepo.WorkItemMetaRepository;
import nz.govt.stats.mdt.dl.localrepo.models.Image;
import nz.govt.stats.mdt.dl.localrepo.models.WorkItemMeta;
import nz.govt.stats.mdt.dl.setstore.CollectionInstanceRepository;
import nz.govt.stats.mdt.dl.setstore.models.CollectionInstance;
import nz.govt.stats.mdt.dl.setstore.models.WorkItemFix;
import nz.govt.stats.mdt.dl.setstore.WorkItemFixRepository;
import nz.govt.stats.mdt.dl.setstore.WorkItemFixRepositoryBase;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class SamplesService {

    private final WorkItemFixRepository workItemRepository;
    private final WorkItemFixRepositoryBase workItemFixRepositoryBase;
    private final LocalRepository localRepository;
    private final WorkItemMetaRepository workItemMetaRepository;
    private final CollectionInstanceRepository collectionInstanceRepository;
    private int collectionInstanceId = 45;
    private String variableName = "overseas_state";
    private String finalValue = "{\"code\":";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String baseUrl = "https://epl-prd/statsnz-epl-responsestore/api/v1/collections/ITM/%s/virtualimages/%s?question=OverseasState";
//    private String collectionInstanceCode = "201905";

    /**
     * Here is the list for each of the states
     * AU001  Australian Capital Territory
     * AU002  New South Wales
     * AU003  Northern Territory
     * AU004  Queensland
     * AU005  South Australia
     * AU006  Tasmania
     * AU007  Victoria
     * AU008  Western Australia
     * **/

    public SamplesService(WorkItemFixRepository workItemRepository, WorkItemFixRepositoryBase workItemFixRepositoryBase, LocalRepository localRepository, WorkItemMetaRepository workItemMetaRepository, CollectionInstanceRepository collectionInstanceRepository) {
        this.workItemRepository = workItemRepository;
        this.workItemFixRepositoryBase = workItemFixRepositoryBase;
        this.localRepository = localRepository;
        this.workItemMetaRepository = workItemMetaRepository;
        this.collectionInstanceRepository = collectionInstanceRepository;
    }

    private long getLastRetrievedWorkItemId(String country, String state) {
        WorkItemMeta meta = workItemMetaRepository.findFirstBycountryAndstate(country, state);
        if (meta == null) {
            return 1;
        }
        return meta.getLast_retrieved_work_item_id();
    }

    public List<WorkItemFix> getSamplesForCountryState(String country, String state, int numberOfSamples) {
        String stateToken = finalValue + "\"" + state + "%";
        List<WorkItemFix> workItems = workItemFixRepositoryBase.findWorkitemsByParameters(variableName, stateToken, getLastRetrievedWorkItemId(country, state), numberOfSamples);
        List<Image> images = processSampleRequest(workItems, country, state);
        insertRecordsToLocalRepo(images);
        workItems.sort(Comparator.comparing(WorkItemFix::getWork_item_fix_id));
        WorkItemFix lastRetrievedItem = workItems.get(workItems.size() -1);
        if (getLastRetrievedWorkItemId(country, state) != 1) {
            workItemMetaRepository.deleteByCountryAndState(country, state);
        }
        workItemMetaRepository.save(new WorkItemMeta(lastRetrievedItem.getWork_item_fix_id(), workItems.size(), country, state));
        return workItems;
    }

    public List<WorkItemFix> getSamplesForCountryState(String country, String state) {
        return workItemFixRepositoryBase.findWorkitemsByParameters(collectionInstanceId, variableName, finalValue, getLastRetrievedWorkItemId(country, state), 5);
    }

    private List<Image> processSampleRequest(List<WorkItemFix> workItems, String country, String state) {
        List<Image> images = new ArrayList<>();
        List<CollectionInstance> instances = collectionInstanceRepository.findAllITMCollectionInstancesByQuery();
        // order by workitem
        for (WorkItemFix workItem : workItems) {
            String collectionInstanceCode = instances.stream().filter(c -> c.getCollection_instance_id() == workItem.getCollection_instance_id()).findFirst().get().getCollection_instance_code();
            String url = String.format(baseUrl, collectionInstanceCode, workItem.getResponse_id());
            String name = country + "_" + state + "_" + workItem.getWork_item_fix_id() + ".png";
            Date now = new Date();
            String timeStamp = sdf.format(now);
            images.add( new Image(name, url, timeStamp, country, state, collectionInstanceCode) );
        }
        return images;
    }

    private void insertRecordsToLocalRepo(List<Image> images) {
        for (Image image : images) {
            if ( localRepository.findByname(image.getName()) == null) {
                localRepository.save(image);
            }
        }
    }
}
