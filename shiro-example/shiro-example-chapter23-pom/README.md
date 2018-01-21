1.mvn clean & mvn install shiro-example-chapter23-core/pom.xml
2.modify dbUrl and password of shiro-example-chapter23-server/src/main/resources/resources.properties
3.exec shiro-example-chapter23-server/src/sql/shiro-schema.sql
4.mvn clean & mvn install shiro-example-chapter23-server/pom.xml
5.mvn clean & mvn install shiro-example-chapter23-client/pom.xml
6.mvn clean & mvn install shiro-example-chapter23-app1/pom.xml
7.mvn clean & mvn install shiro-example-chapter23-app2/pom.xml

8.右键 *chapter23-server->Run as->Run on Server
如果server运行不起来，则
(1)mvn clean *chapter23-core,mvn install *chapter23-core,
(2)mvn clean *chapter23-server,mvn install *chapter23-server
9.右键 *chapter23-app1->Maven Build..->jetty:run
10.右键 *chapter23-app2->Maven Build

访问
11.http://localhost:8080/chapter23-server/
12.http://localhost:9080/chapter23-app1/hello
13.http://localhost:10080/chapter23-app2/hello
