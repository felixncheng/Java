package com.cheng.config;


import com.ecaicn.cbs.apiclient.bis.SyncSchoolInfo.BisSyncClient;
import com.ecaicn.cbs.apiclient.bis.SyncSchoolInfo.HttpBisSyncClient;
import com.ecaicn.cbs.apiclient.cbs.Sync.CbsSyncClient;
import com.ecaicn.cbs.apiclient.cbs.Sync.HttpCbsSyncClient;
import com.ecaicn.cbs.apiclient.cms.SyncCourseware.CmsSyncClient;
import com.ecaicn.cbs.apiclient.cms.SyncCourseware.HttpCmsSyncClient;
import com.ecaicn.cbs.business.enums.WorkMode;
import com.ecaicn.cbs.business.repository.FileRepository;
import com.ecaicn.cbs.business.service.CoursewareImageService;
import com.ecaicn.cbs.business.service.ImageStorageService;
import com.ecaicn.cbs.business.service.SettingService;
import com.ecaicn.cbs.business.service.WorkContext;
import com.ecaicn.cbs.business.service.impl.CoursewareImageServiceImpl;
import com.ecaicn.cbs.business.service.impl.ImageStorageServiceImpl;
import com.ecaicn.cbs.business.service.impl.TokenProviderImpl;
import com.ecaicn.cbs.business.service.impl.WorkContextImpl;
import com.ecaicn.cbs.common.id.IdGenerator;
import com.ecaicn.cbs.common.id.SnowflakeIdGenerator;
import com.ecaicn.cbs.framework.mime.DefaultMimeDictionary;
import com.ecaicn.cbs.framework.mime.MimeDictionary;
import com.ecaicn.cbs.framework.security.api.TokenProvider;
import com.ecaicn.cbs.model.SettingKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * @author cheng_mboy
 */
@Configuration
public class BeanConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConfig.class);

    @Lazy
    @Primary
    @Bean
    public static IdGenerator idGenerator() {
        LOGGER.trace("Create bean: @Primary IdGenerator");
        return new SnowflakeIdGenerator(0L, 0L);
    }

    @Lazy
    @Primary
    @Bean
    public static MimeDictionary mimeDictionary() {
        LOGGER.trace("Create bean: @Primary MimeDictionary");
        return new DefaultMimeDictionary();
    }

    @Lazy
    @Primary
    @Bean
    public static WorkContext workCo ntext(final Environment environment) {
        LOGGER.trace("Create bean: @Primary " + WorkContext.class.getName());
        WorkContextImpl ctx = new WorkContextImpl();
        ctx.setMode("school".equals(environment.getProperty("app.mode")) ? WorkMode.SCHOOL : WorkMode.CLASSROOM);
        ctx.setDebug("true".equals(environment.getProperty("app.debug")));
        return ctx;
    }

    @Lazy
    @Primary
    @Bean
    public static CoursewareImageService coursewareImageService(final Environment environment) {
        LOGGER.trace("Create bean: @Primary CoursewareImageService");
        return new CoursewareImageServiceImpl(environment.getProperty("storage.data.path") + "/preview");
    }

    @Lazy
    @Primary
    @Bean
    public static ImageStorageService imageStorageService(final Environment environment, final IdGenerator idGenerator, final FileRepository fileRepository) {
        LOGGER.trace("Create bean: @Primary ImageStorageService");
        String basePath = environment.getProperty("storage.data.path") + "/files";
        return new ImageStorageServiceImpl(basePath, idGenerator, fileRepository);
    }

    @Lazy
    @Bean
    public static CmsSyncClient getHttpCmsSyncClient(@Value("${apiclient.cms.baseUrl}") String baseUrl, TokenProvider tokenProvider) {
        return new HttpCmsSyncClient(baseUrl, tokenProvider);
    }

    @Lazy
    @Bean
    public static CbsSyncClient getHttpCbsSyncClient(SettingService service, TokenProvider tokenProvider) {
        String endPoint = service.get(SettingKeys.SCHOOL_SERVER_ENDPOINT);
        if (endPoint == null) {
            endPoint = "localhost:80";
        }
        return new HttpCbsSyncClient(String.format("http://%s/", endPoint), tokenProvider);
    }

    @Lazy
    @Bean
    public static BisSyncClient getHttpBisSyncClient(@Value("${apiclient.bis.baseUrl}") String baseUrl, SettingService service) {
        String schoolId = service.get(SettingKeys.SCHOOL_ID);
        if (schoolId == null) {
            schoolId = "0L";
        }
        return new HttpBisSyncClient(baseUrl, Long.valueOf(schoolId));
    }

    @Lazy
    @Primary
    @Bean
    public static TokenProvider getTokenProvider(SettingService service, IdGenerator idGenerator) {
        return new TokenProviderImpl(service, idGenerator);
    }


    @Lazy
    @Bean
    public static JobBeanConfigurer jobBeanConfigurer(final Environment environment) {
        return new JobBeanConfigurer(environment);
    }

}
