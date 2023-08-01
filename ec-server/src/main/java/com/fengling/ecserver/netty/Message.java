package com.fengling.ecserver.netty;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /**
     * 数据长度
     */
    private Integer len;
    /**
     * 通讯数据
     */
    private byte[] content;

    public Message(Object object) {
        content = JSONUtil.toJsonStr(object).getBytes(StandardCharsets.UTF_8);
        len = content.length;
    }

}
