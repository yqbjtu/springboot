package com.yq.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yq.util.other.Address;
import com.yq.util.other.Employee;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple to Introduction
 * className: JsonUtils
 *
 * @author EricYang
 * @version 2019/4/19 13:55
 */
@Slf4j
public class JsonUtils {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Address address=new Address();
        address.setStreet("Lake Park Road");
        address.setCity("Phoenix");
        address.setZipCode(85003);

        Employee emp = new Employee();
        emp.setId(124);
        emp.setName("Alice Celci");
        emp.setAge(24);
        emp.setSalary(new BigDecimal(1800));
        emp.setDesignation("UI Designer");
        emp.setAddress(address);
        emp.setPhoneNumbers(new long[]{246802});

        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("gender", "Female");
        infoMap.put("maritialstatus", "Unmarried");
        emp.setPersonalInformation(infoMap);

        try {
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
            log.info("Employee JSON is\n" + jsonInString);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            objectMapper.writeValue(new File("employee.json"), emp);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Employee emp2 = objectMapper.readValue(new File("employee.json"), Employee.class);
        log.info(emp2.toString());

        Map<?,?> empMap = objectMapper.readValue(new FileInputStream("employee.json"),Map.class);
        for (Map.Entry<?, ?> entry : empMap.entrySet())
        {
            log.info("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
        }
    }

}
