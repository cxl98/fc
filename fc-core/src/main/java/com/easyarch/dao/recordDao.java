package com.easyarch.dao;


import com.easyarch.model.other.FightRecord;

import java.util.List;

public interface recordDao {

    int insertRecord();

    List<FightRecord> searchRecord(String userId);

    int searchRecordCount();

    int searchRecordCountById(String userId);
}
