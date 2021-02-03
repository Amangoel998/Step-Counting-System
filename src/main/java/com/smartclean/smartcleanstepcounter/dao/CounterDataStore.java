package com.smartclean.smartcleanstepcounter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartclean.smartcleanstepcounter.dto.StepCountItem;

import org.springframework.stereotype.Component;

@Component
public class CounterDataStore {
    private static Connection conn = null;
    public CounterDataStore(){
        try{
            conn = DriverManager.getConnection ("jdbc:h2:./testdb", "sa","");
            Statement stmt = conn.createStatement(); 
            String sql =  "CREATE TABLE CounterRegistration " + 
               "(uid VARCHAR(255) not NULL, " +
               " counterValue BIGINT, " +
               " stepValue INT, " +
               " createdTime TimeStamp, " + 
               " modifiedTime TimeStamp, " + 
               " stopped Boolean DEFAULT True, " +
               " PRIMARY KEY ( uid ))";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            // System.err.println(e.getMessage());
        }
    }
    public boolean persistCounter(StepCountItem item) throws Exception{
        Statement stmt = conn.createStatement();
        String sql =  "INSERT INTO CounterRegistration " +
           "VALUES ('"+
           item.getServiceIdentifier()+"', "+
           item.getStepCount()+", "+
           item.getStepTime()+", '"+
           item.getCreatedDate()+"', '"+
           item.getModifiedDate()+"', True)";
        stmt.executeUpdate(sql);
        return true;
    }
    public List<StepCountItem> allCounters() throws Exception{
        Statement stmt = conn.createStatement();
        String sql =  "SELECT * from CounterRegistration";
        ResultSet rs = stmt.executeQuery(sql);
        List<StepCountItem> list = new ArrayList<>();
        while(rs.next()){
            list.add(new StepCountItem(rs.getString(1),rs.getLong(2),rs.getInt(3),rs.getTimestamp(4),rs.getTimestamp(5),true));
        }
        return list;
    }
}
