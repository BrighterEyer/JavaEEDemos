package com.code.controller;


import com.code.service.ICityCodeService;
import com.code.service.IJsonService;
import com.code.utils.AddressUtils;
import com.code.utils.HttpUtils;
import com.code.utils.ResultMap;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IJsonService jsonService;
    @Autowired
    private ICityCodeService cityCodeService;

    //citykey
    //https://www.cnblogs.com/danyueweb/p/3521973.html

    //http://wthrcdn.etouch.cn/weather_mini?citykey=101010100
    private final String weatherApi = "http://wthrcdn.etouch.cn/weather_mini";

    private final String weatherApi2 = "https://bird.ioliu.cn/weather?city=珠海";
    private final String weatherApi3 = "https://www.sojson.com/open/api/weather/json.shtml?city=珠海";

    //    单天天气接口地址
//    http://www.weather.com.cn/data/sk/101010100.html
//    http://www.weather.com.cn/data/cityinfo/101010100.html
//    http://m.weather.com.cn/data/101010100.html
    private final String weatherApi4 = "http://www.weather.com.cn/data/sk/101110101.html";

    @RequestMapping("/index")
    @CrossOrigin
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public ResultMap getData(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
//            String ip = ((JSONObject) JSONObject.fromObject(HttpUtils.doPost("https://bird.ioliu.cn/ip", null).getResponse()).get("data")).get("ip").toString();
            String ip = request.getParameter("ip");
            String cityName = AddressUtils.getAddressByIp(ip).getCity();
            String cityCode = cityCodeService.getCodeByCity(cityName);
            String weather = HttpUtils.doGet(weatherApi + "?citykey=" + cityCode, null).getResponse();
            weather = new String(weather.getBytes("iso-8859-1"), "utf-8");
            JSONObject data = (JSONObject) (JSONObject.fromObject(weather).get("data"));
            resultMap.put("weatherData", data);
            return ResultMap.okData(resultMap);
        } catch (Exception e) {
            if (resultMap.size() > 0) {
                return ResultMap.okData(resultMap);
            }
            return ResultMap.fail();
        }
    }

    //经常挂
    @RequestMapping("/getData1")
    @ResponseBody
    public ResultMap getData1(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
//            String ip = ((JSONObject) JSONObject.fromObject(HttpUtils.doPost("https://bird.ioliu.cn/ip", null).getResponse()).get("data")).get("ip").toString();
            String ip = request.getParameter("ip");
            String cityName = AddressUtils.getAddressByIp(ip).getCity();
            String cityCode = jsonService.getCityByName(cityName).get("city_code").toString();
            String weather = HttpUtils.doGet("http://t.weather.sojson.com/api/weather/city/" + cityCode, null).getResponse();
            weather = new String(weather.getBytes("iso-8859-1"), "utf-8");
            JSONObject weatherData = (JSONObject) (JSONObject.fromObject(weather).get("data"));
            resultMap.put("weatherData", weatherData);
            return ResultMap.okData(resultMap);
        } catch (Exception e) {
            if (resultMap.size() > 0) {
                return ResultMap.okData(resultMap);
            }
            return ResultMap.fail();
        }
    }

    @RequestMapping("/getLocation")
    @ResponseBody
    public Object getLocation(HttpServletRequest request) {
        try {
//            String ip = ((JSONObject) JSONObject.fromObject(HttpUtils.doPost("https://bird.ioliu.cn/ip", null).getResponse()).get("data")).get("ip").toString();
            String ip = request.getParameter("ip");
            return ResultMap.okData(AddressUtils.getAddressByIp(ip));
        } catch (Exception e) {
            return ResultMap.fail();
        }
    }

    private final String ipApi = "http://pv.sohu.com/cityjson?ie=utf-8";

    @ResponseBody
    @RequestMapping("/getCityJson")
    public String getMySeatSuccess(@RequestParam("callback") String callback) {
        String jsonStr = HttpUtils.doGet(ipApi, null).getResponse();
        jsonStr = jsonStr.substring(jsonStr.indexOf("{"), jsonStr.indexOf("}") + 1);
        System.out.println("callback");
        System.out.println(callback);
        return callback + "(" + JSONObject.fromObject(jsonStr) + ")";
    }

}
