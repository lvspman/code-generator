package com.xiaolv.cli.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
@Data
@AllArgsConstructor
public class Device {
    String name;


    public void turnOff() {
        System.out.println(name + "关闭了");
    }

    public void turnOn() {
        System.out.println(name +"打开了");
    }
}
