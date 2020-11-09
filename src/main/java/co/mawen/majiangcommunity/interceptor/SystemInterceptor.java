package co.mawen.majiangcommunity.interceptor;

import co.mawen.majiangcommunity.enums.AdPosEnum;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.AdService;
import co.mawen.majiangcommunity.service.NotificationService;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SystemInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AdService adService;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置 context 级别的属性
        request.getServletContext().setAttribute("redirectUri", redirectUri);

        for(AdPosEnum adPosEnum: AdPosEnum.values()){
            request.getServletContext().setAttribute(adPosEnum.name(),adService.list(adPosEnum.name()));
        }

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String value = cookie.getValue();
                    User user = userService.findByToken(value);
                    if(user!=null){
                        Long unreadCount = notificationService.unReadCount(user.getId());
                        request.getSession().setAttribute("unreadCount",unreadCount);
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return true;
    }
}
