package utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map combineMaps(Map baseMap, Map overwritingMap) {
        HashMap combinedMap = new HashMap();

        if (baseMap != null && !baseMap.isEmpty()) {
            combinedMap.putAll(baseMap);
        }

        if (overwritingMap != null && !overwritingMap.isEmpty()) {
            combinedMap.putAll(overwritingMap);
        }

        return combinedMap;
    }
}
