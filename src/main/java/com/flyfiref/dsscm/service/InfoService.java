package com.flyfiref.dsscm.service;

import com.flyfiref.dsscm.pojo.Info;
import com.github.pagehelper.PageInfo;

public interface InfoService {
    public PageInfo<Info> getInfoList(Integer currentPageNo,
                                      Integer pageSize) throws Exception;
    public int deleteInfoById(int id) throws Exception;

    public Info getInfoById(int id) throws Exception;
}
