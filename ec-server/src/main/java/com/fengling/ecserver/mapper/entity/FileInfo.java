package com.fengling.ecserver.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengling.ecserver.base.LavaDo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
@Data
@Accessors(chain = true)
@TableName("ec_file_info")
public class FileInfo extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    @TableField("file_name")
            private String fileName;
    /**
     * 文件路径
     */
    @TableField("file_path")
            private String filePath;
    /**
     * 文件大小
     */
    @TableField("file_size")
            private String fileSize;
    /**
     * 文件格式
     */
    @TableField("file_ext")
            private String fileExt;


}