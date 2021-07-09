package com.xxx.service.PersonalDepartment;

import com.xxx.dao.Department.DepartmentDao;
import com.xxx.dao.Department.DepartmentDaoImpl;
import com.xxx.pojo.Department;

import java.util.List;

public class PersonalDepartmentServiceImpl implements PersonalDepartmentService{

    private DepartmentDao departmentDao = null;

    public PersonalDepartmentServiceImpl(){
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    public List<Department> selectAll() {
        return departmentDao.selectAll();
    }

    @Override
    public Department selectAllByDepartmentName(String departmentName){
        return  departmentDao.selectAllByDepartmentName(departmentName);
    }
    @Override
    public int updateByPrimaryKey(Department record){
        return departmentDao.updateByPrimaryKey(record);
    }
    @Override
    public int insert(Department record){
        return departmentDao.insert(record);
    }
    @Override
    public int deleteByPrimaryKey(String departmentId){
        return departmentDao.deleteByPrimaryKey(departmentId);
    }
}
