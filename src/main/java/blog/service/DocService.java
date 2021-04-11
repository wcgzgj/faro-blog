package blog.service;

import blog.mapper.DocMapper;
import blog.pojo.Doc;
import blog.req.DocQueryReq;
import blog.resp.CommonResp;
import blog.resp.DocQueryResp;
import blog.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DocService
 * @Description 博客文档
 * @Author FARO_Z
 * @Date 2021/4/11 下午4:44
 * @Version 1.0
 **/
@Service
public class DocService {

    @Resource
    private DocMapper docMapper;

    public CommonResp save() {
        return null;
    }

    /**
     * 获取文章信息
     * @return
     */
    public List<DocQueryResp> list(DocQueryReq req) {
        List<Doc> docs = docMapper.selectByExample(null);
        List<DocQueryResp> docQueryResps = CopyUtil.copyList(docs, DocQueryResp.class);

        return docQueryResps;
    }


    /**
     * 删除对应文章信息
     * @param id
     */
    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }

}
