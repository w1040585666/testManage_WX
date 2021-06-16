package com.hw.example.service;


import com.hw.example.pojo.RecordPerson;
import com.hw.example.pojo.SystemUserParam;

public interface RecordPersonService {
    RecordPerson queryByCardNo(SystemUserParam systemUserParam);

    int insertDetailedInfo(RecordPerson recordPerson);
}
