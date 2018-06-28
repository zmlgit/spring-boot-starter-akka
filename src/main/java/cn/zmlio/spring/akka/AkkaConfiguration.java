package cn.zmlio.spring.akka;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.zmlio.spring.akka.SpringExtension.SpringExtProvider;
import static com.typesafe.config.ConfigFactory.load;
import static com.typesafe.config.ConfigFactory.parseMap;

/**
 * @author zml
 */
@Configuration
@EnableConfigurationProperties(AkkaProperties.class)
public class AkkaConfiguration {

    private final AkkaProperties akkaProperties;

    private final ApplicationContext applicationContext;

    @Autowired
    public AkkaConfiguration(AkkaProperties akkaProperties, ApplicationContext applicationContext) {
        this.akkaProperties = akkaProperties;
        this.applicationContext = applicationContext;
    }

    @Bean(destroyMethod = "terminate")
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create(akkaProperties.getSystemName(), akkaConfiguration());
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return parseMap(akkaProperties.getConfig()).withFallback(load());
    }
}
