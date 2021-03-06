package com.xxx.dao.Department;

import com.xxx.pojo.Department;

import java.util.List;

public interface DepartmentDao {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    //通过部门名称查询部门信息
    Department selectAllByDepartmentName(String departmentName);
}