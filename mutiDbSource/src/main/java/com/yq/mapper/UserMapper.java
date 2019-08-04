package com.yq.mapper;

import com.yq.annotation.SpecifyDBSource;
import com.yq.domain.User;


public interface UserMapper {
	/**
	 * 从test1数据源中获取用户信息
	 */
	@SpecifyDBSource("test1")
	User selectByOddUserId(Integer id);
	/**
	 * 从test2数据源中获取用户信息
	 */
	@SpecifyDBSource("test2")
	User selectByEvenUserId(Integer id);
}