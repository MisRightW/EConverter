package com.fengling.ecserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fengling.ecserver.mapper.entity.FileInfo;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
public interface IFileInfoService extends IService<FileInfo> {

        /**
         *  分页查询
         * @param param
         * @return
         */
        Page<FileInfo> selectPage(FileInfo param, Page page);

        /**
         *  数据集
         * @param param
         * @return
         */
        List<FileInfo> selectList(FileInfo param);

}