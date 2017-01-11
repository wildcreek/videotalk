package com.wildcreek.videotalk.test;

import com.wildcreek.videotalk.dao.PhoneLocaleDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/10.
 */
public class TestDbFileReader {
    private static PhoneLocaleDaoImpl phoneLocaleDaoImpl ;
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/spring/applicationContext.xml");
        phoneLocaleDaoImpl = (PhoneLocaleDaoImpl)ctx.getBean("phoneLocaleDaoImpl");
        TestDbFileReader reader = new TestDbFileReader();
        reader.readFromFile();
    }

    public  void readFromFile() {
        File file = new File("D:/IDEAWorkspace/videotalk/src/main/webapp/2016phonelocale.txt");
        if (!file.exists() || file.isDirectory()) {
            System.out.print("file not found");
        }
        BufferedReader br = null;
        String[] entity = null;
        System.out.println("开始时间--"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = null;
            temp = br.readLine();
            entity = temp.replace("\"","").split(",");
            System.out.println(Arrays.toString(entity));
            phoneLocaleDaoImpl.insertPhoneLocale(Integer.parseInt(entity[1]),entity[2],entity[3],entity[4],Integer.parseInt(entity[5]),Integer.parseInt(entity[6]));
            while (temp != null) {
                temp = br.readLine();
                entity = temp.replace("\"","").split(",");
                phoneLocaleDaoImpl.insertPhoneLocale(Integer.parseInt(entity[1]),entity[2],entity[3],entity[4],Integer.parseInt(entity[5]),Integer.parseInt(entity[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("结束时间--"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
    }
}
