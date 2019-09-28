package knowledgebase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import knowledgebase.entity.User;
import knowledgebase.entity.Users;
import knowledgebase.service.IUserService;
import knowledgebase.service.IUsersService;
import knowledgebase.utils.ip.LoginInfo;
import knowledgebase.utils.message.Message;
import knowledgebase.utils.redis.RedisUtil;
import knowledgebase.utils.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import knowledgebase.utils.token.PwdUtil;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.HashMap;
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
@RequestMapping("/knowledgebase/user")
public class UserController {

    //Redis过期时间
    private static final long expire = 5;
    //Redis过期时间单位
    private static final TimeUnit expireTimeUnit = TimeUnit.MINUTES;


    @Autowired
    private IUserService userlService;

    //使用redis
    @Autowired

    private RedisUtil redisUtil;

    @RequestMapping(value = "/IdnumberIsOrNotExist")
    public Map<String, String> phoneIsOrNotExist(HttpServletRequest request, String idnumber) {
        System.out.println("当前注册账号"+idnumber);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid", idnumber);

        Map<String, String> map = new HashMap<>();
        if(userlService.count(queryWrapper) != 0)
        {
            map.put("isExist","yes");
        }
        else{
            map.put("isExist","no");
        }


        return map;
    }

//    @RequestMapping(value = "/getValidatecode")
//    public Map<String, String> getValidatecode(HttpServletRequest request, String userphone) {
//        Map<String, String> map = new HashMap<>();
//        boolean redis = redisUtil.hasKey(userphone);
//        boolean hasRedis = redis && redisUtil.getExpire(userphone, TimeUnit.SECONDS) < ((expire - 1) * 60);
//        //查询该手机号用户是否存在,判断上次验证码发送时间,发送验证码
//        if (!checkUser(userphone)) {
//            if (!redis || hasRedis) {
////                int code = message.getCode(userphone);
//                int code = 1111;
//                if (code != 0) {
//                    //获取验证码存入redis
//                    redisUtil.set(userphone, code + "");
//                    redisUtil.expire(userphone, expire, expireTimeUnit);
//                    map.put("state", "200");
//                    map.put("msg", "验证码发送成功");
//                } else {
//                    map.put("state", "400");
//                    map.put("msg", "验证码发送失败");
//                }
//            } else {
//                map.put("state", "400");
//                map.put("msg", "验证码发送频繁");
//            }
//        } else {
//            map.put("state", "400");
//            map.put("msg", "手机号已存在");
//        }
//
//        return map;
//    }
//
//    //查询该手机号用户是否存在
//    private boolean checkUser(String userphone) {
//        Map<String, String> map = new HashMap<>();
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("phone", userphone);
//        return userslService.count(queryWrapper) != 0 ? true : false;
//    }


    @RequestMapping(value = "/registe")
    public Map<String, String> registeuser(HttpServletRequest request, String idnumber,String password,String uname) {
        System.out.println("当前注册账号"+idnumber);
        System.out.println("密码"+password);
        System.out.println("名字"+uname);
        System.out.println("加密后的密码"+PwdUtil.encode(password));

        Map<String, String> map = new HashMap<>();
         User user=new User();
         user.setUname(uname);
         user.setPassword(PwdUtil.encode(password));
         user.setIp(LoginInfo.getIpAddr(request));
         user.setUserid(idnumber);
        map.put("saveuser","no");
        try{


            if(userlService.save(user)){
                map.put("saveuser","yes");
            }

        }catch (Exception e){
            map.put("saveuser","no");
        }


        return map;
    }
    @RequestMapping(value = "/loginByPassword")
    public Map<String, String> loginByPassword(HttpServletRequest request, String idnumber,String password) {
        System.out.println("当前账号"+idnumber);
        System.out.println("密码"+password);
        User user=new User();
        Map<String, String> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid", idnumber);
        if(userlService.list(queryWrapper).size()!= 0)
        {

            map.put("isExist","yes");

            System.out.println("后台密码"+((User)userlService.list(queryWrapper).get(0)).getPassword());
            System.out.println("输入的"+PwdUtil.encode(password));
            if(((User)userlService.list(queryWrapper).get(0)).getPassword().equals(PwdUtil.encode(password))){
                map.put("isThisGuy","yes");
                //生成token
                String token= Token.getJwtToken((User)userlService.list(queryWrapper).get(0));
                map.put("token",token);
                //存在redis数据库中
               if(!redisUtil.hasKey(idnumber)){
                   System.out.println("redis没有记录");
                   try{
                       System.out.println("redis插入记录");
                       redisUtil.set(idnumber,token);
                       redisUtil.expire(idnumber,9000,TimeUnit.SECONDS);
                   }catch (Exception e){

                       System.out.println("redis存储失败或者修改时间失败");

                   }


               }else{
                //有记录 //覆盖

                   redisUtil.set(idnumber,token);
                   redisUtil.expire(idnumber,9000,TimeUnit.SECONDS);
               }
            }else{
                map.put("isThisGuy","no");
                map.put("token","notoken");
            }
            //
        }
        else{
            map.put("isExist","no");
        }


        return map;
    }

}
