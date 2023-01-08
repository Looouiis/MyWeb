package me.looouiiis.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("contents")
public class Contents {
    @TableField(value = "home_id")
    private int homeId;
    private String content;

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "homeId=" + homeId +
                ", content='" + content + '\'' +
                '}';
    }
}
