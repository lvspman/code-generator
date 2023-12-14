package com.xiaolv.generator;

import com.xiaolv.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
//        模板路径
        String inputPath = System.getProperty("user.dir")+ File.separator+"lvDiyCode-basic"+File.separator +"src/main/resources/templates";
//        输出路径
        String outputPath = System.getProperty("user.dir")+ File.separator + "MainTemplate.java";

//        数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setName("xiaolv");
        mainTemplateConfig.setOutputText("小吕有点帅哦");
        mainTemplateConfig.setLoop(false);


        doGenerate(inputPath,outputPath,mainTemplateConfig);
    }

    /**
     * 生成文件 （模板+数据模型 = 输出）
     * @param inputPath 模板位置
     * @param outputPath 输出文件位置
     * @param dataModel 数据模型
     * @throws IOException,TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object dataModel) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        String templatesPath = System.getProperty("user.dir")+ File.separator+"lvDiyCode-basic"+File.separator +"src/main/resources/templates";
//        System.out.println("templatesPath = " + templatesPath);
        cfg.setDirectoryForTemplateLoading(new File(templatesPath));
        cfg.setDefaultEncoding("UTF-8");

        Template temp = cfg.getTemplate("MainTemplate.java.ftl");

        FileWriter fileWriter = new FileWriter(outputPath);
        temp.process(dataModel, fileWriter);
    }
}
