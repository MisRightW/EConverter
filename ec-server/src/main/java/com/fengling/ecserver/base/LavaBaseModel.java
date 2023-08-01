package com.fengling.ecserver.base;

import java.io.Serializable;

/**
 * 基类<br>
 * 万事之祖，万事皆允，万事皆虚。
 *
 * @author jiyu.gjy
 */
public abstract class LavaBaseModel implements Serializable {

    private static final long serialVersionUID = -6019275603702011190L;

    /**
     * 主键，所有模型都需要的唯一标识
     */
    protected Long id;

    /**
     * 序列化时不暴露此Id，改为序列化加密的SecurityId
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
