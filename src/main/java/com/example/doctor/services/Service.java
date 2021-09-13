package com.example.doctor.services;

import com.example.doctor.entities.InputData;
import com.example.doctor.repositories.InputDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("localInputService")
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    @Autowired
    private InputDataRepo inputDataRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void save (MultipartFile file){
        long time1 = System.currentTimeMillis();
        List<InputData> list = new ArrayList<>();
        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((line = in.readLine()) != null) {
                InputData entity = new InputData();
                entity.setData(line);
                list.add(entity);
            }
            inputDataRepo.saveAllAndFlush(list);
        } catch (IOException  e){
            e.printStackTrace();
        }
        logger.info(String.valueOf(System.currentTimeMillis()-time1));
        //5672
    }

    //second variant
    public void save1 (MultipartFile file){
        long time1 = System.currentTimeMillis();
        String sql = "insert into doctor (data) values (?)";
        int i=0;
        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()));
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt =conn.prepareStatement(sql);
            while ((line = in.readLine()) != null) {
                pstmt.setString(1,line);
                pstmt.addBatch();
                i++;
                if (i % 1000 == 0){
                    pstmt.executeBatch();
                    conn.commit();
                }
            }
            pstmt.executeBatch();
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }
        logger.info(String.valueOf(System.currentTimeMillis()-time1));
        //640 ms
    }
}


