package com.xiaolv.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class MetaManager {
    private static volatile Meta meta;//volatile保证多线程环境下内存的可见性

    public static Meta getMetaObject() {
//        双检锁单例模式
        if (meta == null) {//防止都抢锁
            synchronized (MetaManager.class){
                if (meta == null) {//防止同时拿到锁
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);

        return newMeta;
    }
}
