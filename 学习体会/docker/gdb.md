# Docker入门


## Ubuntu docker 安装
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


## Hello World
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

## 运行容器
* 我们通过docker的两个参数 -i -t，让docker运行的容器实现"对话"的能力
```bash
runoob@runoob:~$ docker run -i -t ubuntu:15.10 /bin/bash
```
&emsp;&emsp;`-t`:在新容器内指定一个伪终端或终端。

&emsp;&emsp;`-i`:允许你对容器内的标准输入 (STDIN) 进行交互。

&emsp;&emsp;我们尝试在容器中运行命令 `cat /proc/version`和`ls`分别查看当前系统的版本信息和当前目录下的文件列表

&emsp;&emsp;我们可以通过运行`exit`命令或者使用`CTRL+D`来退出容器。

