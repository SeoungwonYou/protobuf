package com.sample.protobuff.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sample.protobuff.domain.SampleData;

@Mapper
public interface SampleMapper {

	public List<SampleData> selectList();


}
