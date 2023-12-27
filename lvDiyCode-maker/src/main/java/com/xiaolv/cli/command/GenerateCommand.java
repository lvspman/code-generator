package com.xiaolv.cli.command;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.tools.javac.util.StringUtils;
import com.xiaolv.generator.file.FileGenerator;
import com.xiaolv.model.DataModel;
import lombok.Data;
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
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        System.out.println("配置信息：" + dataModel);

        FileGenerator.doGenerate(dataModel);
        return 0;
    }
}
