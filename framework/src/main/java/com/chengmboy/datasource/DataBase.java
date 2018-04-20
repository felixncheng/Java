package com.chengmboy.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cheng_mboy
 */
public class DataBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBase.class);
    private final DataSource dataSource;

    public DataBase(String url, String username, String password, String driver) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        dataSource = new HikariDataSource(config);
    }


    public Map<String, Object> selectOne(String sql) {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int length = resultSet.getMetaData().getColumnCount();
            if (length > 1) {
                throw new DataBaseException("返回数据大于1条");
            }
            Map<String, Object> filedMap = new HashMap<>(length);
            while (resultSet.next()) {
                for (int i = 1; i <= length; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    filedMap.put(columnName, value);
                }
            }
            return filedMap;
        } catch (SQLException e) {
            LOGGER.error("sql执行出错", e);
        }
        return null;
    }

    public List<Map<String, Object>> select(String sql) {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> resultList = new ArrayList<>();
            int length = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> filedMap = new HashMap<>(length);
                for (int i = 1; i <= length; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    filedMap.put(columnName, value);
                }
                resultList.add(filedMap);
            }
            return resultList;
        } catch (SQLException e) {
            LOGGER.error("sql执行出错", e);
        }
        return null;
    }

    public int deleteAll(String sql) {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("sql执行出错", e);
        }
        return -1;
    }
}
