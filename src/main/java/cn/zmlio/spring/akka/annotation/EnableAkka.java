package cn.zmlio.spring.akka.annotation;

import cn.zmlio.spring.akka.configuration.DefaultAkkaConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : zml
 * @version : 1.0
 * Description :
 * Created in: 2018/6/28 上午10:24
 * Modified By:
 * @since : 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DefaultAkkaConfiguration.class)
public @interface EnableAkka {
}
