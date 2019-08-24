package com.yq.mapper;


import java.util.List;

import com.yq.pojo.FKRole;
import com.yq.pojo.FKUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;


public interface UserMapper {
	
	// 根据loginName查询用户信息，同时关联查询出用户的权限
	@Select("select * from my_user where login_name = #{loginName}")
	 @Results({  
	        @Result(id=true,column="id",property="id"),  
	        @Result(column="login_name",property="loginName"),
	        @Result(column="username",property="username"),
			 @Result(column="password",property="password"),
			 @Result(column="id",property="roles",
	        many=@Many(select="findRoleByUser",
	        fetchType=FetchType.EAGER))  
	     })
	FKUser findByLoginName(String loginName);
	
	// 根据用户id关联查询用户的所有权限
	@Select(" SELECT r.id,authority FROM my_role r,my_user_role ur "
			+ " WHERE r.id = ur.role_id AND ur.user_id = #{id}")
	List<FKRole> findRoleByUser(Long id);

}
