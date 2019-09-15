package com.yq.hive;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by EricYang on 2019/7/13.
 */
@Slf4j
public class HiveInsertDemo {
    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            //Class.forName(driverName);
            Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.119.139:10000/default;AuthMech=0;transportMode=binary;");
            //String url = "jdbc:hive2://192.168.119.139:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";
            //Connection conn = DriverManager.getConnection(url, "", "");
            //Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.119.139:10000/default", "", "");
            System.out.println(conn);
            Statement stat = conn.createStatement();
            String hql = "create table t_student_jdbc(id int,name string,tel string)" +
                    " row format delimited" +
                    " fields terminated by ','" +
                    " stored as textfile";
            stat.execute(hql);
            stat.close();
            conn.close();
        } catch (Exception ex) {
            log.error("failed to insert values", ex);
        }

        log.info("done");

    }
}
