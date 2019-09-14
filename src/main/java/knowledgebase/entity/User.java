package knowledgebase.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private String userid;

    /**
     * 名字
     */
    private String uname;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型  0普通用户 1管理员
     */
    private Integer type;

    private String ip;


}
