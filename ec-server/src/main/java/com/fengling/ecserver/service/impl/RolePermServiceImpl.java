package com.fengling.ecserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.RolePermMapper;
import com.fengling.ecserver.mapper.entity.RolePerm;
import com.fengling.ecserver.service.IRolePermService;
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
public class RolePermServiceImpl extends ServiceImpl<RolePermMapper, RolePerm> implements IRolePermService {


    @Autowired
    private RolePermMapper rolePermMapper;

    @Override
    public Page<RolePerm> selectPage(RolePerm param, Page page) {
        QueryWrapper<RolePerm> entityWrapper = new QueryWrapper<RolePerm>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<RolePerm> selectList(RolePerm param) {
        QueryWrapper<RolePerm> entityWrapper = new QueryWrapper<RolePerm>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<RolePerm> getEntityWrapper(QueryWrapper<RolePerm> entityWrapper,RolePerm rolePerm){
        QueryWrapper<RolePerm> result = null;
        //条件拼接
        return result;
    }
}