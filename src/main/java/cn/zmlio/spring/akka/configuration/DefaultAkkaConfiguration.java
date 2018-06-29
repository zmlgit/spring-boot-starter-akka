package cn.zmlio.spring.akka.configuration;

import akka.actor.ActorSystem;
import cn.zmlio.spring.akka.AkkaProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.zmlio.spring.akka.SpringExtension.SpringExtProvider;

/**
 * @author zml
 */
@Configuration
@EnableConfigurationProperties(AkkaProperties.class)
public class DefaultAkkaConfiguration {

    protected final AkkaProperties akkaProperties;

    private final ApplicationContext applicationContext;

    public DefaultAkkaConfiguration(AkkaProperties akkaProperties, ApplicationContext applicationContext) {
        this.akkaProperties = akkaProperties;
        this.applicationContext = applicationContext;
    }

    @Bean(destroyMethod = "terminate")
    @ConditionalOnMissingBean(ActorSystem.class)
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create(akkaProperties.getSystemName(), akkaConfiguration());
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    /**
     * akka 具体配置
     *
     * @return akka config
     */
    public Config akkaConfiguration() {
        return ConfigFactory.parseMap(akkaProperties.getAkka()).withFallback(ConfigFactory.load());
    }
}
