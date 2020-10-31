package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.model.Ad;

import java.util.List;

public interface AdService {

    /**
     * 根据位置获取广告
     * @param pos
     * @return
     */
    List<Ad> list(String pos);
}
