package com.xxx.service.WorkAgeMoney;

import com.xxx.pojo.WorkAgeMoney;

import java.util.List;

public interface WorkAgeMoneyService {

    List<WorkAgeMoney> getAllWorkAgeMoney();

    WorkAgeMoney getWorkAgeMoneyByWorkAge(int workAge);

    boolean insertWorkAgeMoney(WorkAgeMoney workAgeMoney);

    boolean deleteWorkAgeMoney(int workAge);

    boolean updateWorkAgeMoney(WorkAgeMoney workAgeMoney, int preWorkAge);

}
