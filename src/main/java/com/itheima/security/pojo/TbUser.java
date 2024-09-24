package com.itheima.security.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbUser implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String roles;

    private static final long serialVersionUID = 1L;
}