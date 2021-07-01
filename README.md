# Mono Repo Kafka Gradle

## PreRequisites

Download kafka from site if you do not want to use docker.

## Creation of th monorepo

```code
gradle init
mkdir store && cd store
Use vscode Spring initializr to create Web and Kafka project
cd .. && mkdir warehouse && cd warehouse
Use vscode Spring initializr to create Web and Kafka project
cd .. && mkdir shared && cd shared
gradle init
```

Now we need to connect to modules together in order to gradle to know the dependencies between them.

In the roor settings.gradle

```code
rootProject.name = 'monorepo'
include "store"
include "warehouse"
include "shared"
```

In the build.gradle of the submodules inject the shared module where needed.

```code
dependencies {
    ...
    compile project(':shared')
    ...
```

## Start Zookeeper (on windows)

Update zookeeper.properties in config directory
Set dataDir to desired path (ex: dataDir=C:\kafka-2.4.1-src\data\zookeeper)
Open Windows Cmd

```code
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

## Start Kafka (on windows)

Update server.properties in config directory
Set log.dirs to desired path (ex: log.dirs=C:\kafka-2.4.1-src\logs\kafka-logs)
Open Windows Cmd

```code
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Create Topics

```code
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

If in cluster, connect to a node:

docker exec -it <node id>
/opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --topic <topic name> --partitions 3 --replication-factor 3

Example:
kafka@08c6de43966e:/$ /opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --topic replicated-topic --partitions 3 --replication-factor 3
Created topic replicated-topic.
```

## Test the kafka broker

```code
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test
```

## Read on kafka topic

```code
.\bin\windows\kafka-console-consumer.bat --bootrap-server localhost:9092 --topic test

```

## Run Zookeeper & Kafka using docker

```code
docker-compose up
```

## Run only the store service

```code
.\gradlew clean store:bootRun
```

## Run only the store service

```code
.\gradlew clean warehouse:bootRun
```

## Test store service rest API

```code
curl http://localhost:8088/articles
```

## Ressources

https://guides.gradle.org/creating-multi-project-builds/
Running Docker and understanding listeners
https://rmoff.net/2018/08/02/kafka-listeners-explained/
https://www.confluent.io/blog/kafka-listeners-explained/
https://dzone.com/articles/running-apache-kafka-on-windows-os
