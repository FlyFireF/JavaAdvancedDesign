package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.BillMapper;
import com.flyfiref.dsscm.dao.ProviderMapper;
import com.flyfiref.dsscm.pojo.Provider;
import com.flyfiref.dsscm.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;
	@Autowired
	private BillMapper billMapper;

	@Override
	public PageInfo<Provider> getProviderList(String proName, String proCode,
			Integer currentPageNo, Integer pageSize) throws Exception {
		// 开启分页
		PageHelper.startPage(currentPageNo, pageSize);
		List<Provider> list = providerMapper.getProviderList(proName, proCode);
		PageInfo<Provider> pi = new PageInfo<Provider>(list);
		return pi;
	}

	@Override
	public int getproviderCount()
			throws Exception {
		// TODO Auto-generated method stub
		return providerMapper.getProviderCount();
	}

	@Override
	public boolean add(Provider provider) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (providerMapper.add(provider) > 0)
			flag = true;
		return flag;
	}

	@Override
	public Provider getProviderById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return providerMapper.getProviderById(id);
	}

	@Override
	public boolean modify(Provider provider) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (providerMapper.modify(provider) > 0)
			flag = true;
		return flag;
	}

	/**
	 * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作 若订单表中无该供应商的订单数据，则可以删除
	 * 若有该供应商的订单数据，则进行级联删除：先删订单，后删供应商
	 * 
	 */
	@Override
	public boolean smbmsdeleteProviderById(Integer delId) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;
		int billCount = billMapper.getBillCountByProviderId(delId);
		if (billCount > 0) {// 先订单信息
			billMapper.deleteBillByProviderId(delId);
		}
		// 先删除该条记录的上传文件
		Provider provider = providerMapper.getProviderById(delId);
		if (provider.getCompanyLicPicPath() != null
				&& !provider.getCompanyLicPicPath().equals("")) {
			// 删除服务器上企业营业执照照片
			File file = new File(provider.getCompanyLicPicPath());
			if (file.exists()) {
				flag = file.delete();
			}
		}
		if (flag && provider.getOrgCodePicPath() != null
				&& !provider.getOrgCodePicPath().equals("")) {
			// 删除服务器上组织机构代码证照片
			File file = new File(provider.getOrgCodePicPath());
			if (file.exists()) {
				flag = file.delete();
			}
		}
		if (!flag) {
			throw new Exception();
		}
		providerMapper.deleteProviderById(delId);
		return flag;
	}

	@Override
	public List<Provider> getProviderList() throws Exception {
		// TODO Auto-generated method stub
		return providerMapper.getProList();
	}

}
