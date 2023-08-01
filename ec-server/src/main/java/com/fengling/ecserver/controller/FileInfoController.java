package com.fengling.ecserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengling.ecserver.base.Result;
import com.fengling.ecserver.mapper.entity.FileInfo;
import com.fengling.ecserver.service.IFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
@RestController
@RequestMapping("/file-info")
@Validated
@Api(description = "接口")
public class FileInfoController {

    private final Logger logger = LoggerFactory.getLogger(FileInfoController.class);

    @Autowired
    public IFileInfoService FileInfoService;

    /**
     * 列表查询
     * @param request
     * @return
     */
    @ApiOperation("列表查询")
    @RequestMapping(method = RequestMethod.GET, value = {"/list"})
    public Result<List<FileInfo>> list(HttpServletRequest request){
        return Result.success(FileInfoService.list());
    }

    /**
     * 分页查询数据
     *
     * @param pn  当前页
     * @param size  每页行数
     * @param model 查询条件
     * @return
     */
    @ApiOperation("分页查询")
    @ResponseBody
    @GetMapping("/getPageList")
    public Result<Page<FileInfo>> getPageList(@RequestParam(value = "pn",defaultValue = "1") Integer pn, @RequestParam(value = "size",defaultValue = "20") Integer size, Model model){
        Page<FileInfo> result = null;
        try{
            result = FileInfoService.page(new Page<>(pn, size));
        }catch(Exception e){
            logger.error("getFileInfoList -=- {}" ,e.toString());
        }
        return Result.success(result);
    }

    /**
     * 新增用户
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ApiOperation("新增")
    @RequestMapping(method = RequestMethod.GET, value = "/save")
    public boolean save (HttpServletRequest request,HttpServletResponse response,Model model) {
        try{
            FileInfo mo = new FileInfo();
            BeanUtils.copyProperties(model, mo);
            return FileInfoService.saveOrUpdate(mo);
        }catch(Exception ex){
            logger.error("fileInfo save -=- {}" ,ex.toString());
        }
        return false;
    }

    /**
     * 修改用户信息
     * @param request
     * @param id  实体ID
     * @return
     */
    @ApiOperation("编辑")
    @RequestMapping(method = RequestMethod.GET, value = "/modify")
    public boolean modify(HttpServletRequest request, Long id, Model model){
        try{
            FileInfo mo = new FileInfo();
            BeanUtils.copyProperties(model, mo);
            return FileInfoService.saveOrUpdate(mo);
        }catch(Exception ex){
            logger.error("fileInfo Update -=- {}" ,ex.toString());
        }
        return false;
    }

    /**
     * 保存和修改公用的
     * @param fileInfo
     * @return 0 失败  1 成功
     */
    @ApiOperation("新增或编辑")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/saveOrUpdate")
    public int saveOrUpdate (FileInfo fileInfo){
        int count=0;
        try{
            count = FileInfoService.saveOrUpdate(fileInfo) ? 1:0;
        }catch(Exception e){
            logger.error("fileInfo saveOrUpdate -=- {}" ,e.toString());
        }
        return count;
    }

    /**
     * 根据id删除对象
     * @param id  实体ID
     * @return 0 失败  1 成功
     */
    @ApiOperation("根据id删除")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public int delete (Long id){
        int count=0;
        try{
            count = FileInfoService.getBaseMapper().deleteById(id);
        }catch(Exception e){
            logger.error("fileInfo Delete -=- {}" ,e.toString());
        }
        return count;
    }

    /**
     * 批量删除对象
     * @param ids 实体集合ID
     * @return  0 失败  1 成功
     */
    @ApiOperation("批量删除")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/batchDelete")
    public int batchDelete(List<Long> ids){
        int count=0;
        try{
            count = FileInfoService.getBaseMapper().deleteBatchIds(ids);
        }catch(Exception e){
            logger.error("fileInfo BatchDelete -=- {}" ,e.toString());
        }
        return count;
    }
}