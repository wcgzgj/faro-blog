package blog.service;

import blog.mapper.DocMapper;
import blog.resp.CommonResp;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName DocService
 * @Description 博客文档
 * @Author FARO_Z
 * @Date 2021/4/11 下午4:44
 * @Version 1.0
 **/
@Service
@RequestMapping("/doc")
public class DocService {

    @Resource
    private DocMapper docMapper;

    @PostMapping("/save")
    public CommonResp save() {
        return null;
    }

    @GetMapping("/list")
    public CommonResp list() {
        return null;
    }

}
