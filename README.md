## Development mode

TDD: Through the test-driven development mode to complete the development of this project.
Test case documentation：document/testcase/test case.xlsx

## Development ideas

#### 1. consider safety

* Use Checkmarx to check whether the package is safe
* In order to prevent the auto-increment ID from being detected, I encrypt it
* For security, it is necessary to encrypt the configuration file and add Nacos for dynamic configuration

#### 2. code quality

Check the quality of code with sonar
![image.png](document/images/sonar.png)

* Check the code specification with the alibaba java coding guidelines tool
* Writing unit tests
* Use Jacoco to check coverage
  ![img.png](document/images/coverage.png)
  There is no time to solve the mock problem of Specification yet, so the coverage rate has not reached 100%.
* Use httpClient tool or postman tool to check the interface

#### 3. The principle of development is to make the code as concise and efficient as possible

* Use unified exception code to throw errors and reduce the use of try and catch
* Unified log processing
* Use traceId to track log location problems

#### 4. Performance considerations

* I will establish the corresponding index when designing the database
* Add redis to cache data
* Use skyWalking and add sql monitoring
* Use Jmeter for stress test
* When problems arise, use Arthas to troubleshoot the problems

(There is no requirement for data and performance in this development, so I didn't add relevant processing.)

## demo url: http://3.0.89.164/

## Setup and deploy

* JDK：21
* tools：IntelliJ IDEA
* maven: 3.9.6
* CI/CD：Automated deployment through Jenkins
  
  * Install JDK21:
  
  ```
  wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz
  tar -xvf jdk-21_linux-x64_bin.tar.gz
  ```
  
  * Install MAVEN:
  
  ```
  wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
  tar -xvf apache-maven-3.9.6-bin.tar.gz
  mv /home/ec2-user/apache-maven-3.9.6 /opt/maven
  ```
  
  * Configure the environment:
    vim /etc/profile

```
export JAVA_HOME=/home/ec2-user/jdk-21.0.2
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

export MAVEN_HOME=/opt/maven
export PATH=$PATH:$MAVEN_HOME/bin
```

* Install Jenkins:

```
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
  sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
yum install jenkins
```

* Config Jenkins:
  config git、pom.xml、maven、jdk





