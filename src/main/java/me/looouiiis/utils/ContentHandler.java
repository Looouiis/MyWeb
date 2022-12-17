package me.looouiiis.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentHandler {
    public static String handle(String path, String content){
        File dir = new File(path);
        dir.mkdirs();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String filePath = path + df.format(new Date()) + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return filePath;
    }
    public static boolean change(String path, String content){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
