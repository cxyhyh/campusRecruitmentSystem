package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.ApplicationMapper;
import com.zbdx.xyzp.mapper.UserMapper;
import com.zbdx.xyzp.model.dto.ApplicationDTO;
import com.zbdx.xyzp.model.entity.Application;
import com.zbdx.xyzp.service.ApplicationService;
import com.zbdx.xyzp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Override
    public List<ApplicationDTO> getApplication() {
        return applicationMapper.getApplication();
    }

    @Override
    public Result apply(String username, String applicationPosition) {

        List<ApplicationDTO> list = applicationMapper.getUsernameAndApplicationPosition(username ,applicationPosition);
        if (list.size() <= 0) {
            applicationMapper.addApplication(username, applicationPosition, "已申请,待审核。。。。", "普通用户");

        }else {
            return Result.fail(400,"该用户已经申请该职位，不必重复申请");
        }
        return null;

    }

    @Override
    public List<ApplicationDTO> selectUsername(String username) {
        return applicationMapper.selectUsername(username);
    }

    @Override
    public Result tongYi(String username) {

        List<ApplicationDTO> list = applicationMapper.selectUsername(username);
        if (list.size() <= 0) {
            return Result.fail(400, "该用户还没有申请该职位，请按规定流程先申请该职位！");

        }else if (list.size() > 0){
            UpdateWrapper<Application> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status","审核通过！").eq("username",username);
            return Result.success(this.update(updateWrapper));

        }
        return null;
    }

    @Override
    public Result buTongYi(String username) {
        List<ApplicationDTO> list = applicationMapper.selectUsername(username);
        if (list.size() <= 0) {
            return Result.fail(400, "该用户还没有申请该职位，请按规定流程先申请该职位！");

        }else if (list.size() > 0){
            UpdateWrapper<Application> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status","审核未通过！").eq("username",username);
            return Result.success(this.update(updateWrapper));

        }
        return null;
    }

    @Override
    public int addApplication(String username, String applicationPosition ,String status, String roleType) {
        return applicationMapper.addApplication(username, applicationPosition, status, roleType );
    }

    @Override
    public Page<ApplicationDTO> pageApplication(Page<ApplicationDTO> page, ApplicationDTO applicationDTO) {
        return applicationMapper.pageApplication(page,applicationDTO);
    }

    @Override
    public List<Map<String, Object>> getApplicationPositionForA() {
        return applicationMapper.getApplicationPositionForA();
    }

    @Override
    public List<Map<String, Object>> getStatus() {
        return applicationMapper.getStatus();
    }

    @Override
    public Result tongGuo(String username, String applicationPosition) {

        List<ApplicationDTO> list = applicationMapper.getUsernameAndApplicationPosition(username , applicationPosition);

         if (list.size() <= 0) {
            return Result.fail(400, "该用户还没有申请该职位，请按规定流程先申请该职位！");
        }else if (list.size() > 0){
             Application application = new Application();
             if (application.getStatus() != "审核通过"){
                 UpdateWrapper<Application> updateWrapper = new UpdateWrapper<>();
                 updateWrapper.set("status","审核通过！").eq("username",username).eq("application_position" ,applicationPosition);
                 return Result.success(this.update(updateWrapper));

             }
        }
         return null;
    }

    @Override
    public Result buTongGuo(String username, String applicationPosition) {

        List<ApplicationDTO> list = applicationMapper.getUsernameAndApplicationPosition(username , applicationPosition);

        if (list.size() <= 0) {
            return Result.fail(400, "该用户还没有申请该职位，请按规定流程先申请该职位！");
        }else if (list.size() > 0){
            Application application = new Application();
            if (application.getStatus() != "审核未通过"){
                UpdateWrapper<Application> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("status","审核未通过！").eq("username",username).eq("application_position" ,applicationPosition);
                return Result.success(this.update(updateWrapper));

            }
        }
        return null;
    }

    @Override
    public List<ApplicationDTO> getUsernameAndApplicationPosition(String username, String applicationPosition) {
        return applicationMapper.getUsernameAndApplicationPosition(username , applicationPosition);
    }

}
