package co.mawen.majiangcommunity.dto;

import co.mawen.majiangcommunity.model.Question;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> implements Serializable {
    private List<T> dataList;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndpage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();//需要在导航条显示的页码

    public void setPagination(int page,long totalCount,int size){
        //设置总页数
        this.totalPage=(int)(totalCount%size==0?totalCount/size:(totalCount/size + 1));

        //设置当前页码数
        //注意totalpage可能为0，这样会使page=0，从而使offset=(page-1)*size是负数，所以最好先验证page>totalpage,再验证page<1
        if(page>totalPage){
            page=totalPage;
        }
        if(page<1){
            page=1;
        }
        this.page=page;
        //设置pages
        for(int i = 1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
        }
        pages.add(this.page);

        for(int i = 1;i<=3;i++){
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //设置showPrevious
        if(this.page<=1){
            this.showPrevious=false;
        }else {
            this.showPrevious=true;
        }
        //设置showNext
        if(this.page>=totalPage){
            this.showNext=false;
        }else {
            this.showNext=true;
        }
        //设置showFirstPage
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //设置showEndpage
        if(pages.contains(totalPage)){
            showEndpage=false;
        }else{
            showEndpage=true;
        }
    }

}
