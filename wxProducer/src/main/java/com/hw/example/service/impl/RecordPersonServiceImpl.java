package com.hw.example.service.impl;

import com.hw.example.dao.RecordPersonDao;
import com.hw.example.pojo.RecordPerson;
import com.hw.example.pojo.SystemUserParam;
import com.hw.example.service.RecordPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 高明亮 on 2019/7/30.
 */
@Service
public class RecordPersonServiceImpl implements RecordPersonService {

    @Autowired
    RecordPersonDao recordPersonDao;

    @Override
    public RecordPerson queryByCardNo(SystemUserParam systemUserParam) {
        return recordPersonDao.queryByCardNo(systemUserParam);
    }

    @Override
    public int insertDetailedInfo(RecordPerson recordPerson) {
        return recordPersonDao.insert(recordPerson);
    }
}
