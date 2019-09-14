package knowledgebase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import knowledgebase.entity.User;

import knowledgebase.mapper.UserMapper;
import knowledgebase.service.IUserService;


import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lenovo
 * @since 2019-09-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
