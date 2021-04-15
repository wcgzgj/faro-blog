package blog.service;

import blog.exception.BusinessException;
import blog.exception.BusinessExceptionCode;
import blog.mapper.UserMapper;
import blog.pojo.User;
import blog.pojo.UserExample;
import blog.req.UserLoginReq;
import blog.req.UserQueryReq;
import blog.req.UserResetPasswordReq;
import blog.req.UserSaveReq;
import blog.resp.PageResp;
import blog.resp.UserLoginResp;
import blog.resp.UserQueryResp;
import blog.resp.UserSaveResp;
import blog.util.CopyUtil;
import blog.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        //名字不空，说明是查询
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andLoginNameLike("%" + req.getLoginName() + "%");
        }

        /**
         * 使用 PageHelper 帮助后台分页
         */
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> users = userMapper.selectByExample(userExample);
        List<UserQueryResp> userQueryResps = CopyUtil.copyList(users, UserQueryResp.class);


        PageInfo<User> info = new PageInfo<>(users);

        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(userQueryResps);

        return pageResp;
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

    /**
     * 修改密码
     * @param req
     */
    public void resetPassword(UserResetPasswordReq req) {
        User user=CopyUtil.copy(req,User.class);
        /**
         * 因为我们的 UserResetPasswordReq只有 id 和 password
         * 所以我们在更新的时候，要使用 selective，这样才不会让其他值变成 null
         */
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 依据登录名去查询数据库
     * 不使用模糊查询
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);

        /**
         * 虽然查出来的最多只可能是一条
         * 但是，我们还是要用 list 去接收
         */
        List<User> userList = userMapper.selectByExample(userExample);

        /**
         * 这里不用 size()==0去判断，是因为担心 userList 还可能是 null
         */
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 用户登录
     * @param req
     * @return
     */
    public UserLoginResp login(UserLoginReq req) {
        User userDB = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDB)) {
            LOG.info("用户名不存在, {}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (userDB.getPassword().equals(req.getPassword())) {
                //登陆成功
                UserLoginResp userLoginResp = CopyUtil.copy(userDB, UserLoginResp.class);
                return userLoginResp;
            } else {
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), userDB.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }


}
