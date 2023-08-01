package com.fengling.ecserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fengling.ecserver.mapper.entity.RolePerm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
public interface IRolePermService extends IService<RolePerm> {

        /**
         *  分页查询
         * @param param
         * @return
         */
        Page<RolePerm> selectPage(RolePerm param, Page page);

        /**
         *  数据集
         * @param param
         * @return
         */
        List<RolePerm> selectList(RolePerm param);

}