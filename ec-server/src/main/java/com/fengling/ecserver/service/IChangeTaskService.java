package com.fengling.ecserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fengling.ecserver.mapper.entity.ChangeTask;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
public interface IChangeTaskService extends IService<ChangeTask> {

        /**
         *  分页查询
         * @param param
         * @return
         */
        Page<ChangeTask> selectPage(ChangeTask param, Page page);

        /**
         *  数据集
         * @param param
         * @return
         */
        List<ChangeTask> selectList(ChangeTask param);

}