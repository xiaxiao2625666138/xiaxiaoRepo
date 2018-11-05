# <img src="img\vue.png" width="25px" height="25px" /> Vue学习体会——计算属性和监听器

周雪振 5141509091

---

## 一、计算属性

模板内的表达式非常便利，但是设计它们的初衷是用于简单运算的。在模板中放入太多的逻辑会让模板过重且难以维护。例如：
```HTML
<div id="example">
  {{ message.split('').reverse().join('') }}
</div>
```
&emsp;&emsp;在这个地方，模板不再是简单的声明式逻辑。你必须看一段时间才能意识到，这里是想要显示变量 message 的翻转字符串。当你想要在模板中多次引用此处的翻转字符串时，就会更加难以处理。  
&emsp;&emsp;所以，对于任何复杂逻辑，你都应当使用**计算属性**。
### &emsp;1.基础例子
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
### 2.计算属性 VS 方法
上面的效果也可以通过在表达式中调用方法来达到
```HTML
<p>Reversed message: "{{reversedMessage()}}"</p>
```
```js
methods: {
       reversedMessage:function(){
       return this.message.split('').reverse().join('');
     }
 }
 ```
 &emsp;&emsp;我们可以将同一函数定义为一个方法而不是一个计算属性。两种方式的最终结果确实是完全相同的。然而，不同的是计算属性是**基于它们的依赖进行缓存的**。只在相关依赖发生改变时它们才会重新求值。这就意味着只要 `message` 还没有发生改变，多次访问 `reversedMessage` 计算属性会立即返回之前的计算结果，而不必再次执行函数。  

### 3.计算属性 VS 监听属性
&emsp;&emsp; Vue 提供了一种更通用的方式来观察和响应 Vue 实例上的数据变动：侦听属性。当你有一些数据需要随着其它数据变动而变动时，你很容易滥用 watch——特别是如果你之前使用过 AngularJS。然而，通常更好的做法是使用计算属性而不是命令式的 watch 回调。细想一下这个例子：
```HTML
<div id="demo">{{ fullName}}</div>
```
```js
var vm = new Vue({
el: '#demo',
data: {
   firstName: 'Foo',
   lastName: 'Bar',
   fullName: 'Foo Bar'
},
watch: {
    firstName: function (val) {
    this.fullName = val + ' ' + this.lastName
    },
    lastName: function (val) {
    this.fullName = this.firstName + ' ' + val
    }
  }
})
```
&emsp;&emsp;上面代码是命令式且重复的。将它与计算属性的版本进行比较：
```js
var vm = new Vue({
  el: '#demo',
  data: {
    firstName: 'Foo',
    lastName: 'Bar'
  },
  computed: {
    fullName: function () {
      return this.firstName + ' ' + this.lastName
    }
  }
})
```
&emsp;&emsp;显然计算属性体现了它的优越性

### 4.计算属性的`setter`
计算属性默认只有 getter ，不过在需要时你也可以提供一个 setter 
```js
computed: {
    fullName: {
    /* getter */
    get: function () {
      return this.firstName + ' ' + this.lastName
    },
    /* setter */
    set: function (newValue) {
      var names = newValue.split(' ')
      this.firstName = names[0]
      this.lastName = names[names.length - 1]
    }
  }
 }
```
&emsp;&emsp;现在再运行 vm.fullName = 'John Doe' 时，setter 会被调用，vm.firstName 和 vm.lastName 也会相应地被更新。

## 二、监听器

&emsp;&emsp;虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的。    
&emsp;&emsp;没学会！！！！！！！！！此处还有一万字！！！！！

---