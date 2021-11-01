package me.iyk.project.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RabbitListener(queues = {"iyk"})
@Component
public class RabbitMQConfig {

    @RabbitHandler
    public void receiver(String msg) {
        log.info("received msg: {}", msg);
    }
}
