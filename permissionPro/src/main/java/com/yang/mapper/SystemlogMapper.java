package com.yang.mapper;

import com.yang.domain.Systemlog;
import java.util.List;

public interface SystemlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Systemlog record);

    Systemlog selectByPrimaryKey(Integer id);

    List<Systemlog> selectAll();

    int updateByPrimaryKey(Systemlog record);
}