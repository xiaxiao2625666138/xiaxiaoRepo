<h1 style="color:red"> SJTU Home page </h1>
&emsp;&emsp;周雪振 5141509091

---


## 一、与众多兄弟高校的Home页面相比
* 交大的页面排版和样式更漂亮
* 页面缩小后，交大的Home页自适应更好
* 都是利用\<div>划块，组件化开发
* 绝大多数学校的主页都用了JQuery
* 交大的首页层次结构上更为复杂

## 二、代码风格最为显著的特点是：模块化编程
### &emsp;1. 单独写样式表，并引入文件
```html
<link href="/resource/assets/css/ETUI/ETUI3.min.css" rel="stylesheet">
<link href="/resource/assets/css/ETUI/ETUI3.min.css" rel="stylesheet">
<!--等等>
```

### &emsp;2. 单独写js文件
```html
<link <script src="/resource/assets/plugin/stickUp/stickUp.js"></script>
<script src="/resource/assets/plugin/OwlCarousel/owl.carousel.min.js"></script>
<!--等等>
```

### &emsp;3. 整个页面用\<div>组件划块分层，组件化开发 
&emsp;&emsp;<img src="./img/div.png" style="width:450px"/>

### &emsp;4. 还使用了页面嵌入\<iframe>潜入主页的底部内容
```html
<iframe width="100%" height="330" class="phone-if1127" src="/dbcylj/index.html" border="0" frameborder="0" framespacing="0" marginwidth="0" marginheight="0" noresize="" scrolling="no" vspale="0">
  <!-- web page -->
</iframe>
```

## 三、 存在问题及解决办法
### &emsp;1. 首页顶部的隐藏的相关\<div>样式丑陋：
&emsp;&emsp;<img src="./img/arguly.png" style="width:400px"/> <br />
> &emsp;&emsp;原因是此处的样式表简陋<br />

> &emsp;&emsp;解决办法：修改样式表

### &emsp;2. 首页底部的微信微博的链接的title显示位置有可能会偏移：<br />
&emsp;&emsp;<img src="./img/titleError.png" style="width:250px"/> <br />
> 原因是两个块无缝排列：<br />

&emsp;&emsp;<img src="./img/titleErrorS.png" style="width:250px"/> <br />
> 解决办法：加大两个块的距离，不改变总体大小和排列效果
```css
/* 原样式 */
.footer .share a{
    padding: 0 30px;
}
/* 修改为 */
.footer .share a{
    margin: 0 30px;
}
```
<br />

### &emsp;3. 仍然有成大段的脚本代码写在主页里：
&emsp;&emsp;<img src="./img/script.png" style="width:400px"/> <br />
> 解决办法：把这些脚本代码写入单独的文件中，并引入。



