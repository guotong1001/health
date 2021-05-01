package com.tech.health.service.impl;

import com.tech.health.entity.Food;
import com.tech.health.mapper.FoodMapper;
import com.tech.health.service.IFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yansiqi
 * @since 2021-04-26
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

}
