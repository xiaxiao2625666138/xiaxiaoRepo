# Docker入门


## 一、 Ubuntu docker 安装
* install
```bash
 runoob@runoob:~$ wget -qO- https://get.docker.com/ | sh
```

* start
```bash
runoob@runoob:~$ sudo service docker start
```
&emsp;&emsp;或者
```bash
runoob@runoob:~$ systemctl start docker
```

* 测试运行hello-world
```bash
runoob@runoob:~$ docker run hello-world
```

* 镜像加速
```

```
* stop
```
runoob@runoob:~$ systemctl stop docker
```


## 二、 Hello World
&emsp;&emsp;Docker 允许你在容器内运行应用程序， 使用 `docker run` 命令来在容器内运行一个应用程序。
输出 Hello world
```bash
runoob@runoob:~$ docker run ubuntu:15.10 /bin/echo "Hello world"
```
&emsp;&emsp;参数解析

* `docker`: Docker 的二进制执行文件。
* `run`:与前面的 docker 组合来运行一个容器。
* `ubuntu:15.10`指定要运行的镜像，Docker首先从本地主机上查找镜像是否存在，如果不存在，Docker 就会从镜像仓库 Docker Hub 下载公共镜像。
* `/bin/echo "Hello world"`: 在启动的容器里执行的命令

&emsp;&emsp;以上命令完整的意思可以解释为：Docker 以 `ubuntu15.10` 镜像创建一个新容器，然后在容器里执行 `bin/echo "Hello world"`，然后输出结果。

## 三、 运行容器
* 运行交互式容器

&emsp;&emsp;我们通过docker的两个参数 -i -t，让docker运行的容器实现"对话"的能力
```bash
runoob@runoob:~$ docker run -i -t ubuntu:15.10 /bin/bash
```
![](img/it.PNG)

&emsp;&emsp;`-t`:在新容器内指定一个伪终端或终端。

&emsp;&emsp;`-i`:允许你对容器内的标准输入 (STDIN) 进行交互。

&emsp;&emsp;我们尝试在容器中运行命令 `cat /proc/version`和`ls`分别查看当前系统的版本信息和当前目录下的文件列表

&emsp;&emsp;我们可以通过运行`exit`命令或者使用`CTRL+D`来退出容器。

* 启动容器（后台模式）
```bash
runoob@runoob:~$ docker run -d ubuntu:15.10 /bin/sh -c "while true; do echo hello world; sleep 1; done"
```
&emsp;&emsp;此时返回的是一个长字符串
![](img/brun.PNG)
&emsp;&emsp;这个长字符串叫做容器ID，对每个容器来说都是唯一的，我们可以通过容器ID来查看对应的容器发生了什么。

&emsp;&emsp;首先，我们需要确认容器有在运行，可以通过 `docker ps` 来查看
```
runoob@runoob:~$ docker ps
```
![](img/ps.PNG)

* `CONTAINER ID`:容器ID
* `NAMES`:自动分配的容器名称

&emsp;&emsp;使用`docker logs`命令，查看容器内的标准输出
```bash
runoob@runoob:~$ docker logs ffbf10fbeb94
runoob@runoob:~$ docker logs nifty_kalam
```
![](img/logsid.PNG)

![](img/logsname.PNG)

* 停止容器

&emsp;&emsp;我们使用 `docker stop `命令来停止容器: 
```
runoob@runoob:~$ docker stop ffbf10fbeb94
```
&emsp;&emsp;或者
```bash
runoob@runoob:~$ docker stop nifty_kalam
```

## 四、 Docker 容器使用

### 1.Docker 客户端

&emsp;&emsp;docker 客户端非常简单 ,我们可以直接输入 docker 命令来查看到 Docker 客户端的所有命令选项。
```
runoob@runoob:~# docker
```
![](img/docker.PNG)
&emsp;&emsp;可以通过命令 `docker command --help` 更深入的了解指定的 Docker 命令使用方法。

![](img/help.PNG)

### 2.运行一个web应用

&emsp;&emsp;在docker容器中运行一个 Python Flask 应用来运行一个web应用。
```bash
runoob@runoob:~$ docker pull training/webapp  # 载入镜像
runoob@runoob:~$ docker run -d -P training/webapp python app.py # 运行容器
runoob@runoob:~$  docker ps #查看运行中的容器
```
* -d:让容器在后台运行。
* -P:将容器内部使用的网络端口映射到我们使用的主机上。

![](img/web.PNG)

&emsp;&emsp;这里多了端口信息
```
PORTS
0.0.0.0:32768->5000/tcp
```
&emsp;&emsp;Docker 开放了 5000 端口（默认 Python Flask 端口）映射到主机端口 32768 上。

&emsp;&emsp;这时我们可以通过浏览器访问WEB应用

![](img/weblook.PNG)

&emsp;&emsp;我们也可以通过 -p 参数来设置不一样的端口：

```bash
 runoob@runoob:~$ docker run -d -p 5000:5000 training/webapp python app.py
 ```

 ![](img/webport.PNG)

 &emsp;&emsp;容器内部的 5000 端口映射到我们本地主机的 5000 端口上。

### 3. 网络端口的快捷方式

&emsp;&emsp;通过 `docker ps` 命令可以查看到容器的端口映射，docker 还提供了另一个快捷方式 `docker port`，使用 `docker port` 可以查看指定 （ID 或者名字）容器的某个确定端口映射到宿主机的端口号。
```bash
runoob@runoob:~$ docker port 77f8d00f38b6
# 或者
runoob@runoob:~$ docker port elegant_heisenberg
```
### 4. 查看 WEB 应用程序日志
&emsp;&emsp;`docker logs` [ID或者名字] 可以查看容器内部的标准输出。
```
runoob@runoob:~$ docker logs -f elegant_heisenberg
```
* `-f`: 让 `docker logs` 像使用 tail -f 一样来输出容器内部的标准输出。

![](img/weblogs.PNG)

### 5. 查看WEB应用程序容器的进程
&emsp;&emsp;我们还可以使用 `docker top` 来查看容器内部运行的进程
```
runoob@runoob:~$ docker top elegant_heisenberg
```
![](img/top.PNG)

### 6. 检查 WEB 应用程序配置
&emsp;&emsp;使用 `docker inspect` 来查看 Docker 的底层信息。它会返回一个 JSON 文件记录着 Docker 容器的配置和状态信息。
```
runoob@runoob:~$ docker inspect elegant_heisenberg
```
![](img/inspect.PNG)

### 7. web容器其他操作
```bash
# 停止 WEB 应用容器
runoob@runoob:~$ docker stop elegant_heisenberg

#重启WEB应用容器
runoob@runoob:~$ docker start elegant_heisenberg

#移除WEB应用容器，先停止才能删除
runoob@runoob:~$ docker rm elegant_heisenberg
```

![](img/webother.PNG)

## 五、docker 镜像使用
&emsp;&emsp;当运行容器时，使用的镜像如果在本地中不存在，docker 就会自动从 docker 镜像仓库中下载，默认是从 Docker Hub 公共镜像源下载。

### 1. 列出镜像列表
```
runoob@runoob:~$ docker images 
```
![](img/images.PNG)

* `REPOSITORY`：表示镜像的仓库源
* `TAG`：镜像的标签
* `IMAGE ID`：镜像ID
* `CREATED`：镜像创建时间
* `SIZE`：镜像大小

&emsp;&emsp;同一仓库源可以有多个 `TAG`，代表这个仓库源的不同个版本，如`ubuntu`仓库源里，有`15.10`、`14.04`等多个不同的版本，我们使用 `REPOSITORY:TAG` 来定义不同的镜像。

&emsp;&emsp;所以，我们如果要使用版本为15.10的ubuntu系统镜像来运行容器时，命令如下：
```bash
runoob@runoob:~$ docker run -t -i ubuntu:15.10 /bin/bash 
```
&emsp;&emsp;如果你不指定一个镜像的版本标签，例如你只使用 `ubuntu`，docker 将默认使用 `ubuntu:latest` 镜像。

### 2. 获取一个新的镜像

&emsp;&emsp;当我们在本地主机上使用一个不存在的镜像时 Docker 就会自动下载这个镜像。如果我们想预先下载这个镜像，我们可以使用 `docker pull` 命令来下载它。
```
Crunoob@runoob:~$ docker pull ubuntu:13.10
```
&emsp;&emsp;下载完成后，我们可以直接使用这个镜像来运行容器。

### 3. 查找镜像

&emsp;&emsp;我们可以从 Docker Hub 网站来搜索镜像，Docker Hub 网址为： [https://hub.docker.com/](https://hub.docker.com/ )

&emsp;&emsp;我们也可以使用 `docker search` 命令来搜索镜像。比如我们需要一个httpd的镜像来作为我们的web服务。我们可以通过 docker search 命令搜索 httpd 来寻找适合我们的镜像。

![](img/search.PNG)

* `NAME`:镜像仓库源的名称
* `DESCRIPTION`:镜像的描述
* `OFFICIAL`:是否docker官方发布

### 4. 构建镜像
