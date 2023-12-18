package com.xiaolv;

import com.xiaolv.cli.CommandExecutor;

public class Main {

    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "false","-n", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}