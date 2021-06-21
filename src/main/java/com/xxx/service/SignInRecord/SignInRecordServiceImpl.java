package com.xxx.service.SignInRecord;

import com.xxx.dao.SignInRecord.SignInRecordDao;
import com.xxx.dao.SignInRecord.SignInRecordDaoImpl;
import com.xxx.pojo.SignInRecord;
import com.xxx.utils.MyDateTimeUtils;

import java.util.List;

public class SignInRecordServiceImpl implements SignInRecordService {

    private SignInRecordDao signInRecordDao = null;

    public SignInRecordServiceImpl() {
        signInRecordDao = new SignInRecordDaoImpl();
    }

    // 获取所有签到记录
    @Override
    public List<SignInRecord> getAllSignInRecord() {
        return signInRecordDao.selectAll();
    }

    @Override
    public List<SignInRecord> getSignInRecordByMonth(String timeMonthSearch) {
        List<SignInRecord> signInRecordList = signInRecordDao.selectAll();

        for (int i = 0; i < signInRecordList.size(); i++) {
            String nowTime = MyDateTimeUtils.getTimeByDate(signInRecordList.get(i).getSignInTime());
            // System.out.println("nowTime => " + nowTime);
            if (!nowTime.startsWith(timeMonthSearch)) {
                signInRecordList.remove(i);
                i--;
            }
        }
        return signInRecordList;
    }

    @Override
    public List<SignInRecord> getSignInRecordByDay(String timeDaySearch) {
        List<SignInRecord> signInRecordList = signInRecordDao.selectAll();

        for (int i = 0; i < signInRecordList.size(); i++) {
            String nowTime = MyDateTimeUtils.getTimeByDate(signInRecordList.get(i).getSignInTime());
            // System.out.println("nowTime => " + nowTime);
            if (!nowTime.startsWith(timeDaySearch)) {
                signInRecordList.remove(i);
                i--;
            }
        }
        return signInRecordList;
    }

    @Override
    public SignInRecord getSignInRecordByDayAndUserId(String timeDaySearch, String userId) {
        List<SignInRecord> signInRecordList = signInRecordDao.selectAll();

        for (SignInRecord signInRecord : signInRecordList) {
            if (!signInRecord.getUserId().equals(userId)) {
                continue;
            }
            String nowTime = MyDateTimeUtils.getTimeByDate(signInRecord.getSignInTime());
            // System.out.println("nowTime => " + nowTime);
            if (nowTime.startsWith(timeDaySearch)) {
                return signInRecord;
            }
        }

        return null;
    }


}
