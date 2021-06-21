package com.xxx.service.WorkTitle;

import com.xxx.pojo.WorkTitle;

import java.util.List;

public interface WorkTitleService {

    List<WorkTitle> getAllWorkTitle();

    WorkTitle getWorkTitleById(String workTitleId);

    boolean updateWorkTitle(WorkTitle workTitle);

    boolean deleteWorkTitle(String workTitleId);

    // 有主键
    boolean insertWorkTitle(WorkTitle workTitle);

    // 无主键，此处生成 UUID
    boolean insertWorkTitleNoPrimaryKey(WorkTitle workTitle);

    // 该职称如果仍然有人拥有，则不可删除
    boolean judgeWorkTitleExists(String workTitleId);

}
