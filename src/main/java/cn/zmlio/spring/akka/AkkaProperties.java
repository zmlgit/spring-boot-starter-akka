package cn.zmlio.spring.akka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.TreeMap;

@ConfigurationProperties(prefix = "spring.akka.")
@Data
public class AkkaProperties {
    private String systemName;
    private Map<String, Object> config = new TreeMap<>();
}
