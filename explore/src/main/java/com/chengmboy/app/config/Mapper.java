package com.chengmboy.app.config;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author cheng_mboy
 */
public interface Mapper<T>  extends BaseMapper<T>, InsertListMapper<T>, ConditionMapper<T> {
}