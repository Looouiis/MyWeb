package me.looouiiis.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("header")
public class Header {
    private String header;
    private String content;
    @TableField(value = "img_name")
    private String imgName;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "Header{" +
                "header='" + header + '\'' +
                ", content='" + content + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
