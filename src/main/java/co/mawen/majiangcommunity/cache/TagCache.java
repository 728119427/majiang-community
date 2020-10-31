package co.mawen.majiangcommunity.cache;



import co.mawen.majiangcommunity.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by codedrinker on 2019/6/5.
 */
public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();


        TagDTO game = new TagDTO();
        game.setCategoryName("游戏");
        game.setTags(Arrays.asList("炉石传说", "守望先锋", "暴雪", "英雄联盟", "绝地求生", "原神", "剑网3", "DNF", "CF", "CS:GO", "DoTA2", "自走棋", "剑网3", "冒险岛", "梦幻西游", "主机游戏", "单机", "3A大作", "qq飞车", "王者荣耀", "阴阳师", "和平精英", "手游", "网游", "页游", "桌游", "其他游戏"));
        tagDTOS.add(game);

        TagDTO entertainment = new TagDTO();
        entertainment.setCategoryName("影音娱乐");
        entertainment.setTags(Arrays.asList("火影忍者", "海贼王", "死神", "炎炎消防队", "期待在地下城邂逅有错吗", "请问您今天要来点兔子吗", "魔法科高校的劣等生", "鬼灭之刃", "罪恶王冠", "神奇宝贝", "数码宝贝", "进击的巨人", "希德尼娅的骑士", "来自新世界", "弹丸论破", "尸鬼", "粗点心战争", "钢之炼金术师", "妖精的尾巴", "家庭教师", "银魂", "野良神", "\n" +
                "新世纪福音战士", "网球王子", "命运石之门", "全职猎人", "反叛的鲁鲁修", "Fate/Zero","其他动漫"));
        tagDTOS.add(entertainment);

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java", "php", "css", "html", "html5", "javascript", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tool);

        TagDTO others=new TagDTO();
        others.setCategoryName("其他");
        others.setTags(Arrays.asList("游戏","动漫","漫画","巨乳","超级","宝贝","神奇","比卡丘","老夫","神","广告","招聘","电视剧","美剧","日剧","韩剧"));
        tagDTOS.add(others);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] inputTags = tags.split(",");
        List<String> allTags = get().stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        String invaildTags = Arrays.stream(inputTags).filter(tag -> !allTags.contains(tag)).collect(Collectors.joining(","));
        return invaildTags;
    }

    public static void main(String[] args) {
        int i = (5 - 1) >>> 1;
        System.out.println(i);
    }
}
