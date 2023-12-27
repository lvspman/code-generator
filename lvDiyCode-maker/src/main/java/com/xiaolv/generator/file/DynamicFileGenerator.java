package com.xiaolv.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class DynamicFileGenerator {
    /**
     * 生成文件 （模板+数据模型 = 输出）
     * @param inputPath 模板位置
     * @param outputPath 输出文件位置
     * @param dataModel 数据模型
     * @throws IOException,TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object dataModel) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
//        String templatesPath = System.getProperty("user.dir")+ File.separator +"src/main/resources/templates";
        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(templateDir);
        cfg.setDefaultEncoding("UTF-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);

        if (!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        FileWriter fileWriter = new FileWriter(outputPath);
        template.process(dataModel, fileWriter);

        fileWriter.close();
    }
}
