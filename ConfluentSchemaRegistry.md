# Confluent Schema Registry

## PreRequisites

- Create your MSK cluster with its own VPC
- Create your EC2 instances or EKS cluster to connect to MSK by using the same VPC
- Update security groups of your EC2 instances or EKS clustr in order to open connections from port 8081

## Update schema registry properties file

Update property file with zookeeper connection strings

WARNING: Don't file the listeners field or you will have a connection issue

Example:

```property
kafkastore.connection.url=z-2.poc-kafka.ie9h9w.c4.kafka.eu-west-3.amazonaws.com:2181,z-1.poc-kafka.ie9h9w.c4.kafka.eu-west-3.amazonaws.com:2181,z-3.poc-kafka.ie9h9w.c4.kafka.eu-west-3.amazonaws.com:2181
```

## Ressources

https://docs.confluent.io/3.1.1/schema-registry/docs/intro.html
https://www.confluent.io/blog/avro-kafka-data/?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.nonbrand_tp.prs_tgt.kafka_mt.mbm_rgn.emea_lng.eng_dv.all&utm_term=%2Bkafka%20%2Bavro&creative=&device=c&placement=&gclid=Cj0KCQjwpfHzBRCiARIsAHHzyZr2i7PEFwKzPCEWnQcgXmjXApfAsKZq3wCj9u2x9jGaRSnlOMiSRGMaAg4_EALw_wcB
https://www.confluent.io/blog/schema-registry-avro-in-spring-boot-application-tutorial/
