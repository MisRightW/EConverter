package com.fengling.ecserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.ChangeTaskMapper;
import com.fengling.ecserver.mapper.entity.ChangeTask;
import com.fengling.ecserver.service.IChangeTaskService;
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
public class ChangeTaskServiceImpl extends ServiceImpl<ChangeTaskMapper, ChangeTask> implements IChangeTaskService {


    @Autowired
    private ChangeTaskMapper changeTaskMapper;

    @Override
    public Page<ChangeTask> selectPage(ChangeTask param, Page page) {
        QueryWrapper<ChangeTask> entityWrapper = new QueryWrapper<ChangeTask>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<ChangeTask> selectList(ChangeTask param) {
        QueryWrapper<ChangeTask> entityWrapper = new QueryWrapper<ChangeTask>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<ChangeTask> getEntityWrapper(QueryWrapper<ChangeTask> entityWrapper,ChangeTask changeTask){
        QueryWrapper<ChangeTask> result = null;
        //条件拼接
        if (StrUtil.isNotBlank(changeTask.getTaskNo())){
            result = entityWrapper.like("TASK_NO",changeTask.getTaskNo());
        }
        if (StrUtil.isNotBlank(changeTask.getTaskParam())){
            result = entityWrapper.like("TASK_PARAM",changeTask.getTaskParam());
        }
        if (StrUtil.isNotBlank(changeTask.getStatus())){
            result = entityWrapper.like("STATUS",changeTask.getStatus());
        }
        if (StrUtil.isNotBlank(changeTask.getTaskMessage())){
            result = entityWrapper.like("TASK_MESSAGE",changeTask.getTaskMessage());
        }
        return result;
    }
}