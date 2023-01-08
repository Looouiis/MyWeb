package me.looouiiis.service.impl;

import me.looouiiis.dao.HeaderDao;
import me.looouiiis.pojo.Header;
import me.looouiiis.service.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class HeaderServiceImpl implements HeaderService {
    private HeaderDao headerDao;
    @Autowired
    public void setHeaderDao(HeaderDao headerDao) {
        this.headerDao = headerDao;
    }

    @Override
    public Header select() {
        return headerDao.selectOne(null);
    }

    @Override
    public boolean update(Header header) {
        return headerDao.update(header, null) > 0;
    }
}
