package blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @ClassName Test
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午1:15
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Test {
    private Integer id;
    private String name;
}
