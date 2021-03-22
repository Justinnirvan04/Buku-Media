package api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL="http://192.168.43.80:8000/api/";

    private static Retrofit getRetrofitClient(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static ApiInterface getService(){
        return getRetrofitClient().create(ApiInterface.class);
    }
}
