package blog.controller;

import blog.req.DocQueryReq;
import blog.resp.CommonResp;
import blog.resp.DocQueryResp;
import blog.service.DocService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName DocController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 下午8:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    /**
     * 查询电子书信息
     * @param req
     * @return
     */
    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req) {
        List<DocQueryResp> list = docService.list(req);
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        resp.setContent(list);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable("id") Long id) {
        docService.delete(id);
        return new CommonResp();
    }

}
