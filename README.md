# Apache Kafka Basics Project

This project provides a basic overview of Apache Kafka, explaining how it works, and demonstrates how to use it to post and consume messages.

## Installation

To get started with the Apache Kafka Basics project, follow these steps:

1. **Download Kafka:** First, download the Kafka_2.13-3.5.0 project from [the official Kafka website](https://kafka.apache.org/downloads).

2. **Extract the Archive:** Once the download is complete, extract the downloaded archive to a location of your choice.

3. **Navigate to Kafka Directory:** Open your terminal and navigate to the `kafka_2.13-3.5.0/` directory using the following command:
   ```
   cd kafka_2.13-3.5.0/
   ```

4. **Check Java Installation:** Before running Kafka, make sure you have Java installed on your system, as Kafka requires Java to run.

## Usage

Now that you have Kafka installed, follow these steps to use the Apache Kafka Basics project:

1. **Start Zookeeper:** Kafka relies on Zookeeper for coordination. Start the Zookeeper server using the provided configuration file:
   ```
   bin/zookeeper-server-start.sh config/zookeeper.properties
   ```

2. **Start Kafka Broker:** Launch the Kafka broker (server) using the provided configuration file:
   ```
   bin/kafka-server-start.sh config/server.properties
   ```

3. **Produce Messages:** To post messages to a topic named "saurabh," use the following command in the terminal:
   ```
   bin/kafka-console-producer.sh --topic saurabh --bootstrap-server localhost:9092
   ```

4. **Consume Messages:** To consume messages from the "saurabh" topic from the beginning, use the following command:
   ```
   bin/kafka-console-consumer.sh --topic saurabh --from-beginning --bootstrap-server localhost:9092
   ```

Now you can interact with Apache Kafka and see how messages are published and consumed.

## Contributing

If you wish to contribute to this project, feel free to fork the repository and submit a pull request. We welcome any improvements, bug fixes, or additional features.

## License

This project is licensed under the [MIT License](LICENSE), which allows you to use, modify, and distribute the code freely. See the LICENSE file for more details.
