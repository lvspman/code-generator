package com.xiaolv.generator.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.xiaolv.meta.Meta;
import com.xiaolv.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class FileGenerator {
        /**
         * 生成
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
            StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
            // 生成动态文件
            String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl.java.ftl";
            String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/xiaolv/acm/MainTemplate.java.ftl.java";
            DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
        }

    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 读取 resources 目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // Java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);



        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);
    }
}
