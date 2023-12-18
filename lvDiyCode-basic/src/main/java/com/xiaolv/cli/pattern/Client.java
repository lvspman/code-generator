package com.xiaolv.cli.pattern;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class Client {
    public static void main(String[] args) {
        Device pc = new Device("PC");
        Device audio = new Device("audio");

        TurnOffCommand turnOff = new TurnOffCommand();
        turnOff.setDevice(pc);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(turnOff);

        remoteControl.pressButton();
    }
}
