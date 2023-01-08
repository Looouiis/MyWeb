package me.looouiiis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.looouiiis.dao.ContentsDao;
import me.looouiiis.dao.HomeDao;
import me.looouiiis.pojo.Contents;
import me.looouiiis.pojo.Home;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    private HomeDao homeDao;
    private ContentsDao contentsDao;
    @Autowired
    public void setHomeDao(HomeDao homeDao) {
        this.homeDao = homeDao;
    }
    @Autowired
    public void setContentsDao(ContentsDao contentsDao) {
        this.contentsDao = contentsDao;
    }

    @Override
    public List<Home> getHomeList() {
        return homeDao.selectList(null);
    }

    @Override
    public boolean updateSingle(Home home) {
        int homePart = homeDao.updateById(home);
        String[] contents = home.getContents();
        LambdaQueryWrapper<Contents> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Contents::getHomeId, home.getId());
        contentsDao.delete(lqw);
        Contents tar = new Contents();
        tar.setHomeId(home.getId());
        if(contents != null)
            for (String content : contents) {
                tar.setContent(content);
                if(!(contentsDao.insert(tar) > 0))
                    return false;
            }
        return homePart > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return homeDao.deleteById(id) > 0;
    }

    @Override
    public boolean insert(Home home) {
        home.setId(0);
        int homePart = homeDao.insert(home);
        System.out.println(home.getId());
        String[] contents = home.getContents();
        Contents tar = new Contents();
        tar.setHomeId(home.getId());
        if(contents != null)
            for (String content : contents) {
                tar.setContent(content);
                if(!(contentsDao.insert(tar) > 0))
                    return false;
            }
        return homePart > 0;
    }
}
