package com.yq.dds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现类
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{
	//数据源路由，此方用于产生要选取的数据源逻辑名称
	@Override
	protected Object determineCurrentLookupKey() {
		//从共享线程中获取数据源名称
		return DynamicDataSourceHolder.getDataSource();
	}
}
