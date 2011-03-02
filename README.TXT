Overview
--------

The cobertura-merge-plugin solves the problem of obtaining code test coverage information when the test code resides in a different module than the code under test

Usage:
------
in your (integration-)test module, specify

            <plugin>
                <groupId>org.oaky.mojo</groupId>
                <artifactId>cobertura-merge-plugin</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <configuration>
                    <includes>
                        <instrumentedModule>
                            <groupId>maven_integration_test_coverage</groupId>
                            <artifactId>app</artifactId>
                        </instrumentedModule>
                    </includes>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>copy-classes</goal>
                            <goal>add-sources</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

this will merge all listed modules instrumented classes into the your test module, enabling cobertura to properly collect coverage information


Example:
--------
To see the plugin in action, simply run

	mvn clean install cobertura:cobertura

and open ./sample/integration-tests/target/site/cobertura/index.html to see the collected coverage information.
