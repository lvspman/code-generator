package com.xiaolv.cli.pattern;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class TurnOffCommand implements Command{
    private Device device;

    @Override
    public void execute() {
        device.turnOff();
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
