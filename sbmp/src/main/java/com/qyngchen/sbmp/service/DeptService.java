package com.qyngchen.sbmp.service;

import com.qyngchen.sbmp.mapper.DeptMapper;
import com.qyngchen.sbmp.pojo.DeptDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptService {

    @Autowired
    private DeptMapper mapper;

    public Boolean adddept(){

        DeptDo deptDo = new DeptDo();
        deptDo.setDeptCreateTime(System.currentTimeMillis());
        deptDo.setDeptName("Test_00012");
        deptDo.setDeptUpdateTime(System.currentTimeMillis());
        int insert = mapper.insert(deptDo);
        return false;
    }
}
