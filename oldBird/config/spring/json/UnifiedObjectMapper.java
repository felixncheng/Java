package com.ecaicn.hms.framework.json;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 平台标准的JSON序列化器
 *
 * @author Raven
 */
public class UnifiedObjectMapper extends ObjectMapper {

    protected static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public UnifiedObjectMapper() {
        super();
        // 长整形转换成字符串，避免使用JS读取精度丢失问题
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.registerModule(simpleModule);
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDateFormat(new SimpleDateFormat(DATE_FORMAT));
    }

}
