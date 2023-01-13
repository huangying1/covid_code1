package com.tyut.covid.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

/**
 * 读取数据库中的数据，转换为 JSON 格式并输出到 data.json 文件
 */
public class DataFormatJson {
    public static void main(String[] args) {
        // 连接数据库
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/covid?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String pwd = "root";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pwd);
            Statement stet = con.createStatement();

            // 查询数据表的信息
//            String sql = "select * from covid";

//            String sql = "select\n" +
//                    "\t\tprovince_name AS `key`,\n" +
//                    "\t\tconfirmed_count AS `value`\n" +
//                    "\t\tfrom\n" +
//                    "\t\tworld_covid";

//            String sql = "select\n" +
//                    "\t\tprovince_name AS `key`,\n" +
//                    "\t\tconfirmed_count AS `value`\n" +
//                    "\t\tfrom\n" +
//                    "\t\tworld_covid\n" +
//                    "\t\torder by\n" +
//                    "\t\t`value`\n" +
//                    "\t\tdesc\n" +
//                    "\t\tlimit 0, 20";

//            String sql = "select\n" +
//                    "\t\tprovince_name AS `key`,\n" +
//                    "\t\tdead_count AS `value`\n" +
//                    "\t\tfrom\n" +
//                    "\t\tworld_covid\n" +
//                    "\t\torder by\n" +
//                    "\t\t`value`\n" +
//                    "\t\tdesc\n" +
//                    "\t\tlimit 0, 20";

//            String sql = "SELECT\n" +
//                    "\t\tcontinents AS `key`,\n" +
//                    "\t\tSUM(confirmed_count) AS `value`\n" +
//                    "\t\tFROM\n" +
//                    "\t\t`world_covid`\n" +
//                    "\t\tGROUP BY\n" +
//                    "\t\tcontinents\n" +
//                    "\t\torder by\n" +
//                    "\t\t`value`\n" +
//                    "\t\tdesc";

            String sql = "select\n" +
                    "\t\tconfirmed_count AS `value1`,\n" +
                    "\t\tcured_count AS `value2`,\n" +
                    "\t\tdead_count AS `value3`,\n" +
                    "\t\tdate_id AS `key`\n" +
                    "\t\tfrom\n" +
                    "\t\tshanghai";

//            String sql = "select\n" +
//                    "\t\tconfirmed_count AS `value1`,\n" +
//                    "\t\tcured_count AS `value2`,\n" +
//                    "\t\tdead_count AS `value3`,\n" +
//                    "\t\tdate_id AS `key`\n" +
//                    "\t\tfrom\n" +
//                    "\t\thubei";

//            String sql = "select\n" +
//                    "\t\tconfirmed_count AS `value1`,\n" +
//                    "\t\tcured_count AS `value2`,\n" +
//                    "\t\tdead_count AS `value3`,\n" +
//                    "\t\tdate_id AS `key`\n" +
//                    "\t\tfrom\n" +
//                    "\t\tbeijing";

            ResultSet rs = stet.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.add(jsonObj);
            }

            // 创建 data.json 文件目录
//            String filePath = "E:\\data.json";
//            String filePath = "E:\\getNumOfCountry.json";
//            String filePath = "E:\\getTopTwentyCountry.json";
//            String filePath = "E:\\getDeadTopTwentyCountry.json";
//            String filePath = "E:\\getNumOfContinents.json";
            String filePath = "E:\\getDataOfShangHai.json";
//            String filePath = "E:\\getDataOfHuBei.json";
//            String filePath = "E:\\getDataOfBeiJing.json";

            // 1. new FileWriter(filePath, true)：表示追加写
            // 2. new FileWriter(filePath)：表示覆盖写
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (Object o : array) {
                bufferedWriter.write(o.toString());
                bufferedWriter.write(",");
                // 插入一个换行
                bufferedWriter.newLine();
            }
            System.err.println("写入文件完毕");
            // 关闭资源
            bufferedWriter.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();// TODO: handle exception
        }
    }
}
