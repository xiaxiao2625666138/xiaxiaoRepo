# <img src="img\vue.png" width="25px" height="25px" /> Vue学习体会——Class和Style绑定

周雪振 5141509091

---

## 一、绑定HTML class

### &emsp;1.对象语法
&emsp;&emsp;我们可以传给 `v-bind:class` 一个对象，以动态地切换 class：
```HTML
<div v-bind:class="{ active: isActive }"></div>
```
&emsp;&emsp;上面的语法表示 active 这个 class 存在与否将取决于数据属性 isActive 的 truthiness。  
&emsp;&emsp;v-bind:class 指令也可以与普通的 class 属性共存。当有如下模板:
```HTML
<div class="static"
     v-bind:class="{ active: isActive, 'text-danger': hasError }">
</div>
```
```js
data: {
  isActive: true,
  hasError: false
}
```
&emsp;&emsp;结果渲染为：`<div class="static active"></div>`    
&emsp;&emsp;当 `isActive` 或者 `hasError` 变化时，class 列表将相应地更新。例如，如果 `hasError` 的值为 `true`，class 列表将变为 `"static active text-danger"`。  
&emsp;&emsp;绑定的数据对象不必内联定义在模板里：
```HTML
<div v-bind:class="classObiect"></div>
```
```js
data:{
  classObject:{
    active:true,
    'text-danger':false
  }
}
```
&emsp;&emsp;渲染结果和上面一样。  
&emsp;&emsp;也可以在这里绑定一个返回对象的计算属性。这是一个常用且强大的模式：
```HTML
<div v-bind:class="classObject"></div>
```
```js
data:{
  isActive:true,
  error:null
}
computed:{
  classObject:function(){
    return {
      active:this.isActive && !this.error,
      'text-danger':this.error &&this.error.type==='fatal'
    }
  }
}
```

### &emsp;2.数组语法
&emsp;&emsp;我们可以把一个数组传给 v-bind:class，以应用一个 class 列表：
```HTML
<div v-bind:class="[activeClass, errorClass]"></div>
```
```js
data:{
  activeClass: 'active',
  errorClass: 'text-danger'
}
```
&emsp;&emsp;渲染为：`<div class="active text-danger"></div>`  
&emsp;&emsp;如果你也想根据条件切换列表中的 class，可以用三元表达式：
```HTML
<div v-bind:class="[isActive ? activeClass : '', errorClass]"></div>
```
&emsp;&emsp;这样写将始终添加 `errorClass`，但是只有在 `isActive` 是 `truthy` 时才添加 `activeClass`。 &emsp;&emsp;不过，当有多个条件 class 时这样写有些繁琐。所以在数组语法中也可以使用对象语法：
```HTML
<div v-bind:class="[{ active: isActive }, errorClass]"></div>
```

## 二、绑定内联样式

### &emsp;1.对象语法
> `v-bind:style` 的对象语法十分直观——看着非常像 CSS，但其实是一个 JavaScript 对象。CSS 属性名可以用驼峰式 (camelCase) 或短横线分隔 (kebab-case，记得用单引号括起来) 来命名：

```HTML
<div v-bind:style="{color: activeCorlor, fontSize:fontSize+30}"></div>
```
```js
data:{
  activeColor: 'red',
  fontSize:30
}
```
&emsp;&emsp;其实，可以直接绑定到一个样式对象，让模板更清晰：
```HTML
<div v-bind:style="styleObject"></div>
```
```js
data:{
  styleObject:{
    color: 'red',
    fontSize: '13px'
  }
}
```
&emsp;&emsp;同样的，对象语法常常也会结合返回对象的计算属性使用。

### &emsp;2.数组语法
&emsp;&emsp;`v-bind:style` 的数组语法可以将多个样式对象应用到同一个元素上：
```HTML
<div v-bind:style="[baseStyles, overridingStyles]"></div>
```
### &emsp;3.自动添加前缀
&emsp;&emsp;当 `v-bind:style` 使用需要添加浏览器引擎前缀的 CSS 属性时，如 `transform`，Vue.js 会自动侦测并添加相应的前缀。
### &emsp; 4.多重值
&emsp;&emsp;可以为 `style` 绑定中的属性提供一个包含多个值的数组，常用于提供多个带前缀的值，例如：
```HTML
<div :style="{ display: ['-webkit-box', '-ms-flexbox', 'flex'] }"></div>
```
&emsp;&emsp;这样写只会渲染数组中最后一个被浏览器支持的值。在本例中，如果浏览器支持不带浏览器前缀的 `flexbox`，那么就只会渲染 `display: flex`。