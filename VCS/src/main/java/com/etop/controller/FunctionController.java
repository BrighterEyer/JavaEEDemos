package com.etop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.dto.FunctionDto;
import com.etop.pojo.Function;
import com.etop.service.FunctionService;
import com.etop.service.PermissionService;
import com.etop.service.RoleService;
import com.etop.utils.PageUtil;

/**
 * @类名: FunctionController
 * @描述: 处理过滤网址出的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月27日 下午3:31:54
 */
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseController {
    Map<String,Object> map = new HashMap<String,Object>();
    private final static Logger log = Logger
            .getLogger(FunctionController.class);
    @Autowired
    private FunctionService functionService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/functionList.html")
    public String getFunctions() {
        log.info("/functionList.html");
        return "/functionList.jsp";
    }

    /**
     * @标题: getFunctionList
     * @描述: 返回分页的json数据
     * @参数 @return 设定文件
     * @返回 Object 返回类型
     * @throws
     * @日期 2015年5月27日 下午3:55:12
     */
    @RequestMapping("/get_functions.html")
    @ResponseBody
    public Object getFunctionList() {
        log.info("/get_functions.html");
        PageUtil<FunctionDto> functionList = functionService.findAllFunction();
        return functionList;
    }

    @RequestMapping("/add.html")
    @ResponseBody
    public Object addFunction(String value,int permission_id,int role_id,String type) {
        log.info("/add.html");
        Function function = new Function();
        function.setPermission_id(permission_id);
        function.setRole_id(role_id);
        function.setValue(value);
        function.setType(type);
        functionService.saveFunction(function);
        map.put(SUCCESS, true);
        return map;
    }

    @RequestMapping("/edit.html")
    @ResponseBody
    public Object updateFunction(int id,String value,String type) {
        log.info("/edit.html");
        Function function = functionService.findFunctionById(id);
        if (function==null||"".equals(function)) {
            map.put(SUCCESS, false);
            return map;
        }
        function.setValue(value);
        function.setType(type);
        functionService.updateFunction(function);
        map.put(SUCCESS, true);
        return map;
    }

    @RequestMapping("/del.html")
    @ResponseBody
    public Object deleteFunction(int id) {
        log.info("/del.html");
        functionService.deleteFunction(id);
        map.put(SUCCESS, true);
        return map;
    }
}
