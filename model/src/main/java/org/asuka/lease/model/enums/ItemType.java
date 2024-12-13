package org.asuka.lease.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ItemType implements BaseEnum {

    APARTMENT(1, "公寓"),

    ROOM(2, "房间");


    /*
    TypeHandler有默认转换的规则,尤其是枚举类型也有
    但是默认的转换规则是将枚举的实例对象转换成实例的名称
    也就是ItemType.ROOM转换成ROOM字符串
    为了解决TypeHandler转换过程中,将枚举对象转换成对应的code
    使用下面的@EnumValue注解
     */
    @EnumValue
    /*
    为了将枚举类型转换成对应的code值响应前端
    需要在HttpMessageConverter进行相关设置
    由于HttpMessageConverter依赖类jackson进行的json转换然后进行响应
    默认的转换规则是将枚举的实例对象转换成实例的名称
    所以可以简单的使用@JsonValue注解解决这个问题
     */
    @JsonValue
    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }


    @Override
    public String getName() {
        return name;
    }

    ItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
