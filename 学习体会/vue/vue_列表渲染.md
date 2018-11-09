# Vue学习——列表渲染

周雪振 5141509091

---

## 一、用 `v-for` 把一个数组对应为一组元素
&emsp;&emsp;我们用 `v-for`指令根据一组数组的选项列表进行渲染。`v-for` 指令需要使用 `item in items` 形式的特殊语法，`items` 是源数据数组并且 `item` 是数组元素迭代的别名。

```HTML
<ul id="example">
  <li v-for="item in items">
    {{item.message}}
  </li>
</ul>
```
```js
var example=new Vue({
    el:"#example",
    data:{
        items:[
           { message: 'Foo' },
           { message: 'Bar' }
        ]
    }
})
```
&emsp;&emsp;在 `v-for` 块中，我们拥有对父作用域属性的完全访问权限。`v-for` 还支持一个可选的第二个参数为当前项的索引。
```HTML
<ul id="example">
  <li v-for="(item, index) in items">
    {{parentMessage }} - {{index}} - {{item.message}}
  </li>
</ul>
```
```js
var example=new Vue({
    el:"#example",
    data:{
       parentMessage: 'parent',
       items:[
           {message: 'Foo'},
           {message: 'Bar'}
       ]
    }
    
})
```
&emsp;&emsp;也可以用 of 替代 in 作为分隔符，因为它是最接近 JavaScript 迭代器的语法：
```HTML
<div v-for="item of items"></div>
```

## 二、一个对象的`v-for`

&emsp;&emsp;可以用 v-for 通过一个对象的属性来迭代。
```HTML
<ul id="v-for-object" class="demo">
  <li v-for="value in object">
    {{ value }}
  </li>
</ul>
```
```js
new Vue({
  el: '#v-for-object',
  data: {
    object: {
      firstName: 'John',
      lastName: 'Doe',
      age: 30
    }
  }
})
```
&emsp;&emsp;可以提供第二个的参数为键名：
```HTML
<div v-for="(value, key) in object">
  {{ key }}: {{ value }}
</div>
```
&emsp;&emsp;第三个参数为索引：
```HTML
<div v-for="(value, key, index) in object">
  {{ index }}. {{ key }}: {{ value }}
</div>
```
>&emsp;&emsp;!在遍历对象时，是按 Object.keys() 的结果遍历，但是不能保证它的结果在不同的 JavaScript 引擎下是一致的。

> ### `key`
>&esmp;&emsp;当 Vue.js 用 v-for 正在更新已渲染过的元素列表时，它默认用“就地复用”策略。如果数据项的顺序被改变，Vue 将不会移动 DOM 元素来匹配数据项的顺序， 而是简单复用此处每个元素，并且确保它在特定索引下显示已被渲染过的每个元素。  
> &emsp;&esmpp;这个默认的模式是高效的，但是只适用于**不依赖子组件状态或临时 DOM 状态 **  
>&esmp;&emsp;为了给 Vue 一个提示，以便它能跟踪每个节点的身份，从而重用和重新排序现有元素，你需要为每项提供一个唯一 key 属性。理想的 key 值是每项都有的唯一 id。它的工作方式类似于一个属性，所以你需要用 v-bind 来绑定动态值
>```HTML
><div v-for="item in items" :key="item.id"></div>
>```
>&esmp;&emsp;建议尽可能在使用 v-for 时提供 key，除非遍历输出的 DOM 内容非常简单，或者是刻意依赖默认行为以获取性能上的提升。
>>key 并不与 v-for 特别关联，key 还具有其他用途

## 三、数组更新检测

### …&emsp;1.变异方法
&emsp;&emsp;Vue 包含一组观察数组的变异方法，所以它们也将会触发视图更新。这些方法如下：
* `push()`
* `pop()`
* `shift()`
* `unshift()`
* `splice()`
* `sort()`
* `reverse()`

### &emsp;2.替换数组
&emsp;&emsp;变异方法 (mutation method)，顾名思义，会改变被这些方法调用的原始数组。相比之下，也有非变异 (non-mutating method) 方法，例如：`filter()`, `concat()` 和 `slice()` 。这些不会改变原始数组，但总是返回一个新数组。当使用非变异方法时，可以用新数组替换旧数组：
```js
example1.items = example1.items.filter(function (item) {
  return item.message.match(/Foo/)
})
```
&emsp;&emsp;你可能认为这将导致 Vue 丢弃现有 DOM 并重新渲染整个列表。幸运的是，事实并非如此。Vue 为了使得 DOM 元素得到最大范围的重用而实现了一些智能的、启发式的方法，所以用一个含有相同元素的数组去替换原来的数组是非常高效的操作。

### &emsp;3.注意事项
&emsp;&emsp;由于 JavaScript 的限制，Vue 不能检测以下变动的数组：
* 当你利用索引直接设置一个项时，例如：`vm.items[indexOfItem] = newValue`
* 当你修改数组的长度时，例如：vm.items.length = newLength
&emsp;&emsp;例如：
```js
var vm = new Vue({
  data: {
    items: ['a', 'b', 'c']
  }
})
vm.items[1] = 'x' // 不是响应性的
vm.items.length = 2 // 不是响应性的
```
&emsp;&emsp;为了解决第一类问题，以下两种方式都可以实现和 `vm.items[indexOfItem] = newValue` 相同的效果，同时也将触发状态更新：
```js
Vue.set(vm.items, indexOfItem, newValue)
```
```js
vm.items.splice(indexOfItem, 1, newValue)
```
&emsp;&emsp;也可以使用 `vm.$set` 实例方法，该方法是全局方法` Vue.set` 的一个别名：
```js
vm.$set(vm.items, indexOfItem, newValue)
```
&emsp;&emsp;为了解决第二类问题，你可以使用 `splice`：
```js
vm.items.splice(newLength)
```