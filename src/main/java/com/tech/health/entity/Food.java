package com.tech.health.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yansiqi
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 食物名称
     */
    private String foodName;

    /**
     * 热量(大卡)
     */
    private Integer quantityHeat;

    /**
     * 可食部分(克)
     */
    private Integer dosage;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
