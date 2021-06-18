package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
public class MQCommandRecord implements Serializable{

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private int flag;

    @Getter
    @Setter
    private String  context;

    @Override
    public String toString() {
        return "MQCommandRecord{" +
                "tag='" + tag + '\'' +
                ", key='" + key + '\'' +
                ", flag=" + flag +
                ", context='" + context + '\'' +
                '}';
    }
}
