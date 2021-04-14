package blog.service;

import blog.mapper.ContentMapper;
import blog.mapper.DocMapper;
import blog.pojo.Content;
import blog.pojo.Doc;
import blog.pojo.DocExample;
import blog.req.DocQueryReq;
import blog.req.DocSaveReq;
import blog.resp.DocQueryResp;
import blog.resp.DocSaveResp;
import blog.resp.PageResp;
import blog.util.CopyUtil;
import blog.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
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

    private static final Logger LOG= LoggerFactory.getLogger(DocService.class);

    public DocSaveResp save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);

        if (ObjectUtils.isEmpty(doc.getId())) {


            doc.setId(snowFlake.nextId());
            doc.setDocCount(0);
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);


            content.setId(doc.getId());
            contentMapper.insert(content);


            /**
             * 保存成功后，返回保存的文档的 id
             */
            DocSaveResp resp = new DocSaveResp();
            resp.setId(doc.getId());
            return resp;

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

            DocSaveResp resp = new DocSaveResp();
            resp.setId(doc.getId());
            return resp;
        }

    }

    /**
     * 获取文章信息
     * @return
     */
    public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();

        /**
         * 名字不空，说明是查询
         *
         * 分页之所以可以和查询放在一起，是因为当分页的时候
         * 不会传进 name,所以模糊查询还是将所有内容查询进来
         * 而且 SQL 显示的是 分页和模糊查询写在一起的
         * 所以，也不会出现查询两次 SQL 的情况
         */
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%"+req.getName()+"%");
        }


        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docs = docMapper.selectByExample(docExample);
        List<DocQueryResp> docQueryResps = CopyUtil.copyList(docs, DocQueryResp.class);

        PageInfo info = new PageInfo(docs);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(docQueryResps);

        return pageResp;
    }


    /**
     * 删除对应文章信息
     * @param id
     */
    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
        /**
         * 在删除文章的时候
         * 也要删除文章对应的文章内容
         *
         * 不然长此以往，会产生大量废弃数据
         */
        contentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 依照 id，查找单个元素
     * @param id
     * @return
     */
    public DocQueryResp find(Long id) {
        Doc doc = docMapper.selectByPrimaryKey(id);
        DocQueryResp resp = CopyUtil.copy(doc, DocQueryResp.class);
        return resp;
    }

    /**
     * 查找文章内容
     * @param id
     * @return 只用返回String 类型的文章内容
     */
    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(content)) {
            return "";
        }
        return content.getContent();
    }

}
