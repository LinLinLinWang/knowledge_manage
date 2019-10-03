package knowledgebase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2019-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Knowledgepoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pointid", type = IdType.AUTO)
    private Integer pointid;

    private Integer moduleid;

    private String question;

    private String answer;

    private String createuserid;

    private LocalDateTime createtime;

    /**
     * 0已解决
     */
    private Integer resolved;


}
