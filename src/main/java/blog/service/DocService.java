package blog.service;

import blog.mapper.ContentMapper;
import blog.mapper.DocMapper;
import blog.pojo.Content;
import blog.pojo.Doc;
import blog.req.DocQueryReq;
import blog.req.DocSaveReq;
import blog.resp.CommonResp;
import blog.resp.DocQueryResp;
import blog.util.CopyUtil;
import blog.util.SnowFlake;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);

        if (ObjectUtils.isEmpty(doc.getId())) {
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);

            content.setId(doc.getId());
            contentMapper.insert(content);

        } else {

            docMapper.updateByPrimaryKey(doc);

            /**
             * updateByPrimaryKeyWithBLOBs表示更新带大字段的内容
             * 因为我们的 content 包含文本这一大字段内容
             * `content` mediumtext not null comment '内容',
             * 处于效率考虑，Mybatis 自动生成的代码，特地添加了一个大字段更新方法
             */
            int affects = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (affects==0) {
                content.setId(doc.getId());
                contentMapper.insert(content);
            }
        }

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
