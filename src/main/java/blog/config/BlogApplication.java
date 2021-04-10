package blog.config;

import blog.mapper.TestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan("blog")
@MapperScan("blog.mapper")
public class BlogApplication {

    private static final Logger LOG= LoggerFactory.getLogger(BlogApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApplication.class);
        ConfigurableEnvironment environment = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址:\thttp://127.0.0.1:{}",environment.getProperty("server.port"));

    }

}
