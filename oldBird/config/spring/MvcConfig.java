package com.cheng.config;

import java.util.List;

import com.ecaicn.cbs.business.service.SettingService;
import com.ecaicn.cbs.business.service.WorkContext;
import com.ecaicn.cbs.framework.json.UnifiedObjectMapper;
import com.ecaicn.cbs.webapp.interceptors.SchoolAuthorizeInterceptor;
import com.ecaicn.cbs.webapp.interceptors.WorkModeVerifyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author cheng_mboy
 * @create 2017-08-01-10:39
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MvcConfig.class);

    private final Environment environment;
    private final WorkContext workContext;
    private final SettingService settingService;

    @Autowired
    public MvcConfig(final Environment environment, final WorkContext workContext, final SettingService settingService) {
        this.environment = environment;
        this.workContext = workContext;
        this.settingService = settingService;
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        LOGGER.trace("ConfigureMessageConverters");
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new UnifiedObjectMapper());
        converters.add(converter);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        LOGGER.trace("AddResourceHandlers");

        String path = this.environment.getProperty("storage.data.path") + "coursewares/";
        registry.addResourceHandler("/static/coursewares/**").addResourceLocations("file:" + path);

        String path2 = this.environment.getProperty("storage.data.path") + "update/";
        registry.addResourceHandler("/update/**").addResourceLocations("file:" + path2);
    }

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        LOGGER.trace("ExtendMessageConverters");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new WorkModeVerifyInterceptor(this.workContext));
        registry.addInterceptor(new SchoolAuthorizeInterceptor(this.settingService));
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "Token")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowCredentials(true);
    }

}
