# SLF4J Demo

1) Dependency for the API of slf4j
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j.version}</version>
</dependency>
```
This allows us to use the API of slf4j, e.g. _Logger_ or _LoggerFactory_.

2) Some concrete logging library has to be added
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>${slf4j.version}</version>
    <scope>runtime</scope>
</dependency>
```
This is actually the worker doing the job.

3) Concrete logging library has to be accessible in the resulting jar. We can do so e.g. by using
[maven-assembly-plugin](https://maven.apache.org/plugins/maven-assembly-plugin/):
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>com.example.App</mainClass>
                    </manifest>
                </archive>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
        </execution>
    </executions>
</plugin>
```

Do not forget to run it through this jar with bundled dependencies:
```shell
java -jar target/slf4j-demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```
