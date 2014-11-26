fabric8-persistence-demo
======================

Working example of Hibernate using version from JBoss Fuse 6.1 with `transaction-type="RESOURCE_LOCAL"`


##### INSTALLATION STEPS
```
mvn clean install -DskipTests

# in fuse
features:addurl mvn:com.github.pires.example/feature-persistence/0.1-SNAPSHOT/xml/features

features:install persistence-aries-hibernate
```


##### EXTRA INFO

http://stackoverflow.com/questions/17487174/openjpa-transaction-management-is-not-available-fuse-esb