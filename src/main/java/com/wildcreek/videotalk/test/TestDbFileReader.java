package com.wildcreek.videotalk.test;

import com.wildcreek.videotalk.service.PhoneLocaleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/10.
 */
public class TestDbFileReader {
    private static PhoneLocaleService phoneLocaleService;

    @Autowired
    public void setPhoneLocaleService(PhoneLocaleService phoneLocaleService) {
        this.phoneLocaleService = phoneLocaleService;
    }
    public static void main(String[] args) {
        readFromFile();
    }

    public static void readFromFile() {
        File file = new File("D:/IDEAWorkspace/videotalk/src/main/webapp/2016phonelocale.txt");
        if (!file.exists() || file.isDirectory()) {
            System.out.print("file not found");
        }
        BufferedReader br = null;
        int prefix = 0;
        String province = null;
        String city = null;
        String provider = null;
        int areacode = 0;
        int postcode = 0;
        String[] entity = new String[5];
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = null;
            temp = br.readLine();
            entity = temp.replace("\"","").split(",");
            System.out.print(Arrays.toString(entity));
            phoneLocaleService.insertIntoDb(Integer.parseInt(entity[1]),entity[2],entity[3],entity[4],Integer.parseInt(entity[5]),Integer.parseInt(entity[6]));
            while (temp != null) {
                temp = br.readLine();
                entity = temp.replace("\"","").split(",");
                phoneLocaleService.insertIntoDb(Integer.parseInt(entity[1]),entity[2],entity[3],entity[4],Integer.parseInt(entity[5]),Integer.parseInt(entity[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
