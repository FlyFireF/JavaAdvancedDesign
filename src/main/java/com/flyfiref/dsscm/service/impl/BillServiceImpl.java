package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.BillMapper;
import com.flyfiref.dsscm.pojo.Bill;
import com.flyfiref.dsscm.service.BillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {
	@Autowired
	private BillMapper billMapper;

	@Override
	public PageInfo<Bill> getBillList(String productName, Integer providerId,
			Integer isPayment, Integer currentPageNo, Integer pageSize)
			throws Exception {
		// 开启分页
		PageHelper.startPage(currentPageNo, pageSize);
		List<Bill> list = billMapper.getBillList(productName, providerId,isPayment);
		PageInfo<Bill> pi = new PageInfo<Bill>(list);
		return pi;
	}

	@Override
	public int getBillCount(String productName, Integer providerId,
			Integer isPayment) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(productName, providerId, isPayment);
	}

	@Override
	public boolean add(Bill bill) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (billMapper.add(bill) > 0)
			flag = true;
		return flag;
	}

	@Override
	public Bill getBillById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillById(id);
	}

	@Override
	public boolean modify(Bill bill) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (billMapper.modify(bill) > 0)
			flag = true;
		return flag;
	}

	@Override
	public boolean deleteBillById(Integer delId) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (billMapper.deleteBillById(delId) > 0)
			flag = true;
		return flag;
	}

}
