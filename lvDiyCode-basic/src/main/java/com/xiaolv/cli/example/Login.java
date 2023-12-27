package com.xiaolv.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, arity = "0..1",description = "user", interactive = true)
    String user;

    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true)
    String password;

    public Integer call() throws Exception {
        System.out.println(user);
        System.out.println("password = " + password);

        return 0;
    }

    private String base64(byte[] arr) {
        return arr.toString();
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user123","-p");
    }
}