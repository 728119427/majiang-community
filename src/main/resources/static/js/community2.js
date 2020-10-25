/**
 * Created by codedrinker on 2019/6/1.
 */

/**
 * 提交回复
 */
function post() {
    let questionVal = $("#question_id").val();
    let connent = $("#comment_content").val();
   comment(1,questionVal,connent);
}

function comment(type,parentId,content) {
    if(!content){
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        url: "/comment",
        type:"post",
        contentType:"application/json;charset=utf-8",
        data:JSON.stringify({
           "type":type,
           "parentId":parentId,
           "content":content
        }),
        success: function(response){
            if(response.code==200){
                window.location.reload();
            }else if(response.code==2003){
                let confirm = window.confirm(response.message);
                if(confirm){
                    window.open("https://github.com/login/oauth/authorize?client_id=f4e182db283dfd9e81ac&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", true);
                }
            }else{
                alert(response.message);
            }
        },
        dataType: "json"
    })

}