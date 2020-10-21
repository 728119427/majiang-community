package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.GithubDTO;
import co.mawen.majiangcommunity.dto.GithubUser;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.provider.GithubProvider;
import co.mawen.majiangcommunity.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private UserService userService;

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response){
        //获取code用于交换access_token
        GithubDTO githubDTO = new GithubDTO();
        githubDTO.setClient_id("f4e182db283dfd9e81ac");
        githubDTO.setClient_secret("6ceb76dd3a8ece11ce0aae2e994fadc37592de3d");
        githubDTO.setRedirect_uri("http://localhost:8080/callback");
        githubDTO.setCode(code);
        githubDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(githubDTO);
        //获取access_token之后使用access_token获取用户数据
        String userData = githubProvider.getUserData(accessToken);
        GithubUser githubUser = JSON.parseObject(userData, GithubUser.class);
        if(githubUser!=null){
            //登录成功，保存用户信息
            System.out.println(githubUser);
            User user = new User();
            user.setAccountId(Long.parseLong(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            String token = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.insert(user);
            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setMaxAge(604800);//一周有效
            response.addCookie(tokenCookie);
            return "redirect:";
        }else {
            //登录失败
            return "redirect:";
        }

    }
}
