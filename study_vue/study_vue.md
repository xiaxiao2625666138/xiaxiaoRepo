# <img src="img\vue.png" width="25px" height="25px" /> **Vue.js学习体会**
2018年9月29日&emsp;&emsp;周雪振(5141509091)
___

## **vue.js是什么**

> [Vue.js教程 from RUNOOB.COM](http://www.runoob.com/vue2/vue-tutorial.html)
> * Vue.js是一套构建用户界面的渐进式框架。
> * Vue只关注视图层，采用自底向上增量开的设计。
> * Vue的目的是通过尽可能简单的API实现响应的数据绑定和组合的shi'tu组件。 <br />

&emsp;&emsp;也就是说，vue.js会自动响应数据的变化情况，并且根据用户在代码中预先写好的绑定关系，对所有绑定在一起的数据和视图内容都进行修改。因此在别的地方可能也会看到有人粗略的称vue.js为声明式渲染的模版引擎。<br />
&emsp;&emsp;在具有响应式系统的Vue实例中，DOM状态只是数据状态的一个映射 即 UI=VM(State) ，当等式右边State改变了，页面展示部分UI就会发生相应改变。

<br />

## **Vue.js的简单使用方法**
### 可以使用CDN，在HTML文件中引入vue.js。也可以下载到本地，使用时用\<script>引用。
* BootCDN（国内）：[https://cdn.bootcss.com/vue/2.2.2/vue.min.js]https://cdn.bootcss.com/vue/2.2.2/vue.min.js
* unpkg：[https://unpkg.com/vue/dist/vue.js](https://unpkg.com/vue/dist/vue.js)
* cdnjs：[https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js](https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js)
```HTML
<head>
<script  src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
</head>
```
<br />

## **Vue.js模板语法**
> Vue.js 使用了基于 HTML 的模版语法，允许开发者声明式地将 DOM 绑定至底层 Vue 实例的数据。<br />
>Vue.js 的核心是一个允许你采用简洁的模板语法来声明式的将数据渲染进 DOM 的系统。<br />
>结合响应系统，在应用状态改变时， Vue 能够智能地计算出重新渲染组件的最小代价并应用到 DOM 操作上。
### **插值**
&emsp;&emsp;数据绑定最常见的形式就是使用<b>{{...}}</b>(双大括号)的文本插值。
```HTML
<body>
<div id="app">
  <p>{{message}}</p>   <!--这句和下面的pvue对象绑定-->
</div>

<script>
var pvue=new Vue({
    el: '#app',
    data:{
        messapge:'Hello Vue.js!' 
    }
})
</script>
</body>
```
&emsp;&emsp;可以使用v-html指令输出html代码
```HTML
<body>
<div id='app'>
  <div v-html="message"><div>
</div>

<script>
var dvue=new Vue({
    el: '#app',
    data:{
        message: '<h1>测试v-html</h1>'
    }
})
</script>
</body>
```
&emsp;&emsp;可以使用v-bind指令绑定HTML属性中的值
```HTML
</body>
  <div id="app">
  <label for="r1">修改颜色</label><input type="checkbox" v-model="class1" id="r1">
  <br><br>
  <div v-bind:class="{'class1': class1}">
    v-bind:class 指令
  </div>
</div>
    
<script>
new Vue({
    el: '#app',
  data:{
      class1: false
  }
});
</script>
</body>
```
&emsp;&emsp;Vue.js提供了完全的JavaScript表达式的支持
```HTML
<body>
<div id="app">
	{{5+5}}<br>  
	{{ ok ? 'YES' : 'NO' }}<br>
	{{ message.split('').reverse().join('') }}
	<div v-bind:id="'list-' + id">菜鸟教程</div>
</div>
	
<script>
new Vue({
  el: '#app',
  data: {
	ok: true,
    message: 'RUNOOB',
	id : 1
  }
})
</script>
</body>
```
### **Vue指令**
&emsp;&emsp;指令是带有v-前缀的特殊属性。指令用于在表达式改变值时，将某些行为应用到DOM上。例如v-if指令：
```HTML
<body>
<div id="app">
    <p v-if="seen">现在你看到我了</p>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    seen: true   //seen为true，插入<p>元素
  }
})
</script>
</body>
```
&emsp;&emsp;参数在指令以后以冒号指明。例如：
```HTML
<body>
<div id="app">  <!--href属性和url数据绑定-->
    <pre><a v-bind:href="url">菜鸟教程</a></pre>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    url: 'http://www.runoob.com'
  }
})
</script>
</body>
```
### **用户输入**
&emsp;&emsp;在input输入框中使用v-model指令来实现双向数据绑定
```HTML
<div id="app">
    <p>{{ message }}</p>
    <input v-model="message">
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'Runoob!'
  }
})
</script>
```
&emsp;&emsp;v-model 指令用来在 input、select、text、checkbox、radio 等表单控件元素上创建双向数据绑定，根据表单上的值，自动更新绑定的元素的值。<br />
&emsp;&emsp;按钮的事件我们可以使用 v-on 监听事件，并对用户的输入进行响应。
```HTML
<body>
<div id="app">
    <p>{{ message }}</p>
    <button v-on:click="reverseMessage">反转字符串</button>
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'Runoob!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
})
</script>
</body>
```
## **过滤器**
&emsp;&emsp;Vue.js允许自定义过滤器，用作一些常见的文本格式化。由"通道符"指示。
```HTML
<!--在两个大括号-->
{{message | capitalize}}
<!--在v-bind指令中-->
<div v-bind:id="rawId | formatId"></div>
```
&emsp;&emsp;过滤器函数接受表达式的值作为第一个参数。
```HTML
<body>
<div id="app">
  {{ message | capitalize }}
</div>
    
<script>
new Vue({
  el: '#app',
  data: {
    message: 'runoob'
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    }
  }
})
</script>
</body>
```
&emsp;&emsp;过滤器是 JavaScript 函数，因此可以接受参数：
```HTML
{{ message | filterA('arg1', arg2) }}
```
&emsp;&emsp;这里，message 是第一个参数，字符串 'arg1' 将传给过滤器作为第二个参数， arg2 表达式的值将被求值然后传给过滤器作为第三个参数。<br />
&emsp;&emsp;过滤器可以串联：
```
{{ message | filterA | filterB }}
```
### **缩写**
&emsp;&emsp;Vue.js为两个最为常用的指令提供了缩写：
```HTML
&emsp;&emsp;**v-bind缩写**
<!--完整语法-->
<a v-bind:href="url"></a>
<!--缩写-->
<a :href="url"></a>
```
&emsp;&emsp;**v-on缩写**
```HTML
<!--完整语法-->
<a v-on:click="doSomething"></a>
<!--缩写-->
<a @click="doSomething"></a>
```

<br />

 ## **NPM方法使用Vue.js**
 > **！此方法需要[安装npm并配置vue.js的编程环境](https://blog.csdn.net/mao834099514/article/details/79138484)(以vscode为例)。** 

### **目录**
 &emsp;&emsp;在npm中安装项目之后，使用vscode(也可以是其他IDE)打开该项目，结构如下：<br />
&emsp;&emsp;<img src="img\directory.PNG" />
<br />
### **目录解析**
><table border="1px">
>  <tr class="gray1">
>  <th>目录文件</th><th>说明</th>
>  </tr>
>  <tr class="gray2">
>  <td>config</td>
>  <td>配置目录，包括端口号等。初学者可以使用默认的</td>
>  </tr>
>  <tr class="gray2">
>  <td>mode_modules</td>
>  <td>npm 加载的项目依赖模块</td>
>  </tr>
>  <tr class="gray2">
>  <td>src</td>
>  <td>这里是开发者的开发目录，基本上要做的事情都在这个目录里。里面包含了几个目录及文件：
>    <ul>
>    <li>assets: 放置一些图片，如logo等。</li>
>    <li>components: 目录里面放了一个组件文件，可以不用。</li>
>    <li>App.vue: 项目入口文件，我们也可以直接将组件写这里，而不使用 * components 目录。</li>
>    <li>main.js: 项目的核心文件。</li>
>    </ul>
>  </td>
>  </tr>
>  </tr>
>  <tr class="gray2">
>  <td>static</td>
>  <td>静态资源目录，如图片、字体等。</td>
>  </tr>
>  </tr>
>  </tr>
>  <tr class="gray2">
>  <td>test</td>
>  <td>初始测试目录，可删除.</td>
>  </tr>
> </tr>
>  <tr class="gray2">
>  <td>.xxxx文件</td>
>  <td>这些是一些配置文件，包括语法配置，git配置等。</td>
>  </tr>
>  <tr class="gray2">
>  <td>package.json</td>
>  <td>项目配置文件.</td>
>  </tr>
>  <tr class="gray2">
>  <td>README.md</td>
>  <td>项目的说明文档，markdown 格式</td>
>  </tr>
> </table>
&emsp;&emsp;**可以从src文件夹里的文件着手vue项目的编写。**



  