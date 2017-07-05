/**
 * @(#)JsonView.java Copyright 2011 fzunite, Inc. All rights reserved.
 */
package com.woshua.core.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 JSON View
 *
 * @author fengyuan
 * @version 1.0, 2011-4-27
 */
public class WebJsonView extends AbstractView {

    private static Logger logger = Logger.getLogger(WebJsonView.class);

    private Map<String, Object> map;

    private Header header;

    private String jsonString;

    private Object object;

    public WebJsonView() {
    }

    /**
     * 需要转换的map,不用在map中加入header
     *
     * @param map
     */
    public WebJsonView(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * 将对象转换成json
     *
     * @param object 需要转换的json的对象
     */
    public WebJsonView(Object object) {
        this.object = object;
    }

    /**
     * 返回头信息
     *
     * @param header
     */
    public WebJsonView(Header header) {
        this.header = header;
    }

    /**
     * 只有一个返回内容的
     *
     * @param name
     * @param value
     */
    public WebJsonView(String name, Object value) {
        this.map = new HashMap<String, Object>();
        map.put(name, value);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void renderMergedOutputModel(Map model,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, Object> content = new HashMap<String, Object>();
        if(map != null && !map.isEmpty()){
            content.put("content", map);
        }
        if (header == null) {
            Map<String, Object> headerMap = new HashMap<String, Object>();
            headerMap.put("flag", 1);
            content.put("header", headerMap);
        } else {
            content.put("header", header);
        }
        if (object != null) {
            content.put("content", object);
        }
        writeJSON(response, content);
    }

    private void writeJSON(HttpServletResponse response,
                           Object object) {
        response.setContentType("application/json;charset=utf-8");

        ObjectMapper mapper = new ObjectMapper();

        //User类转JSON
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonString);
        } catch (IOException e) {
            logger.error(getClass(), e);
        } finally {
            out.flush();
            out.close();
        }
    }

    public Header getHeader() {
        return header;
    }

    /**
     * 获得json的字符串
     *
     * @return
     */
    public String getJsonString() {
        return jsonString;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

}
