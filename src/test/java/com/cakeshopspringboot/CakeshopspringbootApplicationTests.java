package com.cakeshopspringboot;

import com.cakeshopspringboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class CakeshopspringbootApplicationTests {

    @Test
    void contextLoads() throws ParseException {
//        Date date = new Date(); // this object contains the current date value
//        System.out.println(date);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
//        String format = formatter.format(date);
//        System.out.println(formatter.format(date));
//        Order order = new Order();
//        order.setDatetime(format);
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        System.out.println(nowTime);
        Date time = sdf.parse( nowTime );
        System.out.println(time);
    }

}
