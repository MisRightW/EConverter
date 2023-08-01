package com.fengling.ecserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengling.ecserver.mapper.UserInfoMapper;
import com.fengling.ecserver.mapper.entity.UserInfo;
import com.fengling.ecserver.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Page<UserInfo> selectPage(UserInfo param, Page page) {
        QueryWrapper<UserInfo> entityWrapper = new QueryWrapper<UserInfo>();
        getEntityWrapper(entityWrapper,param);
        return super.page(page, entityWrapper);
    }

    @Override
    public List<UserInfo> selectList(UserInfo param) {
        QueryWrapper<UserInfo> entityWrapper = new QueryWrapper<UserInfo>();
        getEntityWrapper(entityWrapper, param);
        return super.list(entityWrapper);
    }

    /**
     *  公共查询条件
     * @param entityWrapper
     * @return
     */
    public QueryWrapper<UserInfo> getEntityWrapper(QueryWrapper<UserInfo> entityWrapper,UserInfo userInfo){
        QueryWrapper<UserInfo> result = null;
        //条件拼接
        if (StrUtil.isNotBlank(userInfo.getUserName())){
            result = entityWrapper.like("USER_NAME",userInfo.getUserName());
        }
        if (StrUtil.isNotBlank(userInfo.getUserPass())){
            result = entityWrapper.like("USER_PASS",userInfo.getUserPass());
        }
        if (StrUtil.isNotBlank(userInfo.getPhone())){
            result = entityWrapper.like("PHONE",userInfo.getPhone());
        }
        if (StrUtil.isNotBlank(userInfo.getNickname())){
            result = entityWrapper.like("NICKNAME",userInfo.getNickname());
        }
        if (StrUtil.isNotBlank(userInfo.getAvatar())){
            result = entityWrapper.like("AVATAR",userInfo.getAvatar());
        }
        if (StrUtil.isNotBlank(userInfo.getAppid())){
            result = entityWrapper.like("APPID",userInfo.getAppid());
        }
        if (StrUtil.isNotBlank(userInfo.getOpenid())){
            result = entityWrapper.like("OPENID",userInfo.getOpenid());
        }
        if (StrUtil.isNotBlank(userInfo.getUnionid())){
            result = entityWrapper.like("UNIONID",userInfo.getUnionid());
        }
        if (StrUtil.isNotBlank(userInfo.getSessionKey())){
            result = entityWrapper.like("SESSION_KEY",userInfo.getSessionKey());
        }
        if (StrUtil.isNotBlank(userInfo.getAccessToken())){
            result = entityWrapper.like("ACCESS_TOKEN",userInfo.getAccessToken());
        }
        return result;
    }
}