package com.hong.myblog.service.Implement;

import com.hong.myblog.dao.TypeDao;
import com.hong.myblog.entity.Type;
import com.hong.myblog.service.Interface.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 分类业务层接口实现类
 * @author: hjx
 * @time: 2021年05月10日 21:00
 */

@Service

public class TypeService_imp implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
//    @Transactional
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    @Transactional
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }
}
