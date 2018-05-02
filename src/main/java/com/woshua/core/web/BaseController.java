package com.woshua.core.web;

import com.woshua.core.utils.IPUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Acer on 2017/3/27.
 */
public class BaseController {

    /**
     * 获取用户真实IP地址
     */
    public String getIpAddress(HttpServletRequest request) {
        return IPUtils.getIpAddress(request);
    }

    public Pageable getPage(HttpServletRequest request) {
        PageRequest pageRequest = null;
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            String sort = request.getParameter("sort");
            String direction = request.getParameter("direction");
            pageRequest = new PageRequest(page, size, Sort.Direction.fromStringOrNull(direction), sort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageRequest;
    }
}
