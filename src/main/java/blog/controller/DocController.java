package blog.controller;

import blog.req.DocQueryReq;
import blog.req.DocSaveReq;
import blog.resp.CommonResp;
import blog.resp.DocQueryResp;
import blog.resp.DocSaveResp;
import blog.resp.PageResp;
import blog.service.DocService;
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
     * @return 通用返回值
     */
    @GetMapping("/list")
    public CommonResp list(@Valid DocQueryReq req) {
        PageResp<DocQueryResp> pageResp = docService.list(req);
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
        resp.setContent(pageResp);
        return resp;
    }

    /**
     * 更具 id 进行删除
     * @param id
     * @return 通用返回值
     */
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable("id") Long id) {
        docService.delete(id);
        return new CommonResp();
    }


    /**
     * 保存文档信息
     * 如果 req 有 id,则是对信息进行更新
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@RequestBody @Valid DocSaveReq req) {
        CommonResp<DocSaveResp> resp = new CommonResp<>();
        DocSaveResp docSaveResp = docService.save(req);

        //保存成功后，返回保存的文档的 id
        resp.setContent(docSaveResp);
        return resp;
    }


    /**
     * 根据 id 进行查询
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public CommonResp find(@PathVariable("id") Long id) {
        DocQueryResp resp = docService.find(id);
        CommonResp<DocQueryResp> commonResp = new CommonResp<>();
        commonResp.setContent(resp);
        return commonResp;
    }

    /**
     * 查找文章内容
     * @param id
     * @return
     */
    @GetMapping("/find-content/{id}")
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp<>();
        String content = docService.findContent(id);
        resp.setContent(content);
        return resp;
    }

}
