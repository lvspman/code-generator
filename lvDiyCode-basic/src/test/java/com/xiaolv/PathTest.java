package com.xiaolv;

import org.junit.Test;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class PathTest {

    // 哪个文件夹用idea打开 则System.getProperty("user.dir)获取的就是该文件夹的目录
    @Test
    public void projectPath() {
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath = " + projectPath);
    }
}
