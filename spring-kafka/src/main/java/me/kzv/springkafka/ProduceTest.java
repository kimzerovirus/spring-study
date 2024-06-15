package me.kzv.springkafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProduceTest {
    private static final String BOOTSTRAP_SERVER = "localhost:10000";
    private static final String TOPIC_NAME = "topic1";

    public static void main(String[] args) throws Exception {
        var properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "100");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        String message = "Test Message";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);

        RecordMetadata metadata = producer.send(record).get();

        System.out.println("###");
        System.out.printf(">>> %s, %d, %d", message, metadata.partition(), metadata.offset());
        System.out.println("\n###");

        producer.flush();
        producer.close();
    }
}
