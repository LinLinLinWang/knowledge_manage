package knowledgebase.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import knowledgebase.entity.Modulecreate;
import knowledgebase.entity.User;
import knowledgebase.service.IModulecreateService;
import knowledgebase.utils.redis.RedisUtil;
import knowledgebase.utils.token.PwdUtil;
import knowledgebase.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lenovo
 * @since 2019-09-15
 */
@RestController
@RequestMapping("/knowledgebase/modulecreate")
public class ModulecreateController {
    //使用redis
    @Autowired

    private RedisUtil redisUtil;
    @Autowired

    private IModulecreateService modulecreateService;

    //显示已有的板块信息
    @RequestMapping(value = "/showModule")
    public Map<String, String> showModule(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        //获取token
        String token = request.getHeader("Authorization");
        System.out.println("前端获取token"+token);
        //获取token中的userid
        map  = Token.parseJwtToken(token);
        String uid="";
        System.out.println("当前操作者"+(uid=map.get("Uid")));

        map.put("needClear","no"); //默认不需要清空token

        //判断redis中有无token
        if((!redisUtil.hasKey(uid))||(!token.equals(redisUtil.get(uid)))){

              map.put("needClear","ok");
            return map;

    }else{
            //返回模块信息
          List<Modulecreate> modulelist=  modulecreateService.list();
             map.put("data",JSON.toJSONString(modulelist));

        }

return map;
}
}