package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.OrderMapper;
import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.pojo.OrderDetail;
import com.flyfiref.dsscm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<Order> getOrders(long id,String queryUserName,Integer status) throws SQLException {
		return orderMapper.getOrders(id,queryUserName,status);
	}

	@Override
	public List<Order> getOrder() throws SQLException {
		return orderMapper.getOrder();
	}

	@Override
	public int saveOrder(Order order) throws SQLException {
		return orderMapper.saveOrder(order);
	}

	@Override
	public int saveOrderDetail(OrderDetail detail) throws SQLException {
		return orderMapper.saveOrderDetail(detail);
	}

	@Override
	public boolean deleteOrder(long id) throws SQLException {
		return orderMapper.deleteOrder(id)==1;
	}

	@Override
	public int deleteOrderDetails(long[] ids) throws SQLException {
		return orderMapper.deleteOrderDetails(ids);
	}

	@Override
	public int updateOrder(Order order) throws SQLException {
		return orderMapper.updateOrder(order);
	}

	@Override
	public int updateOrderDetails(OrderDetail detail) throws SQLException {
		return orderMapper.updateOrderDetails(detail);
	}

	@Override
	public int getOrderCount()
			throws Exception {
		// TODO Auto-generated method stub
		return orderMapper.getOrderCount();
	}

}
