package me.looouiiis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Arrays;

@TableName("home")
public class Home {
    @TableId(type = IdType.AUTO)
    private int id;
    private String header;
    @TableField(value = "img_name")
    private String imgName;
    private int showRank;
    @TableField(exist = false)
    private String[] contents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getShowRank() {
        return showRank;
    }

    public void setShowRank(int showRank) {
        this.showRank = showRank;
    }

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", imgName='" + imgName + '\'' +
                ", showRank=" + showRank +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}
