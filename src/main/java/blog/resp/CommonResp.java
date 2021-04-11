package blog.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResp
 * @Description 统一返回数据
 * @Author FARO_Z
 * @Date 2021/4/11 上午11:20
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResp<T> {
    /**
     * 返回是否成功
     */
    private boolean success=true;

    /**
     * 如果不成功，返回错误信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T content;
}
