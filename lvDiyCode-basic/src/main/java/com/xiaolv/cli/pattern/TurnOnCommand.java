package com.xiaolv.cli.pattern;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class TurnOnCommand implements Command{
    private Device device;

    @Override
    public void execute() {
        device.turnOn();
    }
}
