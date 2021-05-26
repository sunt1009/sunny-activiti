package com.sunny.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.entity.VacationOrder;
import com.sunny.activiti.entity.VacationOrderVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 假期管理 Mapper 接口
 * </p>
 *
 * @author sunt
 * @since 2020-06-01
 */
public interface VacationOrderMapper extends BaseMapper<VacationOrder> {

    Page<VacationOrderVo> queryVacationOrder(Page<VacationOrder> page, @Param("userId") String userId);
}
