package com.cheng.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.env.Environment;

/**
 * @author cheng_mboy
 */
public class JobBeanConfigurer implements BeanDefinitionRegistryPostProcessor {

    private final BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    private final Environment environment;

    public JobBeanConfigurer(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Class courClazz = Class.forName(this.environment.getProperty("app.job.courseware-sync.class-name"));
            this.registerBean(registry, "coursewareSyncJob", courClazz);

            Class baseClazz = Class.forName(this.environment.getProperty("app.job.bid-sync.class-name"));
            this.registerBean(registry, "baseDataSyncJob", baseClazz);
        } catch (ClassNotFoundException e) {
            throw new FatalBeanException(e.getMessage(), e);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


    private void registerBean(final BeanDefinitionRegistry registry, final String name, final Class<?> beanClass) {
        AnnotatedGenericBeanDefinition definition = new AnnotatedGenericBeanDefinition(beanClass);
        definition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(definition, registry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(definition);
        BeanDefinitionHolder holder = new BeanDefinitionHolder(definition, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }
}
