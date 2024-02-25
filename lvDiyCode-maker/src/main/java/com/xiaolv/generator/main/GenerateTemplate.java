package com.xiaolv.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.xiaolv.generator.JarGenerator;
import com.xiaolv.generator.ScriptGenerator;
import com.xiaolv.generator.file.DynamicFileGenerator;
import com.xiaolv.meta.Meta;
import com.xiaolv.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {

    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 1.复制原始文件
        // 2.代码生成
        // 3.jar包生成
        // 4.脚本生成
        // 5.精简版产物

        String sourceCopyDestPath = copySource(meta, outputPath);

        generateCode(meta, outputPath);

        String jarPath = buildJar(meta, outputPath);

        String shellOutputFilePath = buildScript(meta, outputPath, jarPath);

        buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
    }

    /**
     * 生成精简版程序
     * @param outputPath
     * @param sourceCopyDestPath
     * @param jarPath
     * @param shellOutputFilePath
     */

    protected static void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        //生成精简版的程序（精简产物包）
        String distOutputPath = outputPath +  "-dist";
        // - 拷贝 jar 包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        // - 拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, true);
        // - 拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath, distOutputPath, true);
    }

    /**
     * 封装脚本
     *
     * @param outputPath
     * @param jarPath
     * @return
     * @throws IOException
     */
    protected static String buildScript(Meta meta, String outputPath, String jarPath) throws IOException {
        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     * 构建 jar 包
     * @param outputPath
     * @return 返回 jar 包的相对路径
     * @throws IOException
     * @throws InterruptedException
     */
    protected String buildJar(Meta meta, String outputPath) throws IOException, InterruptedException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    /**
     * 代码生成
     * @param meta
     * @param outputPath
     * @throws IOException
     * @throws TemplateException
     */
    protected static void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
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
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticFileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml.ftl.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // README.md.ftl
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    /**
     * 复制原始文件
     *
     * @param meta
     * @param outputPath
     * @return
     */
    protected static String copySource(Meta meta, String outputPath) {
        // 复制 source 目录
        String sourcePath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath  = outputPath + File.separator + ".source";
        FileUtil.copy(sourcePath, sourceCopyDestPath , false);
        return sourceCopyDestPath;
    }
}