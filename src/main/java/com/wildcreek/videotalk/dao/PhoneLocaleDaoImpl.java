package com.wildcreek.videotalk.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/5/31.
 */
public class PhoneLocaleDaoImpl implements PhoneLocaleDao {

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public boolean insertPhoneLocale(int prefix, String province, String city, String provider, int areacode, int postcode) {
        String sql = "insert into phonelocale " + "values(? ,? ,? ,? , ?, ?)";//普通的sql语句
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update(sql, new Object[]{prefix, province, city, provider, areacode, postcode});
        return false;
    }
}
