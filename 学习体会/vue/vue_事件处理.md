# Vue学习——事件处理
周雪振  5141509091
 
---

## 一、事件监听
&emsp;&emsp;可以用 v-on 指令监听 DOM 事件，并在触发时运行一些 JavaScript 代码。
```HTML
<div id="example-1">
    <button v-on:click="counter +=1">Add 1</button>
    <p>The button above has been clicked {{counter}} times.</p>
</div>
```
```js
var example1=new Vue({
    el:"#example-1",
    data:{
        counter:0
    }
})
```
## 二、时间处理方法
&emsp;&emsp;然而许多事件处理逻辑会更为复杂，所以直接把 `JavaScript` 代码写在 `v-on` 指令中是不可行的。因此 `v-on` 还可以接收一个需要调用的方法名称。
```HTML
<div id="example-2">
  <!-- `greet` 是在下面定义的方法名 -->
  <button v-on:click="greet">Greet</button>
</div>
```
```js
var example2 = new Vue({
  el: '#example-2',
  data: {
    name: 'Vue.js'
  },
  // 在 `methods` 对象中定义方法
  methods: {
    greet: function (event) {
      // `this` 在方法里指向当前 Vue 实例
      alert('Hello ' + this.name + '!')
      // `event` 是原生 DOM 事件
      if (event) {
        alert(event.target.tagName)
      }
    }
  }
})

// 也可以用 JavaScript 直接调用方法
example2.greet() // => 'Hello Vue.js!'
```

## 三、内联处理器中的方法
&emsp;&emsp;除了直接绑定到一个方法，也可以在内联 `JavaScript` 语句中调用方法：
```HTML
<div id="example-3">
  <button v-on:click="say('hi')">Say hi</button>
  <button v-on:click="say('what')">Say what</button>
</div>
```
```js
new Vue({
  el: '#example-3',
  methods: {
    say: function (message) {
      alert(message)
    }
  }
})
```
&emsp;&emsp;有时也需要在内联语句处理器中访问原始的 DOM 事件。可以用特殊变量 `$event` 把它传入方法：
```HTML
<button v-on:click="warn('Form cannot be submitted yet.', $event)">
  Submit
</button>
```
```js
// ...
methods: {
  warn: function (message, event) {
    // 现在我们可以访问原生事件对象
    if (event) event.preventDefault()
    alert(message)
  }
}
```

## 三、事件修饰符
&emsp;&emsp;在事件处理程序中调用 `event.preventDefault()` 或 `event.stopPropagation()` 是非常常见的需求。尽管我们可以在方法中轻松实现这点，但更好的方式是：方法只有纯粹的数据逻辑，而不是去处理 DOM 事件细节。 
&emsp;&emsp;为了解决这个问题，Vue.js 为 v-on 提供了事件修饰符。之前提过，修饰符是由点开头的指令后缀来表示的。
* `.stop`
* `.prevent`
* `.capture`
* `.self`
* `.once`
* `.passive`
```HTML
<!-- 阻止单击事件继续传播 -->
<a v-on:click.stop="doThis"></a>

<!-- 提交事件不再重载页面 -->
<form v-on:submit.prevent="onSubmit"></form>

<!-- 修饰符可以串联 -->
<a v-on:click.stop.prevent="doThat"></a>

<!-- 只有修饰符 -->
<form v-on:submit.prevent></form>

<!-- 添加事件监听器时使用事件捕获模式 -->
<!-- 即元素自身触发的事件先在此处理，然后才交由内部元素进行处理 -->
<div v-on:click.capture="doThis">...</div>

<!-- 只当在 event.target 是当前元素自身时触发处理函数 -->
<!-- 即事件不是从内部元素触发的 -->
<div v-on:click.self="doThat">...</div>
```

>&emsp;&emsp;使用修饰符时，顺序很重要；相应的代码会以同样的顺序产生。因此，用 `v-on:click.prevent.self` 会阻止所有的点击，而 `v-on:click.self.prevent` 只会阻止对元素自身的点击。  

&emsp;&emsp;不像其它只能对原生的 DOM 事件起作用的修饰符，`.once` 修饰符还能被用到自定义的组件事件上。`.once`表示事件只会出发一次。  

&emsp;&emsp;Vue 还对应` addEventListener` 中的 `passive` 选项提供了 .passive 修饰符。`.passive` 修饰符尤其能够提升移动端的性能。`.passive `会告诉浏览器你不想阻止事件的默认行为。
```HTML
<!-- 滚动事件的默认行为 (即滚动行为) 将会立即触发 -->
<!-- 而不会等待 `onScroll` 完成  -->
<!-- 这其中包含 `event.preventDefault()` 的情况 -->
<div v-on:scroll.passive="onScroll">...</div>
```

## 四、按键修饰符
&emsp;&emsp;在监听键盘事件时，我们经常需要检查常见的键值。Vue 允许为 `v-on` 在监听键盘事件时添加按键修饰符：  
```HTML
<!-- 只有在 `keyCode` 是 13 时调用 `vm.submit()` -->
<input v-on:keyup.13="submit">
```
&emsp;&emsp;记住所有的` keyCode` 比较困难，所以 Vue 为最常用的按键提供了别名：
```HTML
<!-- 同上 -->
<input v-on:keyup.enter="submit">

<!-- 缩写语法 -->
<input @keyup.enter="submit">
```
&emsp;&emsp;全部的按键别名：
* `.enter`
* `.tab`
* `.delete`
* `.esc`
* `.space`
* `.up`
* `.down`
* `.legt`
* `.right`
&emsp;&emsp;可以通过全局 config.keyCodes 对象自定义按键修饰符别名：
```js
Vue.config.keyCodes.f1=112
```
## 五、系统修饰键
&emsp;&emsp;可以用如下修饰符来实现仅在按下相应按键时才触发鼠标或键盘事件的监听器。  
* `.ctrl`
* `.alt`
* `.shift`
* `.meta`
&emsp;&emsp;注意：在 Mac 系统键盘上，meta 对应 command 键 (⌘)。在 Windows 系统键盘 meta 对应 Windows 徽标键 (⊞)。在 Sun 操作系统键盘上，meta 对应实心宝石键 (◆)。在其他特定键盘上，尤其在 MIT 和 Lisp 机器的键盘、以及其后继产品，比如 Knight 键盘、space-cadet 键盘，meta 被标记为“META”。在 Symbolics 键盘上，meta 被标记为“META”或者“Meta”。  

```HTML
<!-- Alt + C -->
<input @keyup.alt.67="clear">

<!-- Ctrl + Click -->
<div @click.ctrl="doSomething">Do something</div>
```
>&emsp;&emsp;请注意修饰键与常规按键不同，在和 keyup 事件一起用时，事件触发时修饰键必须处于按下状态。换句话说，只有在按住 ctrl 的情况下释放其它按键，才能触发 keyup.ctrl。而单单释放 ctrl 也不会触发事件。如果你想要这样的行为，请为 ctrl 换用 keyCode：keyup.17。

&emsp;&emsp;`.exact` 修饰符允许你控制由精确的系统修饰符组合触发的事件。  
```HTML
<!-- 即使 Alt 或 Shift 被一同按下时也会触发 -->
<button @click.ctrl="onClick">A</button>

<!-- 有且只有 Ctrl 被按下的时候才触发 -->
<button @click.ctrl.exact="onCtrlClick">A</button>

<!-- 没有任何系统修饰符被按下的时候才触发 -->
<button @click.exact="onClick">A</button>
```
&emsp;&emsp;鼠标按键修饰符  
* `.left`
* `.right`
* `.middle`