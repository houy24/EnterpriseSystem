package com.xxx.service.PersonalResume;

import com.xxx.dao.PersonalResume.PersonalResumeDao;
import com.xxx.dao.PersonalResume.PersonalResumeDaoImpl;
import com.xxx.dao.UserAccount.UserAccountMapper;
import com.xxx.dao.UserAccount.UserAccountMapperImpl;
import com.xxx.pojo.PersonalResume;

public class PersonalResumeServiceImpl implements PersonalResumeService{

    private PersonalResumeDao personalResumeDao = null;

    public PersonalResumeServiceImpl() {
        personalResumeDao = new PersonalResumeDaoImpl();
    }

    @Override
    public PersonalResume selectAllByUserId(String userId) {
        return personalResumeDao.selectAllByUserId(userId);
    }
}
