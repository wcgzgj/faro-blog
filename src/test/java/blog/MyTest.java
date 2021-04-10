package blog;

import blog.mapper.TestMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午1:22
 * @Version 1.0
 **/
public class MyTest {
    @Resource
    TestMapper testMapper;

    @Test
    void mybatisTest() {
        List<blog.pojo.Test> list = testMapper.getList();
        System.out.println(list);
    }
}
