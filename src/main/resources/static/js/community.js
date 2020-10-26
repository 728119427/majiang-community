/**
 * Created by codedrinker on 2019/6/1.
 */

/**
 * 提交回复,插入评论
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                if(type==1){
                    window.location.reload();
                }else {
                   // window.location.reload();
                    //展开评论
                    //清空input框
                    $("#input-" + targetId).val("");
                    var comments = $("#comment-" + targetId);
                    //清空二级评论得内容，避免重叠
                    let children = comments.children();
                    $.each(children,function (index,element) {
                            if (index==children.length-1){

                            }else {
                                element.remove();
                            }
                    });
                    var clickSpan=$("span[data-id='"+targetId+"']");
                    showSecondComment(targetId,comments,clickSpan);
                }

            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f4e182db283dfd9e81ac&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
    //展开评论
 /*   var comments = $("#comment-" + commentId);
    var clickSpan=$("span[data-id='"+commentId+"']");
   showSecondComment(commentId,comments,clickSpan);*/
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {

        //子元素得个数不等于一，说明已经查询数据库展开过了，就不用再查了，直接展开即可！
        if (comments.children().length > 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
        showSecondComment(id,comments,e)
        }
    }
}

function showSecondComment(firstCommentId,secondComment,clickSpan){
    $.getJSON("/comment/" + firstCommentId, function (data) {
        $.each(data.data, function (index, comment) {
            var mediaLeftElement = $("<div/>", {
                "class": "media-left"
            }).append($("<img/>", {
                "class": "media-object img-rounded",
                "src": comment.user.avatarUrl
            }));

            var mediaBodyElement = $("<div/>", {
                "class": "media-body"
            }).append($("<h5/>", {
                "class": "media-heading second",
                "html": comment.user.name
            })).append($("<div/>", {
                "html": comment.content
            })).append($("<div/>", {
                "class": "menu"
            }).append($("<span/>", {
                "class": "pull-right",
                "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
            })));

            var mediaElement = $("<div/>", {
                "class": "media"
            }).append(mediaLeftElement).append(mediaBodyElement);

            var commentElement = $("<div/>", {
                "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
            }).append(mediaElement);

            secondComment.prepend(commentElement);
        });
        //展开二级评论
        secondComment.addClass("in");
        // 标记二级评论展开状态
        clickSpan.setAttribute("data-collapse", "in");
        clickSpan.classList.add("active");
    });
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}