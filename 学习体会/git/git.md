# <font color=red> Git零基础学习 </font>
2018年10月13日 &emsp;周雪振

---
![git img](img/git.png)

<br />

## 什么是Git
&emsp;&emsp;[Git菜鸟教程](http://www.runoob.com/git/git-tutorial.html)关于git的开言这样介绍：
> Git是一个开源的分布式版本控制系统，用于敏捷高效地处理任何或小或大的项目。<br/>
>Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。
>Git 与常用的版本控制工具 CVS, Subversion 等不同，它采用了分布式版本库的方式，不必服务器端软件支持。

&emsp;&emsp;学习Git的第一个疑问就是什么叫做**分布式版本控制系统**，网上有很多关于分布式版本控制系统的文章，他们的说法大多相似，可以参考，如果还不懂什么叫分布式版本控制系统，只有在亲身体验了Git和svn等不同的版本控制系统后，才会对这个概念产生比较直观的体验！<br />
&emsp;&emsp;[分布式和集中式版本控制的区别](https://blog.csdn.net/zhangqun23/article/details/52972261)<br />
&emsp;&emsp;[集中式版本控制与分布式版本控制的区别](https://www.jianshu.com/p/302932e65bbb)<br />
&emsp;&emsp;[何为集中式版本控制系统与分布式版本控制系统?](https://blog.csdn.net/gggg_ggg/article/details/49981617)<br />
&emsp;&emsp;
## <font color=gray> Git配置
&emsp;&emsp;**！！！这部分可以说跟一般使用者没什么关系，可以完全忽略不去了解！！！**<br /><br />
&emsp;&emsp;Git提供了叫做git config的工具，专门用来配置或读取相应的环境变量，而这些变量，据顶了Git在各个环节的具体工作方式和行为。这些变量可以存储在三个不同的地方：
* /etc/gitconfig文件：系统对所有用户都普遍使用的配置。执行<code>git congig --system</code>读写的就是这个文件。
* ~/.gitconfig文件：用户目录下的配置文件只适用于该用户。执行<code>git config --global</code>读写的就是这个文件。
* 当前项目的Git目录中的配置文件(也就是工作目录中的.git/config文件):这里的配置仅仅针对当前项目有效。<br />

&emsp;&emsp;**每一个级别的配置都会覆盖上层的相同配置，所以 .git/config 里的配置会覆盖 /etc/gitconfig 中的同名变量。**<br />
&emsp;&emsp;**如果使用了--global选项，那么更改的配置文件就是位于你用户主目录下的那个，以后你所有的项目都会默认使用这里配置的用户信息。**<br />
&emsp;&emsp;如果在某个特定的项目中使用其他名字或邮件，只要去掉--global选项重新配置即可，新的设定保存在当前项目的.git/config文件中。<br />
&emsp;&emsp;例如：
* 查看和修改用户名和用户邮箱：
```
$ git config --global user.name               //查看用户名
$ git config --global user.name xiaxiao       //修改用户名
$ git config --global user.email              //查看用户邮箱
$ git config --global user.email xxx@qq.com   //修改用户邮箱
```
* 修改Git的文本编辑器，一般默认为Vim，可以使用下面的命令修改Git的编辑器：
```
$ git config --global core.editor vim
```
* 设置解决合并冲突时使用的差异分析工具，比如设置为vimdiff
```
$ git config --global merge.tool vimdiff
```
* 查看已有的配置信息，有时会看到重复的变量名，说明他们是来自不同的配置文件。
```
$ git config --list
```
* 也可以直接查看某个环境变量的设定：
```
$ git config user.name
```


</font>