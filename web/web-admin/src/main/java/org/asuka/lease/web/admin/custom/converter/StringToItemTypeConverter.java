package org.asuka.lease.web.admin.custom.converter;

import org.asuka.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 为了解决这个报错
 * Failed to convert value of type 'java.lang.String' to required type 'org.asuka.lease.model.enums.ItemType';
 * 当@RequestParam参数类型不是String类型时,就会使用WebDataBinder进行类型转换
 * 默认提供了String到Integer类型等等的转换,
 * 虽然提供了字符串到枚举类的默认转换,但是是按照枚举类的名称进行映射的,比如?type=ROOM这样传值能获取
 * 可以自定义转换的规则
 * 1.实现Converter接口,泛型1是原始类型,泛型2是目标类型
 * 2.实现convert方法,参数是原始类型的数据,指的是?type=1里面的value值1
 * 3.添加到IOC管理,可以使用@Component注解
 * 4.在WebMvcConfiguration配置类当中的addFormatters方法当中,使用addConverter方法添加Converter
 */
@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String source) {
//        if ("1".equals(source)) {
//            return ItemType.APARTMENT;
//        } else if ("2".equals(source)) {
//            return ItemType.ROOM;
//        } else {
//            throw new IllegalArgumentException("code:" + source + "非法");
//        }
        //更明智的写法
//        ItemType[] itemTypes = ItemType.class.getEnumConstants();//这样也能获取所有的枚举类型
        for (ItemType value : ItemType.values()) {
            if (value.getCode().equals(Integer.valueOf(source))) {
                return value;
            }
        }
        throw new IllegalArgumentException("code:" + source + "非法");
    }
}
