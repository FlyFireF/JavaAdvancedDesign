package com.flyfiref.dsscm.dao;

import com.flyfiref.dsscm.pojo.Info;

import java.util.List;


public interface InfoMapper {
    public List<Info> getInfoList() throws Exception;
    public Info getInfoById(int id) throws Exception;
    public int deleteInfoById(int id) throws Exception;
}
