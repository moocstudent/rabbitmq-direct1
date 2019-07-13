package com.ykmimi.rabbitmqdirect.util;

import cn.hutool.core.util.NetUtil;

import javax.swing.*;

public class RabbitMQUtil {

    public static void checkServer(){
        if(NetUtil.isUsableLocalPort(15672)){
            JOptionPane.showMessageDialog(null,"RabbitMQ服务器未启动");
            System.exit(1);
        }
    }
}
