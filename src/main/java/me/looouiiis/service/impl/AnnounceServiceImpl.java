package me.looouiiis.service.impl;

import me.looouiiis.controller.Code;
import me.looouiiis.dao.AnnounceDao;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.Announce;
import me.looouiiis.pojo.UsrComment;
import me.looouiiis.service.AnnounceService;
import me.looouiiis.utils.ContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    private AnnounceDao announceDao;

    @Autowired
    public void setAnnounceDao(AnnounceDao announceDao) {
        this.announceDao = announceDao;
    }

    @Override
    public Integer insert(String content, Integer id) {
        if (id == null) {
            Announce announce = new Announce();
            announce.setDate(new Date());
            if (content.length() > 100) {
                String path = "../AllMsg/Announce/";
                String filepath = ContentHandler.handle(path, content);
                if ("".equals(filepath)) {
                    return null;
                }
                announce.setContent(filepath);
            } else {
                announce.setContent(content);
            }
            announce.setLocal(content.length() > 100);
            return announceDao.insert(announce);
        } else {
            if (content.length() > 100) {
                Announce announce = announceDao.selectById(id);
                if (ContentHandler.change(announce.getContent(), content))
                    return 1;
                else
                    return null;
            } else {
                return announceDao.update(content, id);
            }
        }
    }

    @Override
    public Integer insert(String message) {
        return insert(message, null);
    }

    @Override
    public HashMap<String, Object> select(Integer start, Integer num) {
        List<Announce> selects = announceDao.select(start, num);
        for (Announce select : selects) {
            if (select.isLocal()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(select.getContent()));
                    StringBuilder sb = new StringBuilder();
                    String tmp = br.readLine();
                    sb.append(tmp);
                    while ((tmp = br.readLine()) != null) {
                        sb.append("\n").append(tmp);
                    }
                    select.setContent(sb.toString());
                    br.close();
                } catch (IOException e) {
                    throw new SystemException(Code.SYSTEM_IO_ERR, "后台文件读写时发生异常，已开启事务所以您的数据是安全的不用担心，您可重试或想办法联系我", e);
                }
            }
        }
        Integer totalCount = announceDao.getTotalCount();
        HashMap<String, Object> res = new HashMap<>();
        res.put("selects", selects);
        res.put("totalCount", totalCount);
        return res;
    }

    @Override
    public Integer delete(int[] ids) {
        return announceDao.delete(ids);
    }

    @Override
    public String getMyName() {
        return announceDao.getMyName();
    }
}
