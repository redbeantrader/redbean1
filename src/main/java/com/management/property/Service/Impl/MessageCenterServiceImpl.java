package com.management.property.Service.Impl;

import com.management.property.Service.MessageCenterService;
import com.management.property.mapper.MessageCenterMapper;
import com.management.property.pojo.MessageCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageCenterServiceImpl implements MessageCenterService {
    @Autowired
    MessageCenterMapper messageCenterMapper;

    @Override
    public int deleteByPrimaryKey(Integer messageId) {
        return messageCenterMapper.deleteByPrimaryKey(messageId);
    }

    @Override
    public int insert(MessageCenter record) {
        return messageCenterMapper.insert(record);
    }

    @Override
    public int insertSelective(MessageCenter record) {
        return messageCenterMapper.insertSelective(record);
    }

    @Override
    public MessageCenter selectByPrimaryKey(Integer messageId) {
        return messageCenterMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public int updateByPrimaryKeySelective(MessageCenter record) {
        return messageCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MessageCenter record) {
        return messageCenterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MessageCenter> selectAll(Integer limit, Integer page, Integer id) {
        return messageCenterMapper.selectAll(limit,page,id);
    }

    @Override
    public int count() {
        return messageCenterMapper.count();
    }
}
