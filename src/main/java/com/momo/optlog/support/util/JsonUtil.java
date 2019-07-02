package com.momo.optlog.support.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.List;

/**
 * fastjson utils
 *
 * @author moqinggen
 * @date 2019/07/01
 */
public class JsonUtil {

    private static SerializeConfig mapping = new SerializeConfig();

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * javaBean、list、map convert to json string
     *
     * @param obj
     * @return
     */
    public static String obj2json(Object obj) {
        return JSON.toJSONString(obj, mapping);
    }

    /**
     * javaBean、list、map convert to json string
     *
     * @param obj
     * @param formatted
     * @return
     */
    public static String obj2Json(Object obj, Boolean formatted) {
        // 在上面的代码中添加下面的这行代码，则可以将转换后的字段名称的引号去掉。
        // serializer.config(SerializerFeature.QuoteFieldNames, false);
        // SerializerFeature.UseSingleQuotes 使用单引号
        // 格式化数据，方便阅读
        return formatted ? JSON.toJSONString(obj, mapping, SerializerFeature.PrettyFormat) : JSON.toJSONString(obj, mapping);
    }

    /**
     * json string convert to javaBean、map
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T json2obj(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * json array string convert to list with javaBean
     *
     * @param jsonArrayStr
     * @param clazz
     * @return
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        return JSON.parseArray(jsonArrayStr, clazz);
    }

}
