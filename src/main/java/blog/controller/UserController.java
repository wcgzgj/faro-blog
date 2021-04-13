package blog.controller;

import blog.req.UserQueryReq;
import blog.req.UserSaveReq;
import blog.resp.CommonResp;
import blog.resp.UserQueryResp;
import blog.resp.UserSaveResp;
import blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    /**
     * 查询电子书信息
     * @param req
     * @return 通用返回值
     */
    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        List<UserQueryResp> list = userService.list(req);
        CommonResp<List<UserQueryResp>> resp = new CommonResp<>();
        resp.setContent(list);
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
        CommonResp<UserSaveResp> resp = new CommonResp<>();
        UserSaveResp userSaveResp = userService.save(req);

        //保存成功后，返回保存的文档的 id
        resp.setContent(userSaveResp);
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


}
