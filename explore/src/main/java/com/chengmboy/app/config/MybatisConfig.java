package com.chengmboy.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * @author cheng_mboy
 */
@Configuration
public class MybatisConfig {

    @Lazy
    @Bean(name = "sqlSessionFactory")
    public static SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource , @Value("${mybatis.show-sql}") Boolean show) throws Exception {
        if (show) {
            LogFactory.useStdOutLogging();
        }
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Lazy
    @Primary
    @Bean(name = "mapperScannerConfigurer")
    public static tk.mybatis.spring.mapper.MapperScannerConfigurer mapperScannerConfigurer() {
        tk.mybatis.spring.mapper.MapperScannerConfigurer mapperScannerConfigurer = new tk.mybatis.spring.mapper.MapperScannerConfigurer();
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.registerMapper(Mapper.class);
        mapperScannerConfigurer.setMapperHelper(mapperHelper);
        mapperScannerConfigurer.setBasePackage("com.chengmboy.app.mybatis");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

}