package knowledgebase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lenovo
 * @since 2019-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Actionlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "autoid", type = IdType.AUTO)
    private Integer autoid;

    private String userid;

    private Integer pointid;

    private LocalDateTime actiontime;

    /**
     * 操作类型 0创建  1删除 2编辑
     */
    private Integer type;

    /**
     * 做了什么
     */
    private String content;


}
