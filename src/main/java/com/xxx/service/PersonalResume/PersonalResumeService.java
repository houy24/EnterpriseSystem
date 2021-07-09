package com.xxx.service.PersonalResume;

import com.xxx.pojo.PersonalResume;

public interface PersonalResumeService {

    //根据用户id查找个人简历表
    PersonalResume selectAllByUserId(String userId);
}
