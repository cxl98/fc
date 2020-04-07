package com.easyArch.dao;




import com.easyArch.entity.FightRecord;

import java.util.List;

public interface recordDao {

    int insertRecord();

    List<FightRecord> searchRecord(String userId);

    int searchRecordCount();

    int searchRecordCountById(String userId);
}
