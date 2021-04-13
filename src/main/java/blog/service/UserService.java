package blog.service;

import blog.mapper.UserMapper;
import blog.pojo.User;
import blog.pojo.UserExample;
import blog.req.UserQueryReq;
import blog.req.UserSaveReq;
import blog.resp.UserQueryResp;
import blog.resp.UserSaveResp;
import blog.util.CopyUtil;
import blog.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserService
 * @Description 博客文档
 * @Author FARO_Z
 * @Date 2021/4/11 下午4:44
 * @Version 1.0
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG= LoggerFactory.getLogger(UserService.class);

    public UserSaveResp save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);

        if (ObjectUtils.isEmpty(user.getId())) {


            user.setId(snowFlake.nextId());

            userMapper.insert(user);


            /**
             * 保存成功后，返回保存的文档的 id
             */
            UserSaveResp resp = new UserSaveResp();
            resp.setId(user.getId());
            return resp;

        } else {

            userMapper.updateByPrimaryKey(user);


            UserSaveResp resp = new UserSaveResp();
            resp.setId(user.getId());
            return resp;
        }

    }

    /**
     * 获取文章信息
     * @return
     */
    public List<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        //名字不空，说明是查询
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andLoginNameLike("%"+req.getLoginName()+"%");
        }

        List<User> users = userMapper.selectByExample(userExample);
        List<UserQueryResp> userQueryResps = CopyUtil.copyList(users, UserQueryResp.class);
        return userQueryResps;
    }


    /**
     * 删除对应文章信息
     * @param id
     */
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);

    }

    /**
     * 依照 id，查找单个元素
     * @param id
     * @return
     */
    public UserQueryResp find(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        UserQueryResp resp = CopyUtil.copy(user, UserQueryResp.class);
        return resp;
    }



}
