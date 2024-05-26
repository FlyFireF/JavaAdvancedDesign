package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.Order;
import com.flyfiref.dsscm.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface OrderMapper {
	/**
	 * 获取订单及其下属的详情记录
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrders(@Param("oid")long id,
			@Param("userName")String userName,@Param("status")Integer status) throws SQLException;

	/**
	 * 获取订单及其下属的详情记录
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrder() throws SQLException;
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
	public int deleteOrder(@Param("id")long id) throws SQLException;
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

	/**
	 * 通过条件查询-订单表记录数
	 * @return
	 * @throws Exception
	 */
	public int getOrderCount()throws Exception;


	
}
