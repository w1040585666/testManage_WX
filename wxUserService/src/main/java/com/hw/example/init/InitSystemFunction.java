package com.hw.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 数据初始化类
 * @author wang xin.
 * @version 1.0
 * @date 2021/11/30 9:54.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class InitSystemFunction {

    private static Logger logger = LoggerFactory.getLogger(InitSystemFunction.class);



    public void initFunction(){
        try {

        }catch (Exception e) {
            logger.error("",e);
        }
    }


}
