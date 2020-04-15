package rapidfeedback.backend.initial.CommonTools.Threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cyt
 * @date 2020/4/15
 */

@Configuration
public class GlobalThreadPool {

    @Bean("serviceThreadPool")
    public ThreadPoolExecutor serviceThreadPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 200,60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        return executor;
    }

    @Bean("controllerThreadPool")
    public ThreadPoolExecutor controllerThreadPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 200,60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        return  executor;
    }

}
