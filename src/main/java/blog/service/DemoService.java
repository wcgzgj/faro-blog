package blog.service;

import blog.mapper.DemoMapper;
import blog.pojo.Demo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DemoService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午2:01
 * @Version 1.0
 **/
@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        List<Demo> demos = demoMapper.selectByExample(null);
        return demos;
    }
}
