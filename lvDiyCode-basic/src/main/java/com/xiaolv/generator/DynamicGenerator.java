package com.xiaolv.generator;

import cn.hutool.core.io.FileUtil;
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
        String inputPath = System.getProperty("user.dir")+File.separator +"src/main/resources/templates";
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

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(templateDir);
        cfg.setDefaultEncoding("UTF-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);

        // 文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        FileWriter fileWriter = new FileWriter(outputPath);
        template.process(dataModel, fileWriter);
    }
}
