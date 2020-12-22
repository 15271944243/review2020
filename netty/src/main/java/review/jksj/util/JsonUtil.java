package review.jksj.util;

import com.google.gson.Gson;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 16:00
 */
public final class JsonUtil {
    private static final Gson GSON = new Gson();

    private JsonUtil() {
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return GSON.fromJson(jsonStr, clazz);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }
}
