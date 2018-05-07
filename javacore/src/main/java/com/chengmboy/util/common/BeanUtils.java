package com.chengmboy.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.chengmboy.util.exception.BeanConvertException;

/**
 * @author cheng_mboy
 */
public class BeanUtils {


    /**
     * 为目标对象的所有属性赋值，调用源对象get方法转换为目标对象相应属性值
     *
     * @param source 源对象
     * @param target 目标对象
     * @return 赋完值的目标对象
     * @throws BeanConvertException 如果源对象没有属性get方法或者目标对象没有set方法
     */
    public static <T> T convert(Object source, Class<T> target) {
        try {
            T t = target.newInstance();
            Class<?> sourceClass = source.getClass();
            Field[] fields = target.getFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Method getMethod = sourceClass.getMethod("get" + fieldName);
                Object v = getMethod.invoke(source);
                Method setMethod = target.getMethod("set" + fieldName, getMethod.getReturnType());
                setMethod.invoke(t, v);
            }
            return t;
        } catch (Exception e) {
            throw new BeanConvertException(e);
        }
    }
}
