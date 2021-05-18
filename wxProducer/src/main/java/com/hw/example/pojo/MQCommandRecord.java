package com.hw.example.pojo;

import java.io.Serializable;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
public class MQCommandRecord implements Serializable{
    private String tag;
    private String key;
    private int flag;
    private String  context;
    private int commandId;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @Override
    public String toString() {
        return "MQCommandRecord{" +
                "tag='" + tag + '\'' +
                ", key='" + key + '\'' +
                ", flag=" + flag +
                ", context='" + context + '\'' +
                ", commandId=" + commandId +
                '}';
    }
}
