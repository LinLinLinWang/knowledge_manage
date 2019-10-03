package knowledgebase.controller;

import knowledgebase.utils.ip.LoginInfo;
import knowledgebase.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/knowledgebase//index")
public class Index {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginInfo loginInfo;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request) {
        return "此路不通\n" + "Your ip:" + loginInfo.getIpAddr(request) + "\n" + loginInfo.getIpLocation(loginInfo.getIpAddr(request));
    }

    //测试可用性
    @RequestMapping(value = "/alive")
    public String alive(HttpServletRequest request) {
        return "200";
    }
    //获取token
    @RequestMapping(value = "/getToken")
    public String getToken(HttpServletRequest request) {
       //获取token
        String token = request.getHeader("Authorization");
        System.out.println("前端获取token"+token);
        return "此路不通\n" + "Your ip:" + loginInfo.getIpAddr(request) + "\n" + loginInfo.getIpLocation(loginInfo.getIpAddr(request));
    }


}
