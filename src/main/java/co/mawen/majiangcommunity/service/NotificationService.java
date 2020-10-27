package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.dto.NotificationDTO;
import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.model.Notification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NotificationService {

    /**
     * 获取通知列表并分页
     * @param userId
     * @param page
     * @param size
     * @return
     */
    PaginationDTO<NotificationDTO> pagination(Integer userId,Integer page,Integer size);

    /**
     * 根据userId查询user的未读通知
     * @param userId
     * @return
     */
    Long unReadCount(Integer userId);

    Notification read(Long notificationId, Integer userId);
}
