package com.hw.example.service.impl;

import com.hw.example.dao.HisUserRecordDao;
import com.hw.example.pojo.HisUserRecord;
import com.hw.example.service.HisUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisUserRecordServiceImpl implements HisUserRecordService {

    @Autowired
    HisUserRecordDao hisUserRecordDao;

    @Override
    public int insertHisUserRecord(HisUserRecord hisUserRecord) {
        return hisUserRecordDao.insert(hisUserRecord);
    }
}
