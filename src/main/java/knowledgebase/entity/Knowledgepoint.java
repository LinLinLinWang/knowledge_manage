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
public class Knowledgepoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pointid", type = IdType.AUTO)
    private Integer pointid;

    private String question;

    private String answer;

    private String createuserid;

    private LocalDateTime createtime;


}
