# wordladder & docker

## 1. 定制容器
* 项目打包
```
mvn package
```
* Dockerfile
```dockerfile
FROM openjdk:8-jdk-alpine
EXPOSE 8086
COPY ./wordladder-0.0.1-SNAPSHOT.jar /home/wordladder.jar
COPY ./txtfile/dictionary.txt  src/txtfile/dictionary.txt
ENTRYPOINT [ "java", "-jar", "/home/wordladder.jar" ]
```
* 构建镜像
```docker
docker build -t spring-boot-docker
```
* 镜像实例化
```
docker run -d -p 8086:8086 spring-boot-docker
```

## 2. Docker Hub
* [My Dockershub](https://cloud.docker.com/repository/docker/xiaxiao/se-work/general
)
* repo
```
xiaxiao/se-work
```
* 上传镜像
```docker
docker tag spring-boot-docker xiaxiao/se-work:zxz-wordladder-1.0
docker push xiaxiao/se-work:zxz-wordladder-v1.0
```
* 拉取镜像并运行
```
docker pull xiaxiao/se-work:zxz-wordladder-1.0

docker run -d -p 8086:8086 xiaxiao/se-work:zxz-wordladder-1.0
```
