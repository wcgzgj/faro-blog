package blog.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageResp
 * @Description
 * @Author FARO_Z
 * @Date 2021/3/24 下午3:22
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResp<T> {
    /**
     * 因为前端已经知道点击的是哪一页了
     * 所以后端没必要返回页数
     */
    private long total;
    private List<T> list;


}
