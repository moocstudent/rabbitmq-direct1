package com.ykmimi.rabbitmqdirect;

import com.ykmimi.rabbitmqdirect.customer.TestDirectCustomer;
import com.ykmimi.rabbitmqdirect.producer.TestDirectProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class RabbitmqDirectApplication {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        TestDirectCustomer.startCustomer();
        TestDirectCustomer.startCustomer();
        Thread.sleep(3000);
        TestDirectProducer.startProducer();
//        SpringApplication.run(RabbitmqDirectApplication.class, args);
    }

}
