package com.xiaolv.cli.pattern;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */

import java.util.List;

/**
 * 相当于遥控器
 */
public class RemoteControl {
    private Command command; //遥控器上的按钮
    private List<Command> historyCommands; //查看遥控器的命令历史

    public void setCommand(Command command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}
