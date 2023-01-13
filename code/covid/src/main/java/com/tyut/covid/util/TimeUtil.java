package com.tyut.covid.util;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 时间工具类
 */
public abstract class TimeUtil {
    public static String format(Long timestamp, String pattern) {
        return FastDateFormat.getInstance(pattern).format(timestamp);
    }


    public static void main(String[] args) {
        String format = TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd");
        System.out.println(format);
    }
}
