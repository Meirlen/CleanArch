package meirlen.cleanarch.utill.interceptors;




import java.io.IOException;


import android.support.annotation.NonNull;
import meirlen.cleanarch.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class add information (API Key) to {@link okhttp3.OkHttpClient} which is passed in
 * which is required when making a request. This will ensure that all requests are made with the API key
 *
 */
public class AuthInterceptor implements Interceptor {


    public AuthInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("key",  BuildConfig.TRELLO_API_KEY)
                .addQueryParameter("token", BuildConfig.TRELLO_TOKEN)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
