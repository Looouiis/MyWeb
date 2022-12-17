package me.looouiiis.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class Announce {
    private AnnounceService announceService;
    @Autowired
    public void setAnnounceService(AnnounceService announceService) {
        this.announceService = announceService;
    }
    @PostMapping(value = "/announce" )
    @ResponseBody
    public String postAnnounceWithPer(HttpServletRequest request, String content, Integer id){
        JsonContentReturn ret = new JsonContentReturn();
        Integer insert = announceService.insert(content, id);
        if(insert != null){
            ret.setStatus(true);
            ret.setDescription("success");
            ret.setContent(null);
        }
        else{
            ret.setStatus(false);
            ret.setDescription("failed");
            ret.setContent(null);
        }
        return JSON.toJSONString(ret);
    }
    @GetMapping(value = "/announce")
    @ResponseBody
    public String getAnnounce(Integer page, Integer num){
        Integer start = null;
        if (page != null && num != null && page > 0) {
            start = num * (page - 1);
        } else if (page == null && num != null) {
            start = 0;
        }
        HashMap<String, Object> res = announceService.select(start, num);
        List<me.looouiiis.pojo.Announce> selects = (List<me.looouiiis.pojo.Announce>) res.get("selects");
        Integer totalCount = (Integer) res.get("totalCount");
        JsonContentReturn ret = new JsonContentReturn();
        if(selects.size() != 0){
            ret.setStatus(true);
            ret.setDescription("success");
        }
        else{
            ret.setStatus(false);
            ret.setDescription("failed");
        }
        ret.setContent(selects);
        ret.setTotalCount(totalCount);
        return JSON.toJSONString(ret);
    }
    @DeleteMapping(value= "/announce")
    @ResponseBody
    public String deleteAnnounceWithPer(HttpServletRequest request, int[] ids){
        JsonContentReturn ret = new JsonContentReturn();
        Integer delete = announceService.delete(ids);
        if(delete != null) {
            ret.setDescription("success");
            ret.setContent(null);
            ret.setStatus(true);
        }else {
            ret.setDescription("fail");
            ret.setContent(null);
            ret.setStatus(false);
        }
        return JSON.toJSONString(ret);
    }
}
