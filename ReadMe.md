##注意事项
mybatis generator执行命令： 
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

##side
```html
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 side" style="margin-top: 10px">
        <h4><img src="/images/find1.png" width="35px" height="30px" style="margin-left: -3px;margin-right: 1px;
        margin-bottom: 6px;margin-top: -5px;">每日发现</h4>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 side"
             th:if="${#lists.size(#httpServletRequest.getServletContext().getAttribute('INDEX_SIDE')) != 0}"
             th:each="ad : ${#httpServletRequest.getServletContext().getAttribute('INDEX_SIDE')}">
            <a th:href="${ad.url}" th:onclick="clickSide([[${ad.title}]])"
               target="_blank">
                <img class="img-thumbnail question-wechat" th:src="${ad.image}">
            </a>
        </div>



    </div>
```