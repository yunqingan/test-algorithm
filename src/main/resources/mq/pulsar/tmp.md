Pulsar是从头开始构建的，以支持多租户用例。Pulsar支持两个多租户特定的资源来启用多租户：<*properties* and *namespaces>*。在系统中，一个property代表一个租户。举个例子，假设部署了一个Pulsar集群来支持多个应用程序，在企业中每个property都可以代表一个团队，一个核心的功能，或者一个产品线，举几个例子，每个property依次可以包含多个namespace，例如，每个应用程序或用例有一个namespace。一个namespace可以包含多个Topic，如下图：

 ![Figure 2: Relationship between Pulsar concepts](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cubHVjaWRjaGFydC5jb20vcHVibGljU2VnbWVudHMvdmlldy8wN2NlMTNmZi0wMTE0LTQwOGYtOTRhYy03MDQxNDY5YTE0ZGMvaW1hZ2UucG5n?x-oss-process=image/format,png)   Pulsar关系图 

namespace是Pulsar的基本管理单元。在namespace级别，可以设置权限permission，备份fine-tune，跨集群管理消息数据的地理复制geo-replaction，控制消息到期message  expiry，和一些关键的操作。一个namespace中的所有Topic都继承相同的设置，为同时配置多个主题提供了强大的管理控制，根据namespace可见的级别，有以下两种不同类型：

- **Local：**local namespace仅对定义他的集群可见
- **Global：**多个集群可见，无论是在数据中心内，还是在地理上独立，这取决于是否在namespace的设置中跨集群启用复制。

虽然local namespace和global  namespace因范围的不同而不同，但是可以通过适当的设置在不同的团队或不同的组织之间共享它们。一旦提供了对名称空间的写入权限，应用程序就可以对名称空间中的任何主题进行写入。如果主题不存在，则在生产者首次写入主题时动态创建该主题（这一点跟Kafka一样）。

如前所述，每个namespace可以有一个或多个主题，每个主题可以有多个订阅，每个订阅都被设置为保留和接收关于主题发布的所有消息。为了给应用程序提供更大的灵活性，Pulsar支持三种不同类型的订阅，它们可以共存于同一个主题：

- **Exclusive subscription：**在任何给定时间都只能有一个消费者
- **Shared subscription：**多个消费者可以附加到同一个订阅，每个消费者将接收到消息的一部分
- **Failover subscription：**故障恢复订阅，允许多个消费者连接到一个主题，但在任何给定时间只有一个消费者将接收消息，其他消费者只有在当前接收消费者失败时才会开始接收消息。

下图显示了3种不同类型的订阅，订阅提供了一个主要的优势，因为它们解耦了消息的生成和使用方式。Pulsar支持不同类型的订阅，在不增加开发复杂性的情况下为应用程序提供了弹性

 ![Figure 3: Different types of Pulsar subscriptions](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cubHVjaWRjaGFydC5jb20vcHVibGljU2VnbWVudHMvdmlldy85YzFkNTY3ZC1mZWIyLTQ3OTctYWU3NC02ZjJmMzAyNjAxOTgvaW1hZ2UucG5n?x-oss-process=image/format,png)   Pulsar subscriptions 

### 数据分区

输入topic的数据可以从几兆字节到几TB不等，这意味着，在某些情况下，topic需要能够维持稳定的低吞吐量，而在其他情况下，则需要维持非常高的吞吐量，这取决于消费者的数量，那么，当一个topic需要高吞吐量而另一个topic需要低吞吐量时，会发生什么情况呢？为了解决这个问题，Pulsar能够在一个topic中分割数据并将其存储在多台机器上。这些数据碎片称为分区。

跨一组机器使用分区是跨多个节点处理大量数据的常用方法，同时还可以实现高吞吐量。默认情况下，Pulsar的Topic是作为非分区主题创建的，但是可以简使用简单的CLI命令或API调用创建分区主题并为它们分配特定数量的分区。

当创建分区topic时，Pulsar会自动对数据进行分区，并确保消费者和生产者可以不受分区限制。也就是说，当topic被分区时，之前使用单个分区主题编写的应用程序仍然可以执行，不需要改代码，使分区成为纯粹的管理级问题，而不是应用程序级问题。

分区由broker来处理，集群中每个节点都有自己的broker，下图展示了broker如何分区：

 ![Figure 4. Partitioning a topic to multiple Pulsar brokers](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cubHVjaWRjaGFydC5jb20vcHVibGljU2VnbWVudHMvdmlldy84NWVkZWY3NC0yMzg3LTQ1ZTItYWM5NS0yYzY3MGFmOTA0ZTQvaW1hZ2UucG5n?x-oss-process=image/format,png)   broker是如何分区的 

虽然应用程序可以在不更改代码的情况下利用分区，但是有一些额外的方法可以帮助您实现跨分区和跨可用消费者更好地分布数据。Pulsar允许选择路由策略，从而控制消息如何路由到特定的分区。有四个基本路由策略：

- **Single partitioning：**生产者选择一个随机分区并只将数据路由到该分区。这个模型保留了非分区主题提供的相同保证，但是当许多生产者发布到一个主题时，这个模型可能会很有用。
- **Round robin partitioning：**在本例中，生产者以循环方式在所有分区上均匀地分布数据。第一个消息进入第一个分区，第二个消息进入第二个分区，依此类推。
- **Hash partitioning：**在这种情况下，每个消息都带有一个键。分区的选择基于对消息键应用hash function生成的值。哈希分区将保证基于键的排序。
- **Custom partitioning：**在这里，生产者使用一个自定义函数来接收消息并生成一个分区号。然后将消息定向到该分区。自定义分区使应用程序能够完全控制分区逻辑。