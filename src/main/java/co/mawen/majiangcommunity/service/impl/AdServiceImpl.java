package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.mapper.AdMapper;
import co.mawen.majiangcommunity.model.Ad;
import co.mawen.majiangcommunity.model.AdExample;
import co.mawen.majiangcommunity.service.AdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    @Override
    public List<Ad> list(String pos) {
        AdExample adExample = new AdExample();
        adExample.createCriteria().andPosEqualTo(pos)
                .andStatusEqualTo(1)
                .andGmtStartLessThan(System.currentTimeMillis())
                .andGmtEndGreaterThan(System.currentTimeMillis());
        return adMapper.selectByExample(adExample);
    }
}
