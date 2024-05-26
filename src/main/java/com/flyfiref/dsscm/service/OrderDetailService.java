package com.flyfiref.dsscm.service;

import com.flyfiref.dsscm.pojo.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailService {

	/**
	 * 获取订单及其下属的详情记录
	 * @return
	 * @throws SQLException
	 */
	public List<OrderDetail> getOrderDetailListByProductId(Long id)  throws SQLException;
}
