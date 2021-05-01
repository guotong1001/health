package com.tech.health.service.impl;

import com.tech.health.entity.Icon;
import com.tech.health.mapper.IconMapper;
import com.tech.health.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname IconServiceImpl
 * @Description None
 * @Date 2019/7/22 11:19
 * @Created by WDD
 */
@Service
public class IconServiceImpl implements IconService {

    @Autowired
    private IconMapper iconMapper;

    @Override
    public List<Icon> selectAll() {
        return iconMapper.selectAll();
    }
}
