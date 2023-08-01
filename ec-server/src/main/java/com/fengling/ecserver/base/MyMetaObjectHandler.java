package com.fengling.ecserver.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ......");
        this.fillStrategy(metaObject, "gmt_create", new Date());
        this.fillStrategy(metaObject, "gmt_modified", new Date());

        this.setFieldValByName("creator", getCurrentUserId(), metaObject);
        this.setFieldValByName("modifier", getCurrentUserId(), metaObject);
        log.info("end insert fill ......");
    }

    /**
     * 修改填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ......");
        this.fillStrategy(metaObject, "gmt_modified", new Date());
        this.fillStrategy(metaObject, "modifier", getCurrentUserId());
        log.info("end update fill ......");
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    private String getCurrentUserId() {
        return "1";
    }
}
