package co.mawen.majiangcommunity.provider;

import co.mawen.majiangcommunity.dto.GithubDTO;
import com.alibaba.fastjson.JSON;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(GithubDTO githubDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(githubDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String resp = response.body().string();
            String access_token= resp.split("&")[0].split("=")[1];
            return access_token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserData(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user").header("Authorization","token "+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String userData = response.body().string();
            return userData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
