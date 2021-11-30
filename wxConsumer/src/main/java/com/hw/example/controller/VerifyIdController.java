package com.hw.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.example.pojo.*;
import com.hw.example.service.HisUserRecordService;
import com.hw.example.service.RecordPersonService;
import com.hw.example.service.SystemUserService;
import com.hw.example.utils.util.DateUtils;
import com.hw.example.utils.http.MyHttpsClient;
import com.hw.example.utils.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author:XN
 */
@EnableAutoConfiguration
@RestController
@ResponseBody
@RequestMapping("/verify")
public class VerifyIdController {

    @Value("${URL}")
    private String URL;
    @Value("${AppID}")
    private String AppID;
    @Value("${Token}")
    private String Token;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private RecordPersonService recordPersonService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private HisUserRecordService hisUserRecordService;

    @GetMapping("/getUser")
    public User getUser() {
        if(redisClient.existsObject("1")){
            Object obj = redisClient.getObject("1", User.class);
            User user = new ObjectMapper().convertValue(obj, User.class);
            if(user == null){

                User userNew = new User();
                userNew.setName("1111");
                redisClient.setObject("1", userNew, 0);
            }
            return user;
        } else {
            User userNew = new User();
            userNew.setName("1111");
            redisClient.setObject("1", userNew, 0);
        }
        return null;
    }

    /**
     * 根据身份证号查询是否是公安系统黑名单
     *
     * @param systemUserParam
     * @return java.util.Map.
     * @author wang xin.
     * @date 2019/7/29 18:07.
     */
    @PostMapping("/getVerifyByCard")
    public Map getVerifyByCard(@RequestBody SystemUserParam systemUserParam) throws IOException {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("status", -1);
        try {
            if (systemUserParam != null && systemUserParam.getUserName() != null && systemUserParam.getPwd() != null && systemUserParam.getCardNo() != null) {
                /**判断传入的用户名、密码是否有效*/
                SystemUser systemUser = systemUserService.getSystemUserInfo(systemUserParam.getUserName(), systemUserParam.getPwd());

                if (systemUser != null) {
                    /**如果用户不为空，表示身份有效*/
                    /**授权数量*/
                    int ntCount = systemUser.getNt_count();
                    /**授权剩余数量*/
                    int NtCountRemain = systemUser.getNt_count_remain();
                    /**授权数量大于0&&剩余授权数量大于0,才能调用公安黑名单判断系统*/
                    if (ntCount != 0 && NtCountRemain > 0) {
                        String cardNo = systemUserParam.getCardNo();
                        /**先查询本地库，有数据则直接返回，没有数据在调用公安系统*/
                        String checkType = systemUserParam.getCheckType();
                        if (checkType == null || checkType.isEmpty()) {
                            returnMap.put("message", "参数不能为空，请输入参数:" + "checkType:" + checkType);
                        }
                        RecordPerson recordPerson = null;
                        if ("1".equals(checkType) || "3".equals(checkType) || "4".equals(checkType)) {
                            recordPerson = recordPersonService.queryByCardNo(systemUserParam);
                        }
                        if (recordPerson != null) {
                            return createLoaclInfo(returnMap, cardNo, NtCountRemain, systemUser, recordPerson);
                        }
                        String name = systemUserParam.getName();
                        if ("1".equals(checkType) || "4".equals(checkType)) {
                            if (name == null || name.isEmpty()) {
                                returnMap.put("message", "参数不能为空，请输入参数:" + "name:" + name);
                            } else {
                                Map<String, Object> jsonInfo = getResultByCheckType(systemUserParam);
                                returnMap = invokeHttps(jsonInfo, NtCountRemain, systemUser, systemUserParam);
                            }
                        } else if ("2".equals(checkType) || "5".equals(checkType)) {
                            if (name == null || name.isEmpty()
                                    || systemUserParam.getCardBase64() == null || systemUserParam.getCardBase64().isEmpty()
                                    || systemUserParam.getFaceBase64() == null || systemUserParam.getFaceBase64().isEmpty()) {
                                returnMap.put("message", "参数不能为空，请输入参数:" + "name:" + name + " " + "CardBase64:" +
                                        systemUserParam.getCardBase64() + " " + "FaceBase64:" + systemUserParam.getFaceBase64());
                            } else {
                                Map<String, Object> jsonInfo = getResultByCheckType(systemUserParam);
                                /**调用第三方接口，返回结果信息*/
                                returnMap = invokeHttps(jsonInfo, NtCountRemain, systemUser, systemUserParam);
                            }
                        } else if ("3".equals(checkType)) {
                            Map<String, Object> jsonInfo = getResultByCheckType(systemUserParam);
                            /**调用第三方接口，返回结果信息*/
                            returnMap = invokeHttps(jsonInfo, NtCountRemain, systemUser, systemUserParam);
                        } else if ("6".equals(checkType)) {
                            if (systemUserParam.getFaceBase64() == null || systemUserParam.getFaceBase64().isEmpty()) {
                                returnMap.put("message", "参数不能为空，请输入参数:"+"FaceBase64:"+systemUserParam.getFaceBase64());
                            } else {
                                Map<String, Object> jsonInfo = getResultByCheckType(systemUserParam);
                                /**调用第三方接口，返回结果信息*/
                                returnMap = invokeHttps(jsonInfo, NtCountRemain, systemUser, systemUserParam);
                            }
                        } else {
                            //未知的checkType数据
                            returnMap.put("message:", "未知的checkType类型：" + checkType);
                        }
                    }else{
                        returnMap.put("message", "调用次数已用尽");
                        returnMap.put("status", -1);
                    }
                } else {
                    returnMap.put("message", "身份验证不通过");
                    returnMap.put("status", -1);
                }
            } else {
                returnMap.put("message", "参数不能为空，请输入参数");
                returnMap.put("status", -1);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return returnMap;
    }

    /**
     * 通过checkType参数封装不同的json数据
     * 参数：systemUserParam
     * 返回结果：jsonStr
     */
    private Map<String, Object> getResultByCheckType(SystemUserParam systemUserParam) {
        HashMap<String, Object> jsonStr = new HashMap<>();
        if (systemUserParam.getYXQQSRQ() != null && systemUserParam.getYXQJZRQ() != null) {
            jsonStr.put("YXQQSRQ", systemUserParam.getYXQQSRQ());
            jsonStr.put("YXQJZRQ", systemUserParam.getYXQJZRQ());
        }
        jsonStr.put("CheckType", systemUserParam.getCheckType());
        jsonStr.put("AppID", AppID);
        jsonStr.put("Token", Token);
        jsonStr.put("CardNo", systemUserParam.getCardNo());
        jsonStr.put("Name", systemUserParam.getName());
        jsonStr.put("Sex", systemUserParam.getSex());
        jsonStr.put("Nation", systemUserParam.getNation());
        jsonStr.put("CardBase64", systemUserParam.getCardBase64());
        jsonStr.put("FaceBase64", systemUserParam.getFaceBase64());
        return jsonStr;
    }

    /**
     * 通过checkType参数封装不同的json数据
     * 参数：jsonStr 封装好的json
     * 参数：NtCountRemain  授权剩余数量
     * 参数：systemUser     用户信息
     * 参数：cardNo         身份证号
     * 返回结果：returnMap
     */
    private Map<String, Object> invokeHttps(Map<String, Object> jsonStr, int NtCountRemain, SystemUser systemUser, SystemUserParam systemUserParam) {
        Map<String, Object> returnMap = new HashMap<>();
        /**调用接口*/
        try {
            String s = MyHttpsClient.sendHttpsPost(URL, jsonStr);
            Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(s);
            Object status = result.get("Status");
            if (Integer.parseInt(status.toString()) == 0) {
                /**调用成功,封装返回给调用者的数据*/
                returnMap.put("message", "成功");
                returnMap.put("status", 0);
                returnMap.put("Data", result.get("Data"));
                /**定义剩余调用次数*/
                NtCountRemain = NtCountRemain - 1;
                returnMap.put("NtCountRemain", "剩余调用次数:" + NtCountRemain);
                /**调用者的授权数量减少 -1*/
                systemUser.setNt_count_remain(NtCountRemain);
                systemUserService.updateByPrimaryKeySelective(systemUser);
                /**记录调用者的详细log日志*/
                RecordPerson record = new RecordPerson();
                record.setSzCardId(systemUserParam.getCardNo());
                /*转成json并保留null值，存库*/
                String json = JSON.toJSONString(result.get("Data"), SerializerFeature.WriteMapNullValue);
                record.setSzBlackLevel(json);
                record.setTsTime(DateUtils.getDate());
                record.setNtChecktype(systemUserParam.getCheckType());
                record.setSzName(systemUserParam.getName());
                /**新增一条Detailed数据*/
                recordPersonService.insertDetailedInfo(record);

                /**新增一条HisUserRecord数据*/
                insertHisUserRecord(systemUser, systemUserParam.getCardNo());
            } else {
                returnMap.put("message", result.get("Message"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return returnMap;
    }

    /*返回本地信息
    * 
    *returnMap:返回结果
    * */
    private Map<String, Object> createLoaclInfo(Map<String, Object> returnMap, String cardNo, int NtCountRemain, SystemUser systemUser, RecordPerson recordPerson) {

        /*res：存放result信息**/
        String strData = recordPerson.getSzBlackLevel();
        JSONObject jsonData = JSONObject.parseObject(strData);
        returnMap.put("message", "成功");
        returnMap.put("status", 0);
        returnMap.put("Data", jsonData);
        /**定义剩余调用次数,可抽取出公共方法*/
        NtCountRemain = NtCountRemain - 1;
        returnMap.put("NtCountRemain", "剩余调用次数:" + NtCountRemain);
        /**调用者的授权数量减少 -1*/
        systemUser.setNt_count_remain(NtCountRemain);
        systemUserService.updateByPrimaryKeySelective(systemUser);
        /**保存调用记录信息*/
        insertHisUserRecord(systemUser, cardNo);
        return returnMap;
    }

    /**
     * 保存调用记录信息
     */
    private void insertHisUserRecord(SystemUser systemUser, String cardNo) {
        HisUserRecord hisUserRecord = new HisUserRecord();
        hisUserRecord.setNgUserId(systemUser.getNg_id());
        hisUserRecord.setNgPersonId(cardNo);
        hisUserRecord.setTsTime(DateUtils.getDate());
        hisUserRecordService.insertHisUserRecord(hisUserRecord);
    }

}