package com.company.project.framework.converter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/7/12 9:48 上午
 * @Description：该代码来源：https://my.oschina.net/u/2608182/blog/2877624
 **/
public class DateJacksonConverter extends JsonDeserializer<Date> {
    private static String[] pattern =
            new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S"};


    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Date targetDate = null;
        String originDate = p.getText();
        if (StrUtil.isNotEmpty(originDate)) {
            try {
                String originDateTrim = originDate.trim();
                long longDate = Long.parseLong(originDateTrim);
                targetDate = new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    targetDate = DateUtils.parseDate(originDate, DateJacksonConverter.pattern);
                } catch (ParseException pe) {
                    throw new IOException(String.format(
                            "'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)",
                            originDate,
                            StrUtil.join(",", pattern)));
                }
            }
        }


        return targetDate;
    }

    @Override
    public Class<?> handledType() {
        return Date.class;
    }
}
