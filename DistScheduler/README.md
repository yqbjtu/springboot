#JSONDemo

http://127.0.0.1:9093/swagger-ui.html

FYI：  
http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-11.html  


Clustering currently works with the JDBC-Jobstore (JobStoreTX or JobStoreCMT) and the TerracottaJobStore. Features include load-balancing and job fail-over (if the JobDetail’s “request recovery” flag is set to true).

####Clustering With JobStoreTX or JobStoreCMT Enable clustering by setting the “org.quartz.jobStore.isClustered” property to “true”. Each instance in the cluster should use the same copy of the quartz.properties file. Exceptions of this would be to use properties files that are identical, with the following allowable exceptions: Different thread pool size, and different value for the “org.quartz.scheduler.instanceId” property. Each node in the cluster MUST have a unique instanceId, which is easily done (without needing different properties files) by placing “AUTO” as the value of this property.

    Never run clustering on separate machines, unless their clocks are synchronized using some form of time-sync service (daemon) that runs very regularly (the clocks must be within a second of each other). See http://www.boulder.nist.gov/timefreq/service/its.htm if you are unfamiliar with how to do this. 

    Never fire-up a non-clustered instance against the same set of tables that any other instance is running against. You may get serious data corruption, and will definitely experience erratic behavior. 

Only one node will fire the job for each firing. What I mean by that is, if the job has a repeating trigger that tells it to fire every 10 seconds, then at 12:00:00 exactly one node will run the job, and at 12:00:10 exactly one node will run the job, etc. It won’t necessarily be the same node each time - it will more or less be random which node runs it. The load balancing mechanism is near-random for busy schedulers (lots of triggers) but favors the same node that just was just active for non-busy (e.g. one or two triggers) schedulers.

####Clustering With TerracottaJobStore Simply configure the scheduler to use TerracottaJobStore (covered in Lesson 9: JobStores), and your scheduler will be all set for clustering.

You may also want to consider implications of how you setup your Terracotta server, particularly configuration options that turn on features such as persistence, and running an array of Terracotta servers for HA.

The Enterprise Edition of TerracottaJobStore provides advanced Quartz Where features, that allow for intelligent targeting of jobs to appropriate cluster nodes.

More information about this JobStore and Terracotta can be found at http://www.terracotta.org/quartz
JTA Transactions

As explained in Lesson 9: JobStores, JobStoreCMT allows Quartz scheduling operations to be performed within larger JTA transactions.

Jobs can also execute within a JTA transaction (UserTransaction) by setting the “org.quartz.scheduler.wrapJobExecutionInUserTransaction” property to “true”. With this option set, a a JTA transaction will begin() just before the Job’s execute method is called, and commit() just after the call to execute terminates. This applies to all jobs.

If you would like to indicate per job whether a JTA transaction should wrap its execution, then you should use the @ExecuteInJTATransaction annotation on the job class.

Aside from Quartz automatically wrapping Job executions in JTA transactions, calls you make on the Scheduler interface also participate in transactions when using JobStoreCMT. Just make sure you’ve started a transaction before calling a method on the scheduler. You can do this either directly, through the use of UserTransaction, or by putting your code that uses the scheduler within a SessionBean that uses container managed transactions.



计划使用的属性
#druid datasouce database settings begin
spring.datasource.druid.db-type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.druid.driverClassName=com.mysql.jdbc.Driver
spring.datasource.druid.url=jdbc:mysql://127.0.0.1:3306/myscheduler?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false
spring.datasource.druid.username=user1
spring.datasource.druid.password=password
# 下面为连接池的补充设置，应用到上面所有数据源中
# 初始化大小，最小，最大
spring.datasource.druid.initial-size=5
spring.datasource.druid.min-idle=5
spring.datasource.druid.max-active=20
# 配置获取连接等待超时的时间
spring.datasource.druid.max-wait=60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.druid.time-between-eviction-runs-millis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.druid.min-evictable-idle-time-millis=300000
spring.datasource.druid.validation-query=SELECT 1 FROM DUAL
spring.datasource.druid.test-while-idle=true
spring.datasource.druid.test-on-borrow=false
spring.datasource.druid.test-on-return=false
# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.druid.pool-prepared-statements=true
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.druid.filter.commons-log.connection-logger-name=stat,wall,log4j
spring.datasource.druid.filter.stat.log-slow-sql=true
spring.datasource.druid.filter.stat.slow-sql-millis=2000
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.druid.connect-properties.=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 合并多个DruidDataSource的监控数据
spring.datasource.druid.use-global-data-source-stat=true