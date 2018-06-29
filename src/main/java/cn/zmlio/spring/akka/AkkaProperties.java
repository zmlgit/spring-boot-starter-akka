package cn.zmlio.spring.akka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zml
 */
@ConfigurationProperties(prefix = "spring")
@Data
public class AkkaProperties {
    private String systemName;
    private Map<String, Object> akka = new TreeMap<>();
}
