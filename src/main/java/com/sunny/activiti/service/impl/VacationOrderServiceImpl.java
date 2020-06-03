package com.sunny.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.activiti.common.entity.PageBean;
import com.sunny.activiti.common.util.CommonUtil;
import com.sunny.activiti.entity.VacationOrder;
import com.sunny.activiti.mapper.VacationOrderMapper;
import com.sunny.activiti.service.IVacationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Page<VacationOrder> vacationOrderPage = vacationOrderMapper.selectPage(page, null);
        List<VacationOrder> records = vacationOrderPage.getRecords();
        for (VacationOrder record : records) {
            record.setOrderNo(String.valueOf(record.getVacationId()));
        }
        return vacationOrderPage;
    }

    @Override
    public VacationOrder queryVacation(Long vacationId) {
        QueryWrapper<VacationOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("VACATION_ID",vacationId);
        return vacationOrderMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void updateState(Long vacationId, Integer state) {
        VacationOrder vacationOrder = new VacationOrder();
        vacationOrder.setVacationState(state);
        vacationOrder.setVacationId(vacationId);
        vacationOrderMapper.updateById(vacationOrder);
    }
}
