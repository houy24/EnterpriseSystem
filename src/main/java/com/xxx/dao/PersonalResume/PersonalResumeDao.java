package com.xxx.dao.PersonalResume;

import com.xxx.pojo.PersonalResume;

import java.util.List;

public interface PersonalResumeDao {
    int deleteByPrimaryKey(String personalResumeId);

    int insert(PersonalResume record);

    int insertSelective(PersonalResume record);

    PersonalResume selectByPrimaryKey(String personalResumeId);

    PersonalResume selectAllByUserId(String userId);

    List<PersonalResume> selectAll();

    int updateByPrimaryKeySelective(PersonalResume record);

    int updateByPrimaryKey(PersonalResume record);
}