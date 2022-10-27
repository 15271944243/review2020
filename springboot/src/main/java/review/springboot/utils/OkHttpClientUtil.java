package review.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/10/27 17:26
 */
@Slf4j
public class OkHttpClientUtil {

    private static OkHttpClient client = new OkHttpClient().newBuilder()
            .callTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1,TimeUnit.SECONDS)
            .readTimeout(1,TimeUnit.SECONDS)
            .build();

    public static String post(String url, String body){
        Response execute = null;
        Request request = new Request.Builder()
                .method("POST", RequestBody.create(body, MediaType.get("application/json")))
                .url(url)
                .build();
        try {
            execute = client.newCall(request).execute();
            if (execute != null && execute.isSuccessful()){
                return execute.body().toString();
            }
        } catch (IOException e) {
            log.error("http post 请求失败--{}", e);
        }
        return null;
    }
}
