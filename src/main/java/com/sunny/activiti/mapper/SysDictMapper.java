package com.sunny.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunny.activiti.entity.SysDict;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 字典类型值表 Mapper 接口
 * </p>
 *
 * @author sunt
 * @since 2020-06-01
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 根据字典类型编码查询字典信息
     * @param dictTypeCode
     * @return
     */
    @Select("SELECT t1.*,t2.DICT_TYPE_NAME from sys_dict t1,sys_dict_type t2 WHERE t1.DICT_TYPE_CODE = t2.DICT_TYPE_CODE and t1.DICT_TYPE_CODE = #{dictTypeCode}")
    List<SysDict> querySysDictInfo(int dictTypeCode);
}
