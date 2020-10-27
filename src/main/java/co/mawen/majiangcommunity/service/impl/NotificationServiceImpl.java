package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.dto.NotificationDTO;
import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.enums.NotifiactionTypeEnum;
import co.mawen.majiangcommunity.enums.NotificationStatusEnum;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;
import co.mawen.majiangcommunity.mapper.NotificationMapper;
import co.mawen.majiangcommunity.model.Notification;
import co.mawen.majiangcommunity.model.NotificationExample;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.QuestionExample;
import co.mawen.majiangcommunity.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public PaginationDTO<NotificationDTO> pagination(Integer userId, Integer page, Integer size) {
        //获取通知总数
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(Long.parseLong(String.valueOf(userId)));
        //获取未读通知总数
        Long totalCount = notificationMapper.countByExample(notificationExample);
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=paginationDTO.getPage();
        //查询notification
        notificationExample.clear();
        notificationExample.createCriteria().andReceiverEqualTo(Long.parseLong(String.valueOf(userId)));
        notificationExample.setOrderByClause(" gmt_create DESC");
        notificationExample.setOffset((page - 1) * size);
        notificationExample.setLimit(size);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        //将notification转换为notificationDTO
        List<NotificationDTO> notificationDTOS = notifications.stream().map(notification -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotifiactionTypeEnum.nameOfType(notification.getType()));
            return notificationDTO;
        }).collect(Collectors.toList());
        //设置list
        paginationDTO.setDataList(notificationDTOS);
        return paginationDTO;
    }

    @Override
    public Long unReadCount(Integer userId) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(Long.parseLong(String.valueOf(userId)))
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        //获取未读通知总数
        Long totalCount = notificationMapper.countByExample(notificationExample);

        return totalCount;
    }

    @Override
    public Notification read(Long notificationId, Integer userId) {
        Notification notification = notificationMapper.selectByPrimaryKey(notificationId);
        if(notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(notification.getReceiver()!=Long.parseLong(String.valueOf(userId))){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        return notification ;
    }
}
