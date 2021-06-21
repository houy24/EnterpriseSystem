package com.xxx.service.Department;

import com.xxx.pojo.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> selectAll();

    Department selectByDepartmentId(String departmentId);


}
