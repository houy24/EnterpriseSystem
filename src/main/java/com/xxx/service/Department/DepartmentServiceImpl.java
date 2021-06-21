package com.xxx.service.Department;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.dao.Department.DepartmentDaoImpl;
import com.xxx.pojo.Department;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao departmentDao = null;

    public DepartmentServiceImpl() {
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    public List<Department> selectAll() {
        return departmentDao.selectAll();
    }

    @Override
    public Department selectByDepartmentId(String departmentId) {
        return departmentDao.selectByPrimaryKey(departmentId);
    }
}
