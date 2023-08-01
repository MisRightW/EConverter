package com.fengling.ecserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.FileInfoMapper;
import com.fengling.ecserver.mapper.entity.FileInfo;
import com.fengling.ecserver.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
@Service
@Transactional
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {


    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public Page<FileInfo> selectPage(FileInfo param, Page page) {
        QueryWrapper<FileInfo> entityWrapper = new QueryWrapper<FileInfo>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<FileInfo> selectList(FileInfo param) {
        QueryWrapper<FileInfo> entityWrapper = new QueryWrapper<FileInfo>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<FileInfo> getEntityWrapper(QueryWrapper<FileInfo> entityWrapper, FileInfo fileInfo){
        QueryWrapper<FileInfo> result = null;
        //条件拼接
        if (StrUtil.isNotBlank(fileInfo.getFileName())){
            result = entityWrapper.like("FILE_NAME",fileInfo.getFileName());
        }
        if (StrUtil.isNotBlank(fileInfo.getFilePath())){
            result = entityWrapper.like("FILE_PATH",fileInfo.getFilePath());
        }
        if (StrUtil.isNotBlank(fileInfo.getFileSize())){
            result = entityWrapper.like("FILE_SIZE",fileInfo.getFileSize());
        }
        if (StrUtil.isNotBlank(fileInfo.getFileExt())){
            result = entityWrapper.like("FILE_EXT",fileInfo.getFileExt());
        }
        return result;
    }
}