package net.reduck.sdp.starter;

import net.reduck.sdp.core.properties.EncryptionBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Reduck
 * @since 2023/5/30 0:13
 */
@Configuration
@Import(ReduckSdpProperties.class)
@ConditionalOnProperty(prefix = "reduck.sdp", value = "enabled")
public class ReduckSdpConfiguration {

    @Bean
    public EncryptionBeanFactoryPostProcessor encryptionBeanFactoryPostProcessor(ConfigurableEnvironment environment) {
        return new EncryptionBeanFactoryPostProcessor(environment);
    }
}
