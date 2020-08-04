package review.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 08:51
 */
public class GenericSub<T extends GenericParent<T>> {

    private Map<String, T> map = new HashMap<>();

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }
}
