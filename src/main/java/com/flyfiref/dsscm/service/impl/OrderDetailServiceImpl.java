package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.OrderDetailMapper;
import com.flyfiref.dsscm.pojo.OrderDetail;
import com.flyfiref.dsscm.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public List<OrderDetail> getOrderDetailListByProductId(Long id) throws SQLException {
		return orderDetailMapper.getOrderDetailListByProductId(id);
	}


}
