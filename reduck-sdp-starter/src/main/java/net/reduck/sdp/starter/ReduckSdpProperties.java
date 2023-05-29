package net.reduck.sdp.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gin
 * @since 2023/5/23 18:55
 */
@Configuration
@ConfigurationProperties(prefix = "reduck.sdp")
@Getter
@Setter
@ConditionalOnProperty(prefix = "reduck.sdp", value = "enabled")
class ReduckSdpProperties {

    public String secretKey;

    private boolean enabled;

}
