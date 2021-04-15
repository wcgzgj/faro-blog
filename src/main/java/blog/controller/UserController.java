package blog.controller;

import blog.req.UserLoginReq;
import blog.req.UserQueryReq;
import blog.req.UserResetPasswordReq;
import blog.req.UserSaveReq;
import blog.resp.*;
import blog.service.UserService;
import blog.util.SnowFlake;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/4/11 下午8:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG= LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * redis 工具
     */
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 查询电子书信息
     * @param req
     * @return 通用返回值
     */
    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        PageResp<UserQueryResp> pageResp = userService.list(req);
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
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
        userService.delete(id);
        return new CommonResp();
    }


    /**
     * 保存文档信息
     * 如果 req 有 id,则是对信息进行更新
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp save(@RequestBody @Valid UserSaveReq req) {
        //对密码进行 md5 加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));

        CommonResp<UserSaveResp> resp = new CommonResp<>();
        UserSaveResp userSaveResp = userService.save(req);

        //保存成功后，返回保存的文档的 id
        resp.setContent(userSaveResp);
        return resp;
    }


    @PostMapping("/reset-password")
    public CommonResp resetPassword(@RequestBody @Valid UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        LOG.info("重置、后端加密："+req.getPassword());
        userService.resetPassword(req);

        CommonResp resp = new CommonResp<>();
        return resp;
    }


    /**
     * 根据 id 进行查询
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public CommonResp find(@PathVariable("id") Long id) {
        UserQueryResp resp = userService.find(id);
        CommonResp<UserQueryResp> commonResp = new CommonResp<>();
        commonResp.setContent(resp);
        LOG.info("find返回的数据为:"+commonResp );
        return commonResp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        LOG.info("登录、后端加密："+req.getPassword());
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        /**
         * redis需要我们手动生成一个不会重复的字符串，作为 token
         * 用 UUID，雪花算法，都可以
         */
        Long token = snowFlake.nextId();
        // {} 是 LOG 中的占位符
        LOG.info("生成单点登录 token:{}",token);
        userLoginResp.setToken(token.toString());

        /**
         * token: 手动生成的 token 作为 redis 的 key
         *
         * JSONObject.toJSON(userLoginResp): 作为 redis 的 value,
         * 这个 value 需要序列化，可以让 userLoginResp 实现Serializable接口，
         * 也可以像下面一样，将其转成 JSON 字符串
         *
         * 3600*24: 设置超时时间
         */
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSON(userLoginResp),3600*24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;

    }


}
