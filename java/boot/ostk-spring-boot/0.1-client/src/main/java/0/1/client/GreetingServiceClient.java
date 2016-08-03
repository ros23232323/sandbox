package 0.1.client;

import 0.1.common.Greeting;
import com.overstock.shadow.retrofit.RetrofitShadow;

import retrofit2.Call;
import retrofit2.http.GET;

@RetrofitShadow("0.1")
public interface GreetingServiceClient {
  @GET("/greeting")
  public Call<Greeting> greeting();
}
