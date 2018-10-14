# <font color=red> Git零基础学习 </font>
2018年10月13日 &emsp;周雪振

---
![git img](img/git.png)

<br />

---
## 什么是Git
&emsp;&emsp;[Git菜鸟教程](http://www.runoob.com/git/git-tutorial.html)关于git的开言这样介绍：
> Git是一个开源的分布式版本控制系统，用于敏捷高效地处理任何或小或大的项目。<br/>
>Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。
>Git 与常用的版本控制工具 CVS, Subversion 等不同，它采用了分布式版本库的方式，不必服务器端软件支持。

&emsp;&emsp;学习Git的第一个疑问就是什么叫做**分布式版本控制系统**，网上有很多关于分布式版本控制系统的文章，他们的说法大多相似，可以参考，如果还不懂什么叫分布式版本控制系统，只有在亲身体验了Git和svn等不同的版本控制系统后，才会对这个概念产生比较直观的体验！<br />
&emsp;&emsp;[分布式和集中式版本控制的区别](https://blog.csdn.net/zhangqun23/article/details/52972261)<br />
&emsp;&emsp;[集中式版本控制与分布式版本控制的区别](https://www.jianshu.com/p/302932e65bbb)<br />
&emsp;&emsp;[何为集中式版本控制系统与分布式版本控制系统?](https://blog.csdn.net/gggg_ggg/article/details/49981617)<br />

---
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

---
## Git工作区、暂存区、版本库
> * **工作区:** 就是你在电脑里能够看到的目录。
> * **暂存区:** 英文叫做stage，或index。一般存放在".git/index"文件中。
> * **版本库:** 工作区有一个隐藏的目录.git，这个不算工作区，二是Git的版本库。

三者之间的关系为：
![工作区、暂存区、版本库](img/gitWIV.png)

---
## Git创建仓库
### **git init** <br />
&emsp;&emsp;Git使用<code>git init</code>命令来初始化一个Git仓库。执行此命令后，Git仓库会在根目录生成一个.git目录，该目录包含了资源的所有元数据，其他项目的目录保持不变。<br />
&emsp;&emsp;使用方法：<br />
&emsp;&emsp;想使用某个目录作为仓库，我们只需要在那个目录下使它初始化。
```
$ cd myrepo
$ git init
```
&emsp;&emsp;myrepo是一个目录，比如d:/myfile/myrepo。<br />
&emsp;&emsp;我们也可以制定目录作为Git仓库：
```
git init newrepo
```
&emsp;&emsp;初始化后，会在myrepo或newrepo目录下会出现一个名为 .git 的目录，所有 Git 需要的数据和资源都存放在这个目录中。<br />
### **git clone**
&emsp;&emsp;
我们可以使用<code>git clone</code>从现有的Git仓库中拷贝项目，格式为：
```
git clone <repo>
```
如果需要克隆到指定的目录，可以使用下面的命令格式：
```
git clone <repo> <directory>
```
&emsp;&emsp;repo:Git仓库。 <br />
&emsp;&emsp;directory:本地仓库。<br />

---
## 文件操作
### **跟踪和提交文件**
&emsp;&emsp;使用<code>git add</code>进行文件的跟踪，使用<code>git commit -m '描述信息'</code>进行文件的提交。<br />
&emsp;&emap;比如创建一个文件之后(test.cpp)，我们需要执行这样的命令跟踪文件：
```
git add test.cpp
```
&emsp;&emsp;执行这样的命令提交文件到版本库：
```
git commit -m 'first commit'
```
&emsp;&emsp;如果我们修改了已经跟踪的文件，我们需要重新执行add和commit指令，完成版本库里文件的更新。

---
