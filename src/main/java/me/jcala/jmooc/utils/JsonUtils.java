package me.jcala.jmooc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum  JsonUtils {
    instance;

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object obj){
        String str= "";
        try {
            str=mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.warn(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return str;
    }

    public List<String> readJsonToFileList(String json) {

        if (json==null||json.trim().isEmpty()) return new ArrayList<>();

        List<String> list;

        try {
            list=mapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            list=new ArrayList<>();
            logger.warn("UpFile list反序列化出现错误:"+e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public HashMap<Character, String> readJsonToExeMap(String jsonStr) {
        if (jsonStr==null || jsonStr.trim().isEmpty()) return new HashMap<>();
        HashMap<Character, String> modelMap;
        try {
            modelMap = mapper.readValue(jsonStr.trim(), new TypeReference<HashMap<Character, String>>() {
            });
            if (modelMap==null) return new HashMap<>();
        } catch (IOException e) {
            logger.warn(e.getLocalizedMessage());
            modelMap=new HashMap<>();
            e.printStackTrace();
        }
        return modelMap;
    }

}
