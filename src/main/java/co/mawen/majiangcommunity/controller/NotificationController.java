package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.model.Notification;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String readNotification(@PathVariable("id") Long notificationId, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null)return "redirect:/";
        Notification notification=notificationService.read(notificationId,user.getId());
        return "redirect:/question/"+notification.getOuterid();
    }

}
