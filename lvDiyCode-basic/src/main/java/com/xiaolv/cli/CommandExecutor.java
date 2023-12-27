package com.xiaolv.cli;

import com.xiaolv.cli.command.ConfigCommand;
import com.xiaolv.cli.command.GenerateCommand;
import com.xiaolv.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
@Command(name = "ldc", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
