package com.cloud.base.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    String testQuery();

}
