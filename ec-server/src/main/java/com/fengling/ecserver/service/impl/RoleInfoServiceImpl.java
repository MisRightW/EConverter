package com.fengling.ecserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.RoleInfoMapper;
import com.fengling.ecserver.mapper.entity.RoleInfo;
import com.fengling.ecserver.service.IRoleInfoService;
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
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {


    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public Page<RoleInfo> selectPage(RoleInfo param, Page page) {
        QueryWrapper<RoleInfo> entityWrapper = new QueryWrapper<RoleInfo>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<RoleInfo> selectList(RoleInfo param) {
        QueryWrapper<RoleInfo> entityWrapper = new QueryWrapper<RoleInfo>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<RoleInfo> getEntityWrapper(QueryWrapper<RoleInfo> entityWrapper,RoleInfo roleInfo){
        QueryWrapper<RoleInfo> result = null;
        //条件拼接
        if (StrUtil.isNotBlank(roleInfo.getRoleName())){
            result = entityWrapper.like("ROLE_NAME",roleInfo.getRoleName());
        }
        if (StrUtil.isNotBlank(roleInfo.getRoleCode())){
            result = entityWrapper.like("ROLE_CODE",roleInfo.getRoleCode());
        }
        return result;
    }
}