# <img src="img\vue.png" width="25px" height="25px" /> **Vue.js学习体会**
2018年9月29日&emsp;&emsp;周雪振(5141509091)
___

## Vue是什么
>&emsp;&emsp;Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。
 ---

## 入门Vue
### &emsp;&emsp;1.Vue.js最简单的使用方法
```js
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
```
### &emsp;&emsp;2.声明式渲染, 数据和 DOM 被建立了关联，所有东西都是响应式的
```
<div id="app">
  {{ message }}
</div>

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})
```
### &emsp;&emsp;3.`v-bind`绑定元素的的属性
```
<div id="app-2">
  <span v-bind:title="message">
    鼠标悬停几秒钟查看此处动态绑定的提示信息！
  </span>
</div>

var app2 = new Vue({
  el: '#app-2',
  data: {
    message: '页面加载于 ' + new Date().toLocaleString()
  }
})
```

### &emsp;&emsp;4.`v-if`条件控制
```
<div id="app-3">
  <p v-if="seen">现在你看到我了</p>
</div>

var app3 = new Vue({
  el: '#app-3',
  data: {
    seen: true
  }
})
```

### &emsp;&emsp;5.`v-for`循环控制
```
<div id="app-4">
  <ol>
    <li v-for="todo in todos">
      {{ todo.text }}
    </li>
  </ol>
</div>

var app4 = new Vue({
  el: '#app-4',
  data: {
    todos: [
      { text: '学习 JavaScript' },
      { text: '学习 Vue' },
      { text: '整个牛项目' }
    ]
  }
})
```

### &emsp;&emsp;6.`v-on`事件监听
```
<div id="app-5">
  <p>{{ message }}</p>
  <button v-on:click="reverseMessage">逆转消息</button>
</div>

var app5 = new Vue({
  el: '#app-5',
  data: {
    message: 'Hello Vue.js!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
})
```
&emsp;&emsp;**注意在 reverseMessage 方法中，我们更新了应用的状态，但没有触碰 DOM——所有的 DOM 操作都由 Vue 来处理，你编写的代码只需要关注逻辑层面即可。**

### 7.`v-model`双向绑定
```
<div id="app-6">
  <p>{{ message }}</p>
  <input v-model="message">
</div>

var app6 = new Vue({
  el: '#app-6',
  data: {
    message: 'Hello Vue!'
  }
})
```

### &emsp;&emsp;8.Vue.component组件化应用构建
>&emsp;&emsp;组件系统是 Vue 的另一个重要概念，因为它是一种抽象，允许我们使用小型、独立和通常可复用的组件构建大型应用。仔细想想，几乎任意类型的应用界面都可以抽象为一个组件树：  

![component tree](img/component_tree.png)
&emsp;&emsp;在 Vue 里，一个组件本质上是一个拥有预定义选项的一个 Vue 实例。在 Vue 中注册组件很简单：
```
Vue.component('todo-item', {
  template: '<li>这是个待办项</li>'
})

<ol>
  <!-- 创建一个 todo-item 组件的实例 -->
  <todo-item></todo-item>
</ol>
```
&emsp;&emsp;但是这样会为每个待办项渲染同样的文本，这看起来并不炫酷。我们应该能从父作用域将数据传到子组件才对。让我们来修改一下组件的定义，使之能够接受一个 prop：
```
<div id="app-7">
  <ol>
    <todo-item
      v-for="item in groceryList"
      v-bind:todo="item"
      v-bind:key="item.id">
    </todo-item>
  </ol>
</div>

Vue.component('todo-item', {
  props: ['todo'],
  template: '<li>{{ todo.text }}</li>'
})

var app7 = new Vue({
  el: '#app-7',
  data: {
    groceryList: [
      { id: 0, text: '蔬菜' },
      { id: 1, text: '奶酪' },
      { id: 2, text: '随便其它什么人吃的东西' }
    ]
  }
})
```

## Vue模板语法
> Vue.js 使用了基于 HTML 的模板语法，允许开发者声明式地将 DOM 绑定至底层 Vue 实例的数据。所有 Vue.js 的模板都是合法的 HTML ，所以能被遵循规范的浏览器和 HTML 解析器解析。

&emsp;&emsp;在底层的实现上，Vue 将模板编译成虚拟 DOM 渲染函数。结合响应系统，Vue 能够智能地计算出最少需要重新渲染多少组件，并把 DOM 操作次数减到最少。

### &emsp;1.文本
&emsp;&emsp;数据绑定最常见的形式就是使用**Mustache**语法 (双大括号) 的文本插值：
```HTML
<span>Message: {{msg}}</span>
```
&emsp;&emsp;Mustache 标签将会被替代为对应数据对象上 msg 属性的值。无论何时，绑定的数据对象上 msg 属性发生了改变，插值处的内容都会更新。
> 通过使用 v-once 指令，你也能执行一次性地插值，当数据改变时，插值处的内容不会更新。但请留心这会影响到该节点上的其它数据绑定：
```HTML
<span v-once>这个将不会改变：{{mag}}</span>
```

### &emsp;2.原始HTML

&emsp;&emsp;双大括号会将数据解释为普通文本，而非 HTML 代码。为了输出真正的 HTML，你需要使用 v-html 指令：
```
<p>Using mustaches: {{ rawHtml }}</p>
<p>Using v-html directive: <span v-html="rawHtml"></span></p>
```
&emsp;&emsp;这个 span 的内容将会被替换成为属性值 rawHtml，直接作为 HTML——会忽略解析属性值中的数据绑定。  

### &emsp;3.特性

&emsp;&emsp;Mustache 语法不能作用在 HTML 特性上，遇到这种情况应该使用 v-bind 指令：
```HTML
<div v-bind:id="dynamicId"><div>
```
&emsp;&emsp;在布尔特性的情况下，它们的存在即暗示为 true，v-bind 工作起来略有不同，在这个例子中：
```HTML
<button v-bind:disabled="isButtonDisabled">Button</button>
```
&emsp;&emsp;如果 isButtonDisabled 的值是 null、undefined 或 false，则 disabled 特性甚至不会被包含在渲染出来的 \<button> 元素中。  

### &emsp;4.使用javaScript表达式

&emsp;&emsp;迄今为止，在我们的模板中，我们一直都只绑定简单的属性键值。但实际上，对于所有的数据绑定，Vue.js 都提供了完全的 JavaScript 表达式支持。
```HTML
{{ number + 1 }}
{{ ok ? 'YES' : 'NO' }}
{{ message.split('').reverse().join('') }}
<div v-bind:id="'list-' +id">
```
&emsp;&emsp;这些表达式会在所属 Vue 实例的数据作用域下作为 JavaScript 被解析。有个限制就是，每个绑定都只能包含单个表达式，所以下面的例子都不会生效:
```HTML
<!-- 这是语句，不是表达式 -->
{{ var a = 1 }}
<!-- 流控制也不会生效，请使用三元表达式 -->
{{ if (ok) { return message } }}
```

### &emsp;6.指令

&emsp;&emsp;指令 (Directives) 是带有 v- 前缀的特殊特性。指令特性的值预期是单个 JavaScript 表达式 (v-for 是例外情况，稍后我们再讨论)。指令的职责是，当表达式的值改变时，将其产生的连带影响，响应式地作用于 DOM。
```HTML
<p v-if="seen">现在你看到我了</p>
```
&emsp;&emsp;这里，v-if 指令将根据表达式 seen 的值的真假来插入/移除 <p> 元素。  

#### &emsp;&emsp;**参数**  
&emsp;&emsp;一些指令能够接收一个“参数”，在指令名称之后以冒号表示。例如，v-bind 指令可以用于响应式地更新 HTML 特性：
```HTML
<a v-bind:href="url">...</a>
```
&emsp;&emsp;在这里 href 是参数，告知 v-bind 指令将该元素的 href 特性与表达式 url 的值绑定。  
&emsp;&emsp;另一个例子是 v-on 指令，它用于监听 DOM 事件：
```HTML
<a v-on:click="dosomething">...</a>
```
&emsp;&emsp;在这里参数是监听的事件名。   

#### &emsp;&emsp;修饰符
&emsp;&emsp;修饰符 (Modifiers) 是以半角句号 . 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。例如，.prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()：
```HTML
<form v-on:submit.prevent="onSubmit">...</form>
```
### &emsp;7.缩写
&emsp;&emsp;Vue.js 为 v-bind 和 v-on 这两个最常用的指令，提供了特定简写：
&emsp;&emsp;`v-bind`缩写
```HTML
<!-- 完整语法 -->
<a v-bind:href="url">...</a>

<!-- 缩写 -->
<a :href="url">...</a>
```
&emsp;&emsp;`v-on`缩写
```HTML
<!-- 完整语法 -->
<a v-on:click="doSomething">...</a>

<!-- 缩写 -->
<a @click="doSomething">...</a>
```
&emsp;&emsp;它们看起来可能与普通的 HTML 略有不同，但 : 与 @ 对于特性名来说都是合法字符，在所有支持 Vue.js 的浏览器都能被正确地解析。  

## 计算属性和监听器

### &emsp;**1.计算属性**

模板内的表达式非常便利，但是设计它们的初衷是用于简单运算的。在模板中放入太多的逻辑会让模板过重且难以维护。例如：
```HTML
<div id="example">
  {{ message.split('').reverse().join('') }}
</div>
```
&emsp;&emsp;在这个地方，模板不再是简单的声明式逻辑。你必须看一段时间才能意识到，这里是想要显示变量 message 的翻转字符串。当你想要在模板中多次引用此处的翻转字符串时，就会更加难以处理。  
&emsp;&emsp;所以，对于任何复杂逻辑，你都应当使用**计算属性**。
#### &emsp;&emsp;**基础例子**
```HTML
<div id="example">
  <p>Original message: "{{ message }}"</p>
  <p>Computed reversed message: "{{ reversedMessage }}"</p>
</div>
```
```js
var vm = new Vue({
  el: '#example',
  data: {
    message: 'Hello'
  },
  computed: {
    // 计算属性的 getter
    reversedMessage: function () {
      // `this` 指向 vm 实例
      return this.message.split('').reverse().join('')
    }
  }
})
```
&emsp;&emsp;这里我们声明了一个计算属性 `reversedMessage`。我们提供的函数将用作属性 `vm.reversedMessage` 的 `getter`。  
&emsp;&emsp;可以像绑定普通属性一样在模板中绑定计算属性。Vue 知道 `vm.reversedMessage` 依赖于 `vm.message`，因此当 `vm.message` 发生改变时，所有依赖 `vm.reversedMessage` 的绑定也会更新。而且最妙的是我们已经以声明的方式创建了这种依赖关系：计算属性的 `getter` 函数是没有副作用 (side effect) 的，这使它更易于测试和理解。
> ### 计算属性 VS 方法
> 上面的效果也可以通过在表达式中调用方法来达到：
> ```HTML
> <p>Reversed message: "{{reversedMessage()}}"</p>
> ```
> ```js
> methods: {
>     reversedMessage:function(){
>         return this.message.split('').reverse().join('');
>     }
> }
> ```
> &emsp;&emsp;我们可以将同一函数定义为一个方法而不是一个计算属性。两种方式的最终结果确实是完全相同的。然而，不同的是计算属性是**基于它们的依赖进行缓存的**。只在相关依赖发生改变时它们才会重新求值。这就意味着只要 `message` 还没有发生改变，多次访问 `reversedMessage` 计算属性会立即返回之前的计算结果，而不必再次执行函数。  

> ### 计算属性 VS 监听属性
> Vue 提供了一种更通用的方式来观察和响应 Vue 实例上的数据变动：侦听属性。当你有一些数据需要随着其它数据变动而变动时，你很容易滥用 watch——特别是如果你之前使用过 AngularJS。然而，通常更好的做法是使用计算属性而不是命令式的 watch 回调。细想一下这个例子：
> ```HTML
> <div id="demo">{{ fullName}}</div>
> ```
> ```js
> var vm = new Vue({
>  el: '#demo',
>  data: {
>    firstName: 'Foo',
>    lastName: 'Bar',
>    fullName: 'Foo Bar'
>  },
>  watch: {
>    firstName: function (val) {
>      this.fullName = val + ' ' + this.lastName
>    },
>    lastName: function (val) {
>      this.fullName = this.firstName + ' ' + val
>    }
>  }
> })
> ```
> 上面代码是命令式且重复的。将它与计算属性的版本进行比较：
> ```js
>var vm = new Vue({
>  el: '#demo',
>  data: {
>    firstName: 'Foo',
>    lastName: 'Bar'
>  },
>  computed: {
>    fullName: function () {
>      return this.firstName + ' ' + this.lastName
>    }
>  }
> })
> ```
> 显然计算属性体现了它的优越性

> ### 计算属性的`setter`
>计算属性默认只有 getter ，不过在需要时你也可以提供一个 setter 
> ```js
> computed: {
>  fullName: {
>    /* getter */
>    get: function () {
>      return this.firstName + ' ' + this.lastName
>    },
>    /* setter */
>    set: function (newValue) {
>      var names = newValue.split(' ')
>      this.firstName = names[0]
>      this.lastName = names[names.length - 1]
>    }
>  }
> }
> ```
> &emsp;&emsp;现在再运行 vm.fullName = 'John Doe' 时，setter 会被调用，vm.firstName 和 vm.lastName 也会相应地被更新。

### &emsp;**2.监听器**

&emsp;&emsp;虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的。  
&emsp;&emsp;没学会！！！！！！！！！此处还有一万字！！！！！

