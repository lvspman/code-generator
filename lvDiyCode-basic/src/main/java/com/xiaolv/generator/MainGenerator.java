package com.xiaolv.generator;

import com.xiaolv.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class MainGenerator{
        /**
         * 生成
         *
         * @param model 数据模型
         * @throws TemplateException
         * @throws IOException
         */
        public static void doGenerate(Object model) throws TemplateException, IOException {
            String projectPath = System.getProperty("user.dir");
            String parentPath = new File(projectPath).getParent();
            // 整个项目的根路径
//            File parentFile = new File(projectPath).getParentFile();
            // 输入路径
            String inputPath = new File(parentPath, "lvDiyCode-demo-projects/acm-template").getAbsolutePath();
            String outputPath = parentPath;
            // 生成静态文件
            StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
            // 生成动态文件
            String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
            String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/xiaolv/acm/MainTemplate.java";
            DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
        }

        public static void main(String[] args) throws TemplateException, IOException {
            MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
            mainTemplateConfig.setName("小吕");
            mainTemplateConfig.setLoop(true);
            mainTemplateConfig.setOutputText("小吕又帅了");
            doGenerate(mainTemplateConfig);
        }
}
