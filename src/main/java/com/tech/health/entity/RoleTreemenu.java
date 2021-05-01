package com.tech.health.entity;

import java.io.Serializable;
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
public class RoleTreemenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer rid;

    private Integer tid;


}
