package blog.controller;

import blog.pojo.Test;
import blog.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午1:35
 * @Version 1.0
 **/
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG= LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;

    @GetMapping("/list")
    public void list() {
        List<Test> list = testService.list();
        LOG.info(list.toString());
    }
}
