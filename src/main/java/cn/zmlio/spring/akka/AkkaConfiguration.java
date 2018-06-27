package cn.zmlio.spring.akka;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.zmlio.spring.akka.SpringExtension.SpringExtProvider;

@Configuration
@ConfigurationProperties(prefix = "spring.akka.")
@EnableConfigurationProperties(AkkaProperties.class)
public class AkkaConfiguration {

    @Autowired
    private AkkaProperties akkaProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create(akkaProperties.getSystemName());
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}