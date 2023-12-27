package com.xiaolv.cli.command;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */

import cn.hutool.core.util.ReflectUtil;
import com.xiaolv.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * 查看参数信息
 */

@Command(name = "config",
        description = "查看参数信息",
        mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("查看参数信息：");

        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field: fields) {
            System.out.println(field.getType());
            System.out.println(field.getName());
        }
    }
}
