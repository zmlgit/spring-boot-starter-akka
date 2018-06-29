package cn.zmlio.spring.akka.configuration;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : zml
 * @version : 1.0
 * Description :
 * Created in: 2018/6/28 上午10:42
 * Modified By:
 * @since : 1.0
 */
@SpringBootTest(classes = DefaultAkkaConfiguration.class)
@RunWith(SpringRunner.class)
public class LocalAkkaConfigurationTest {
    @Autowired
    ActorSystem actorSystem;

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void akkaConfiguration() {
        Assert.assertEquals("ActorSystem", actorSystem.name());
        ActorRef actorRef = actorSystem.actorOf(Props.create(SomeActor.class));
        actorRef.tell("hello", ActorRef.noSender());
    }

    public static class SomeActor extends AbstractActor {
        ActorRef target = null;

        @Override
        public AbstractActor.Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("hello", message -> {
                        getSender().tell("world", getSelf());
                        if (target != null) target.forward(message, getContext());
                    })
                    .match(ActorRef.class, actorRef -> {
                        target = actorRef;
                        getSender().tell("done", getSelf());
                    })
                    .build();
        }
    }
}