package com.etop.service;

import com.etop.dao.FunctionDAO;
import com.etop.dto.FunctionDto;
import com.etop.pojo.Function;
import com.etop.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类名: FunctionService
 * @描述: 网页过滤服务，与dao进行对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 上午11:46:22
 */
@SuppressWarnings("serial")
@Service("FunctionService")
public class FunctionService implements Serializable {

    @Autowired
    public FunctionDAO functionDAO;

    /**
     * 查找所有权限过滤信息
     * 
     * @return
     */
    public List<Function> findAll() {
        return functionDAO.find("from Function f");
    }

    @Transactional
    public PageUtil<FunctionDto> findAllFunction() {
        return functionDAO.findBySql("select * from t_function",
                FunctionDto.class, false);
    }

    public void saveFunction(Function function) {
        functionDAO.save(function);
    }

    public Function findFunctionById(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id", id);
        return functionDAO.findUniqueResult("from Function f where f.id= :id", params);
    }

    public void updateFunction(Function function) {
        functionDAO.saveOrUpdate(function);
    }

    public void deleteFunction(int id) {
        functionDAO.deleteById(id);
    }
}
