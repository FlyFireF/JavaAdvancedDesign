package com.flyfiref.dsscm.service.impl;

import com.flyfiref.dsscm.dao.InfoMapper;
import com.flyfiref.dsscm.pojo.Info;
import com.flyfiref.dsscm.service.InfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("infoService")
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoMapper infoMapper;
    @Override
    public PageInfo<Info> getInfoList(Integer currentPageNo,
                                      Integer pageSize) throws Exception{
        PageHelper.startPage(currentPageNo, pageSize);
        List<Info> list = infoMapper.getInfoList();
        PageInfo<Info> pi = new PageInfo<>(list);
        return pi;
    }

    public int deleteInfoById(int id) throws Exception{
        return infoMapper.deleteInfoById(id);
    }
    public Info getInfoById(int id) throws Exception{
        return infoMapper.getInfoById(id);
    }
}
