# poc-webflux-reactive
POC API Webflux para estudo

# Stack
- Java
- Spring Webflux
- Kafka
- MongoDB

# Configuração kafka local
###### Start: Entrar no diretorio bin do kafka e executar o comando abaixo para iniciar o zookeper
./zookeeper-server-start.sh ../config/zookeeper.properties

Alterar propriedades do arquivo /config/server.properties adicionando as linhas abaixo ao listener do arquivo
listeners = PLAINTEXT://localhost:9092
auto.create.topics=false

###### Iniciar broker do kafka
./kafka-server-start.sh ../config/server.properties 

###### Criar tópico
./kafka-topics.sh --create --topic test-topic --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092

###### Instanciar console producer
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic

###### Instanciar console consumer
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning