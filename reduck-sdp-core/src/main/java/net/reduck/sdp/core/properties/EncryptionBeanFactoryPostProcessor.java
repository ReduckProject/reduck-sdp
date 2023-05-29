package net.reduck.sdp.core.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author Gin
 * @since 2023/5/22 11:07
 */
//@Configuration
@RequiredArgsConstructor
public class EncryptionBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
    private final ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        MutablePropertySources mutablePropertySources = environment.getPropertySources();

        String secretKey = environment.getProperty("configuration.crypto.secret-key");

        if (secretKey == null) {
            return;
        }

        for (PropertySource<?> propertySource : mutablePropertySources) {
            if (propertySource instanceof OriginTrackedMapPropertySource) {
                mutablePropertySources.replace(propertySource.getName(),
                        new PropertySourceWrapper<>(propertySource
                                , new AesEncryptor(PrivateKeyFinder.getSecretKey(secretKey))
                                , new EncryptionWrapperDetector("$ENC{", "}"))
                );
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 100;
    }
}
