package cn.zmlio.spring.akka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.akka.")
@Data
public class AkkaProperties {
    private String systemName;
}
