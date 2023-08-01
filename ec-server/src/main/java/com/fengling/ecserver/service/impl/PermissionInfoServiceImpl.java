package com.fengling.ecserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.PermissionInfoMapper;
import com.fengling.ecserver.mapper.entity.PermissionInfo;
import com.fengling.ecserver.service.IPermissionInfoService;
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
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements IPermissionInfoService {


    @Autowired
    private PermissionInfoMapper permissionInfoMapper;

    @Override
    public Page<PermissionInfo> selectPage(PermissionInfo param, Page page) {
        QueryWrapper<PermissionInfo> entityWrapper = new QueryWrapper<PermissionInfo>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<PermissionInfo> selectList(PermissionInfo param) {
        QueryWrapper<PermissionInfo> entityWrapper = new QueryWrapper<PermissionInfo>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<PermissionInfo> getEntityWrapper(QueryWrapper<PermissionInfo> entityWrapper,PermissionInfo permissionInfo){
        QueryWrapper<PermissionInfo> result = null;
        //条件拼接
        if (StrUtil.isNotBlank(permissionInfo.getPermName())){
            result = entityWrapper.like("PERM_NAME",permissionInfo.getPermName());
        }
        if (StrUtil.isNotBlank(permissionInfo.getPermType())){
            result = entityWrapper.like("PERM_TYPE",permissionInfo.getPermType());
        }
        if (StrUtil.isNotBlank(permissionInfo.getPermUrl())){
            result = entityWrapper.like("PERM_URL",permissionInfo.getPermUrl());
        }
        if (StrUtil.isNotBlank(permissionInfo.getPermIcon())){
            result = entityWrapper.like("PERM_ICON",permissionInfo.getPermIcon());
        }
        return result;
    }
}