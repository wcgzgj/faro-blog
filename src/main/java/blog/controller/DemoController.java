package blog.controller;

import blog.pojo.Demo;
import blog.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 上午2:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger LOG= LoggerFactory.getLogger(DemoController.class);

    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public String list() {
        List<Demo> list = demoService.list();
        return list.toString();
    }

}
