package knowledgebase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lenovo
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Modulecreate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "moduleid", type = IdType.AUTO)
    private Integer moduleid;

    /**
     * 模块名字
     */
    private String modulename;

    private String createuserid;


}
