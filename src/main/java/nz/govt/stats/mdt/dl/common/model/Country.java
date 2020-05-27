package nz.govt.stats.mdt.dl.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Country {

    public static Map<String, List<String>> getCountryStateCode() {
        return countryStateCode;
    }

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
     **/
    private static Map<String, List<String>> countryStateCode = new HashMap<String, List<String>>() {{
        put("australia", new ArrayList<String>() {{
                    add("AU001");
                    add("AU002");
                    add("AU003");
                    add("AU004");
                    add("AU005");
                    add("AU006");
                    add("AU007");
                    add("AU008");
                }}
        );
    }};
}
