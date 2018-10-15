# <font color=red>Markdown</font>入门级简单操作

## 一、标题
```markdown
# 标题h1
## 标题h2
### 标题h3
#### 标题h4
##### 标题h5
###### 标题h6
```
&emsp;&emsp;效果是这样的:
># 标题h1
>## 标题h2
>### 标题h3
>#### 标题h4
>##### 标题h5
>###### 标题h6
&emsp;&emsp;标题还可以这样写：
```markdown
大标题
===

小标题
---
```
&emsp;&emsp;效果是这样的：
>大标题
>===
>
>小标题
>---

## 二、引用
&emsp;&emsp;其他部分展示效果时都用了引用（除了无序列表)。
```markdown
>引用
>>引用
>>>引用
>>>>引用
```
&emsp;&emsp;效果是这样的：
>引用
>>引用
>>>引用
>>>>引用

## 三、分割线
```markdown
***
___
---
```
&emsp;&emsp;效果是这样的：
>***
>___
>---

## 四、段落缩进和换行
```markdown
&emsp;&emsp;段前有两个空格(中文的)
&ensp;&ensp;段前有两个空格(英文的),这里&#8194;&#8194;也有两个空格<br/>
这里会换行
上面换行了
```
&emsp;&emsp;效果是这样的：
>&emsp;&emsp;段前有两个空格(中文的) <br/>
>&ensp;&ensp;段前有两个空格(英文的),这里&#8194;&#8194;也有两个空格<br/>
>这里会换行  
>上面换行了

&emsp;&emsp;上述换行操作是通过两个空格一个回车完成的，和直接使用\<br />效果是一样的。

## 五、强调
&emsp;&emsp;斜体是这样的：
```markdown
*这是斜体* <br />
_这是斜体_ <br />
```
&emsp;&emsp;效果是这样的：
> *这是斜体* <br />
> _这是斜体_ <br />

&emsp;&emsp;粗体是这样的：
```markdown
**这是粗体** <br />
__这是粗体__<br />
```
&emsp;&emsp;效果是这样的：
>**这是粗体字** <br />
>__这是粗体字__ <br />

## 六、注释
```markdown
<!--这是注释，不会显示出来-->
```
&emsp;&emsp;注释使用的是html文档的格式，注释不会显示出来<!--这是注释，不会显示出来-->

## 七、反斜杠的作用
```markdown
\\ <br />
\' <br />
\{ <br />
\# <br />
\* <br />
```
&emsp;&emsp;效果是这样的：
>\\ <br />
>\' <br />
>\{ <br />
>\# <br />
>\* <br />

## 八、列表
&emsp;&emsp;无序列表是这样的：
```markdown
* 这是无序列表
* 这是无序列表
* 这是无序列表
- 这是无序列表
- 这是无序列表
+ 这是无序列表
+ 这是无序列表

* 这是无序列表
  - 这是无序列表
    + 这是无序列表
    + 这是无序列表
  - 这是无序列表
* 这是无序列表
* 这是无序列表
```
&emsp;&emsp;效果是这样的：
* 这是无序列表
* 这是无序列表
* 这是无序列表
- 这是无序列表
- 这是无序列表
+ 这是无序列表
+ 这是无序列表

* 这是无序列表
  - 这是无序列表
    + 这是无序列表
    + 这是无序列表
  - 这是无序列表
* 这是无序列表
* 这是无序列表

&emsp;&emsp;有序列表是这样的：
```markdown
1. 这是有序列表
2. 这是有序列表
3. 这是有序列表
```
&emsp;&emsp;效果是这样的：
>1. 这是有序列表
>2. 这是有序列表
>3. 这是有序列表

## 九、代码块
&emsp;&emsp;在\```和\```之间写入代码块，此处不易展示，效果如下：
```cpp
int main(){
    return 0;
}
```
&emsp;&emsp;或者，缩进一个Tab或者四个空格，也可以达到同样的效果(除了不能指名代码语言类型)：
```markdown

    int main(){
        return 0;
    }
    
```
&emsp;&emsp;效果是这样的：
    
    int main(){
        return 0;
    }

&emsp;&emsp;行内代码可以这样写：
```markdown
这是行内代码<code>int i=1;</code>，字体会突出。
```
&emsp;&emsp;效果是这样的：
>这是行内代码<code>int i=1;</code>，字体会突出。

## 十、链接和图片
&emsp;&emsp;直接地址链接是这样的：
```markdown
<http://www.w3school.com.cn> <br /> <!-- br是html的换行 -->
<2625666138@qq.com>
```
&emsp;&emsp;效果是这样的：
> <http://www.w3school.com.cn> <br /> 
> <2625666138@qq.com>

&emsp;&emsp;引用链接是这样的：
```markdown
[w3school](www.w3school.com.cn)
```
&emsp;&emsp;效果是这样的：
>[w3school](www.w3school.com.cn)

&emsp;&emsp;索引链接是这样的（"w3school"是tatil可以不写)：
```markdown
[1]:www.w3school.com.cn "w3school"
[w3school][1]
```
&emsp;&emsp;\[1]放到文档的哪里都可以，效果是这样的：
>[1]:www.w3school.com.cn "w3school"
>[w3school][1] 

&emsp;&emsp;图片是这样的(路飞是tatil，可以不写)：
```markdown
![路飞](\img\lufei.jpg "路飞")
```
&emsp;&emsp;效果是这样的：
> ![路飞](\img\lufei.jpg "路飞")

&emsp;&emsp;图片也可以像链接的索引连接一样(效果不做展示):
```markdown
![路飞][lf]

[lf]:\img\lufei.jpg "路飞"
```
&emsp;&emsp;**markdown尚不能指定图片的高度和宽度等，如果需要的话可以使用html的<img>标签。**

## 十一、表格
&emsp;&emsp;表格可以使用html的\<table>标签：
```markdown
<table>
  <tr>
    <th>name</th>
    <th>age</th>
    <th>like</th>
    <th>major</th>
  </tr>
  <tr>
    <td>zhou</td>
    <td>22</td>
    <td>run</td>
    <td>software</td>
  </tr>
  <tr>
    <td>li</td>
    <td>21</td>
    <td>sing</td>
    <td>software</td>
  </tr>
</table>
```
&emsp;&emsp;效果是这样的：
><table>
>  <tr>
>    <th>name</th>
>    <th>age</th>
>    <th>like</th>
>    <th>major</th>
>  </tr>
>  <tr>
>    <td>zhou</td>
>    <td>22</td>
>    <td>run</td>
>    <td>software</td>
>  </tr>
>  <tr>
>    <td>li</td>
>    <td>21</td>
>    <td>sing</td>
>    <td>software</td>
>  </tr>
></table>

&emsp;&emsp;或者可以这样写，其中<code>-</code>是默认的左对齐，<code>:-</code>是左对齐，<code>:-:</code>是居中对齐，<code>-:</code>是右对齐。
```markdown
| name | age | like | major |
| - | :- | :-: | -: |
| zhou | 22 | run |software|
| li | 21 | singing | sofeware |
```
&emsp;&emsp;效果是这样的：
>| name | age | like | major |
>| - | :- | :-: | -: |
>| zhou | 22 | run |software|
>| li | 21 | singing | sofeware |

## 十二、字体
```markdown
<font face="黑体">我是黑体字</font> <br />
<font face="微软雅黑">我是微软雅黑</font> <br />
<font face="仿宋">我是仿宋体</font> <br />
<font color=#0099ff size=12 face="仿宋">仿宋</font> <br />
<font color=#00ffff size=18 face="STCAIYUN">华文彩云</font> <br />
<font color=gray size=8>gray</font> <br />
```
&emsp;&emsp;字体也是使用html文档的格式，有些编辑器是不支持的，比如github！vscode支持！效果是这样的：
><font face="黑体">我是黑体字</font> <br />
><font face="微软雅黑">我是微软雅黑</font> <br />
><font face="仿宋">我是仿宋体</font> <br />
><font color=#0099ff size=12 face="仿宋">仿宋></font> <br />
><font color=#00ffff size=18 face="STCAIYUN">华文彩云</font> <br />
><font color=gray size=8>gray</font> <br />

