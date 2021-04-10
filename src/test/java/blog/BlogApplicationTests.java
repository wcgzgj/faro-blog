package blog;

import blog.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    TestMapper testMapper;

    @Test
    void contextLoads() {
        List<blog.pojo.Test> list = testMapper.getList();
        System.out.println(list);
    }

}
