package com.hw.example.service;


import com.hw.example.pojo.RecordPerson;
import com.hw.example.pojo.SystemUserParam;

/**
 * Created by 高明亮 on 2019/7/30.
 */
public interface RecordPersonService {
    RecordPerson queryByCardNo(SystemUserParam systemUserParam);

    int insertDetailedInfo(RecordPerson recordPerson);
}
