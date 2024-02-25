package com.xiaolv;

import com.xiaolv.cli.CommandExecutor;
import com.xiaolv.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();

    }
}