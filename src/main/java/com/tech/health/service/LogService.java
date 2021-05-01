package com.tech.health.service;

import com.tech.health.entity.Log;
import com.tech.health.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Classname LogService
 * @Description None
 * @Date 2019/7/24 12:21
 * @Created by WDD
 */
public interface LogService {
    void insertByLog(Log log);

    PageBean<Log> queryPage(Map<String, Object> paramMap);

    int delByLogIds(List<Integer> ids);
}
