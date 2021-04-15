package blog.config;

import blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName SpringMvcConfig
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/3/23 下午7:37
 * @Version 1.0
 **/
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 绑定自定义过滤器
     */
    // @Autowired
    // LogInterceptor logInterceptor;

    @Resource
    LoginInterceptor loginInterceptor;

    // @Resource
    // ActionInterceptor actionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 对部分访问进行拦截
         */
        // registry.addInterceptor(loginInterceptor)
        //         .addPathPatterns("/**") //先对所有部分进行拦截
        //         .excludePathPatterns( //再排除不像拦截的部分
        //                 "/",
        //                 "/doc/list",
        //                 "/doc/all/**",
        //                 "/redis/**",
        //                 "/login",
        //                 "/user/login",
        //                 "/user/logout/**",
        //                 "/doc/find-content/**"
        //         );

        // registry.addInterceptor(actionInterceptor)
        //         .addPathPatterns(
        //                 "/*/save",
        //                 "/*/delete/**",
        //                 "/*/reset-password");
    }
}
