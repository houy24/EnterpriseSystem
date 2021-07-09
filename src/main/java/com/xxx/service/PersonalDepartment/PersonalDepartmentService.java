package com.xxx.service.PersonalDepartment;

import com.xxx.pojo.Department;

import java.util.List;

public interface PersonalDepartmentService {
    //查询所有部门
    List<Department> selectAll();
    //通过部门名称查询部门信息
    Department selectAllByDepartmentName(String departmentName);
    //修改部门信息
    int updateByPrimaryKey(Department record);
    //添加部门信息
    int insert(Department record);
    //删除
    int deleteByPrimaryKey(String departmentId);
}
