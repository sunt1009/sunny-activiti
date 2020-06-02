package com.sunny.activiti.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.FlowRule;
import com.sunny.activiti.entity.VacationOrder;
import com.sunny.activiti.mapper.VacationOrderMapper;
import com.sunny.activiti.service.IVacationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: VacationOrderServiceImpl
 * @Description:
 * @Author: sunt
 * @Date: 2020/6/1 17:43
 * @Version 1.0
 **/
@Service
public class VacationOrderServiceImpl implements IVacationOrderService {

    @Autowired
    private VacationOrderMapper vacationOrderMapper;

    @Override
    @Transactional
    public void insertVacationOrder(VacationOrder vacationOrder) {
        vacationOrder.setVacationId(CommonUtil.genId());
        vacationOrder.setVacationState(0);
        vacationOrder.setUserId("sunny");
        vacationOrder.setSystemCode("1001");
        vacationOrder.setBusiType("2001");
        vacationOrderMapper.insert(vacationOrder);
    }

    @Override
    public Page<VacationOrder> queryVacationOrder(PageBean pageBean) {
        Page<VacationOrder> page = new Page<>(pageBean.getPage(),pageBean.getLimit());
        return vacationOrderMapper.selectPage(page, null);
    }
}
