package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.News;
import com.flyfiref.dsscm.pojo.OrderDetail;
import com.flyfiref.dsscm.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailMapper {
	/**
	 * 通过条件查询-orderdetailList
	 * @throws Exception
	 */
	public List<OrderDetail> getOrderDetailListByProductId(Long id)throws SQLException;


}
