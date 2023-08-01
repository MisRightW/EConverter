package com.fengling.ecserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fengling.ecserver.mapper.entity.PermissionInfo;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
public interface IPermissionInfoService extends IService<PermissionInfo> {

        /**
         *  分页查询
         * @param param
         * @return
         */
        Page<PermissionInfo> selectPage(PermissionInfo param, Page page);

        /**
         *  数据集
         * @param param
         * @return
         */
        List<PermissionInfo> selectList(PermissionInfo param);

}