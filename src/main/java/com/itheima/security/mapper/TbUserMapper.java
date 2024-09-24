package com.itheima.security.mapper;

import com.itheima.security.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.itheima.security.pojo.TbUser
 */
@Mapper
public interface TbUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    TbUser findByUserName(@Param("userName") String userName);

}




