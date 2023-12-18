package com.xiaolv.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.xiaolv.generator.MainGenerator;
import com.xiaolv.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
@Command(name = "generate",
        description = "生成模板代码",
        mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable {

//    思路 接受MainTemplateConfig的参数 注入模板文件

    @Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, echo = true)
    private boolean loop;

    @Option(names = {"-o", "--outputText"}, arity = "0..1", interactive = true, echo = true)
    private String outputText;

    @Option(names = {"-n", "--name"}, arity = "0..1", interactive = true, echo = true)
    private String name;


    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);

        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
