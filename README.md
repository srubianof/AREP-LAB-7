# Safe Aplication with Certificates
The project is composed by different Docker containters, those containers have different the web clients there requests are made and haddled. The project is a small implementation of the following architecture

![](https://cdn.discordapp.com/attachments/748398289514397717/762435252957610024/Screen_Shot_2020-10-04_at_5.01.01_PM.png)

* To check a demo of the implementation, follow the next [Link](https://www.youtube.com/watch?v=Y73ZDZ7e1bs)

![hola](https://cdn.discordapp.com/attachments/748398289514397717/762454817699790849/giphy.gif)

## Badges
Continous Integration for Web Server [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/srubianof/arep-webserver)

Continous Integration for Load Balancer [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/srubianof/arep-loadbalancer)
## Getting Started
The following steps are required in order to get a copy of the project, and be able to run it and perform tests.
### Prerequisites
You need to have installed the next software to successfully run the project:

* Java recommended Java 8
* Maven
* Git
* Docker
### Installing & execution
This a step by step guide that will tell you how to get a copy of the project and how to execute
        
First get a copy of the repository
```
git clone https://github.com/srubianof/AREP-LAB-7.git
```
Once the project is downloaded in your machine, 

## Program execution
For a proper execution of the project, please perfom it in a UNIX environment where you use the provided shell program to a controlled execution:

```
sh pruebita.sh
```

![](https://cdn.discordapp.com/attachments/748398289514397717/762455756791349278/carbon-2.png)

## Built With
* Java 8
* Docker
* MongoDB
* Git - Version-control system
* [Maven](https://maven.apache.org) - Dependency Management
## Javadoc

The complete documentation of the project is divided by components, the documentation for the Load Balancer can be found [here](https://github.com/srubianof/AREP-LAB-5/tree/master/Docs/LoadBalancer) and the documentation for the Web Server [here](https://github.com/srubianof/AREP-LAB-5/tree/master/Docs/WebServer)


In order to generate the documentation type the following commands in the terminal:
* This generates a html with the javadoc
```
mvn javadoc:javadoc
```

* This generates a site for the project where the project's reports are included.
```
mvn site
```
* This opens the site where the documentation is located
```
mvn site:run
```

## Author

[**Santiago Rubiano Fierro**](https://github.com/srubianof) Software Engineering Student

## License

 This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/srubianof/AREP-LAB-1/blob/master/LICENSE) file for details.
