package knowledgebase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import knowledgebase.entity.Knowledgepoint;

import knowledgebase.mapper.KnowledgepointMapper;
import knowledgebase.service.IKnowledgepointService;

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
public class KnowledgepointServiceImpl extends ServiceImpl<KnowledgepointMapper, Knowledgepoint> implements IKnowledgepointService {

}
