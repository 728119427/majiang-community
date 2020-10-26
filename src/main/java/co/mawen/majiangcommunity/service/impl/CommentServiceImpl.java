package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.enums.CommentEnum;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;
import co.mawen.majiangcommunity.mapper.*;
import co.mawen.majiangcommunity.model.*;
import co.mawen.majiangcommunity.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;

    @Override
    public void insert(Comment comment) {
        Long parentId = comment.getParentId();
        Integer type = comment.getType();
        //未针对任何问题或评论进行回复
       if(parentId==null || parentId==0){
           throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
       }
       //评论类型不存在
       if(  type==null || !CommentEnum.isCommentType(type)){
           throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
       }

       if(type==1){
           //针对问题回复
           Question question = questionMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(parentId)));
           if(question==null){
               throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
           }
           //插入评论，且问题评论数加1
           commentMapper.insertSelective(comment);
           question.setCommentCount(1);
           questionExtMapper.incCommentCount(question);

       }else {
           //针对评论回复
           Comment firstComment = commentMapper.selectByPrimaryKey(parentId);
           if(firstComment==null){
               throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
           }
           commentMapper.insertSelective(comment);
           //增加评论数
           firstComment.setCommentCount(1);
           commentExtMapper.incCommentCount(firstComment);

       }

    }


    /**
     * 查询一级评论
     * @param id
     * @param commentEnum
     * @return
     */
    @Override
    public List<CommentDTO> listByParentId(Integer id, CommentEnum commentEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(Long.parseLong(String.valueOf(id))).andTypeEqualTo(commentEnum.getCode());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments==null || comments.size()==0) return new ArrayList<CommentDTO>();

        //获取评论得userId
        Set<Integer> commentatorIds = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //根据id查找所有user
        ArrayList<Integer> ids = new ArrayList<>();
        ids.addAll(commentatorIds);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(ids);
        List<User> users = userMapper.selectByExample(userExample);
        //将users转换为map
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //将comment转换为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    @Override
    public List<Comment> listByQuestionId2(Integer id) {
        return commentExtMapper.unionListByQuestionId(id);
    }
}
