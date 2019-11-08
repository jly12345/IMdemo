package com.demo.tio.study.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: lingyun.jiang
 * @Date: 2019/11/7 15:15
 * @Description:
 */
public class DateUtil {
    private static final DateTimeFormatter YMD= DateTimeFormatter.ofPattern("YYYY-MM-dd");

    public static String getDateStr(Long createAt) {
        Date date = new Date(createAt);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return YMD.format(localDateTime);
    }

}
