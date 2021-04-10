package blog.service;

import blog.mapper.TestMapper;
import blog.pojo.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午1:34
 * @Version 1.0
 **/
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.getList();
    }
}
