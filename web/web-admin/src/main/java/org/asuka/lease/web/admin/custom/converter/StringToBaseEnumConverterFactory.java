package org.asuka.lease.web.admin.custom.converter;

import org.asuka.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 但是所有的枚举类可能都需要这样进行转换
 * 为了方便,可以使用ConverterFactory
 * 统一实现所有枚举类型的转换逻辑,这就是为什么需要使用BaseEnum接口
 * 1.实现ConverterFactory接口
 * 2.实现getConverter方法,返回值是一个Converter对象,能够实现S到T的转换
 * 3.其余代码基本上一致
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                //获取枚举类型的全部实例对象
                T[] ts = targetType.getEnumConstants();
                for (T t : ts) {
                    if (t.getCode().equals(Integer.valueOf(source))) {
                        return t;
                    }
                }
                throw new IllegalArgumentException("code:" + source + "非法");
            }
        };
    }
}
