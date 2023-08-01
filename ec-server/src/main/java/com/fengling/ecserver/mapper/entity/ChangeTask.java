package com.fengling.ecserver.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengling.ecserver.base.LavaDo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
@TableName("ec_change_task")
public class ChangeTask extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 转换任务编号
     */
    @TableField("task_no")
            private String taskNo;
    /**
     * 任务参数
     */
    @TableField("task_param")
            private String taskParam;
    /**
     * 任务状态[0:任务初始化;1:任务完成;2:任务异常后重试;3:任务失败;]
     */
    @TableField("status")
            private String status;
    /**
     * 任务消息
     */
    @TableField("task_message")
            private String taskMessage;
    /**
     * 任务执行次数
     */
    @TableField("task_count")
            private Integer taskCount;
    /**
     * 下一次执行时间
     */
    @TableField("next_time")
            private LocalDateTime nextTime;


}