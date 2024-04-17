package com.flyfiref.dsscm.service;

import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.pojo.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderService{
	/**
	 * 获取订单及其下属的详情记录
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrders(long id,String queryUserName,Integer status) throws SQLException;
	/**
	 * 保存订单
	 * @param order
	 * @throws SQLException
	 */
	public int saveOrder(Order order) throws SQLException;
	/**
	 * 保存订单详情
	 * @throws SQLException
	 */
	public int saveOrderDetail(OrderDetail detail) throws SQLException;
	/**
	 * 删除订单
	 * @param id
	 * @throws SQLException
	 */
	public boolean deleteOrder(long id) throws SQLException;
	/**
	 * 删除订单详情
	 * @throws SQLException
	 */
	public int deleteOrderDetails(long[] ids) throws SQLException;
	
	/**
	 * 更新订单
	 * @param order
	 * @throws SQLException
	 */
	public int updateOrder(Order order) throws SQLException;
	/**
	 * 更新订单详情
	 * @throws SQLException
	 */
	public int updateOrderDetails(OrderDetail detail) throws SQLException;
	
}
