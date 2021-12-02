package com.hw.example.listener;

import com.hw.example.utils.util.Utils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by wangxin on 2021/11/30.
 */

@Component
public class SettingDataInitListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       /* WebApplicationContext webApplicationContext =
                (WebApplicationContext)contextRefreshedEvent.getApplicationContext();
        ServletContext context = webApplicationContext.getServletContext();

        // 初始化系统参数
        String path = context.getRealPath("/");
        Utils.webPath = path;*/
    }
}
