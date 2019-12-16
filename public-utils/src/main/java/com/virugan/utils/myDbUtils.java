package com.virugan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myDbUtils {

    //用于entity与 数据库表名称转换
    public static String toChangeTableNames(String tableNme){
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(tableNme);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
