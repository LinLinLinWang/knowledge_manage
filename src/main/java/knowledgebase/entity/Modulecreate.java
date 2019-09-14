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
public class Modulecreate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块
     */
    private String module;

    private String createuserid;


}
