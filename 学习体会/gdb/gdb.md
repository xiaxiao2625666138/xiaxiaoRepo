# gdb的使用手册
周雪振 5141509091

---

## 一、(gdb)手册

<table>
<caption>(gdb)入门手册</caption>
  <tr>
  <th colspan="2">Command</th>
  <th>Effect</th>
  </tr>
  <tr>
  <td rowspan="3">Starting and stopping</td>
  <td>quit</td>
  <td>Exit GDB</td>
  </tr>
  <tr>
  <td>run</td>
  <td>Run your program (give command-line arguments here)</td>
  </tr>
  <tr>
  <td>kill</td>
  <td>Stop your program</td>
  </tr>
  <tr>
  <td rowspan="5">Breakpoints</td>
  <td>break multstore</td>
  <td>Set breakpoint at entry to function multstore</td>
  </tr>
  <tr>
  <td>break *0x400540</td>
  <td>Set breakpoint at adress 0x400540</td>
  </tr>
  <tr>
  <td>delete 1</td>
  <td>Delete breakpoint 1</td>
  </tr>
  <tr>
  <td>delete</td>
  <td>Delete all breakpoints</td>
  </tr>
  <tr>
  <td>info break</td>
  <td>Look breakPoint information</td>
  </tr>
  <tr>
  <td rowspan="5">Execution</td>
  <td>stepi</td>
  <td>Execute one instruction</td>
  </tr>
  <tr>
  <td>stepi 4</td>
  <td>Execute four instructions</td>
  </tr>
  <tr>
  <td>nexti</td>
  <td>Like stepi, but proceed through function calls</td>
  </tr>
  <tr>
  <td>continue</td>
  <td>Resume execution</td>
  </tr>
  <tr>
  <td>finish</td>
  <td>Run until current function returns</td>
  </tr>
  <tr>
  <td rowspan="5">Examining code</td>
  <td>disas</td>
  <td>Disassemble current function</td>
  </tr>
  <tr>
  <td>disas mulstore</td>
  <td>Disassemble function multstore</td>
  </tr>
  <tr>
  <td>disas 0x400544</td>
  <td>Disassemble function around address 0x400544</td>
  </tr>
  <tr>
  <td>disas 0x400540, 0x40054d</td>
  <td>Disassemble code within specified adress range</td>
  </tr>
  <td>print/x $rip</td>
  <td>Print program counter in hex</td>
  </tr>
  <tr>
  <td rowspan="8">Examining data</td>
  <td>print $rax</td>
  <td>Print contents of %rax in decimal</td>
  </tr>
  <tr>
  <td>print/x $rax</td>
  <td>Print contents of %rax in hex</td>
  </tr>
  <tr>
  <td>print/t $rax</td>
  <td>Print contents of %rax in binary</td>
  </tr>
  <tr>
  <td>print 0x100</td>
  <td>Print decimal representation of 0x100</td>
  </tr>
  <tr>
  <td>print/x 55</td>
  <td>Print hex representation of 55</td>
  </tr>
  <tr>
  <td>print/x ($rsp+8)</td>
  <td>Print (contents of %rsp) plus 8 in hex</td>
  </tr>
  <tr>
  <td>print *(long*) 0x7fffffffe81</td>
  <td>Print long integer at address 0x7fffffffe818</td>
  <tr>
  <td>print *(long*) ($rsp+8)</td>
  <td>Print long integer at adress (%rsp + 8)
  </tr>
  <tr>
  <td rowspan="3">Useful information</td>
  <td>info frame</td>
  <td>Information about current stack frame</td>
  </tr>
  <tr>
  <td>info registers</td>
  <td>Values of all the registers</td>
  </tr>
  <tr>
  <td>help</td>
  <td>Get information about GDB</td>
  </tr>
</table>

---

&emsp;&emsp;`print`很实用的用法之一还有：
```
print/s (char*) $rdi
```
## 二、`examine`命令的用法
&emsp;1.`examine`命令的用法:  
&emsp;&emsp;使用`examine`命令（简写是x）来查看内存地址中的值。`x`命令的语法如下：
```js
x/<n/f/u><addr>  
```
&emsp;&emsp;其中，n、f、u是可选的参数
* n是一个正整数，表示显示的数据的个数，也就是说从当前地址向后显示几个地址的内容
* f 表示显示的格式。
* u 表示显示的单个数据的长度，如果不指定的话，GDB默认是4个bytes。

### &emsp;2.f：
* `x` 按十六进制格式显示变量。
* `d` 按十进制格式显示变量。
* `u` 按十六进制格式显示无符号整型。
* `o` 按八进制格式显示变量。
* `t` 按二进制格式显示变量。
* `a` 按十六进制格式显示变量。
* `c` 按字符格式显示变量。
* `f` 按浮点数格式显示变量。

### &emsp;3.u:
&emsp;&emsp;当我们指定了字节长度后，GDB会从指内存定的内存地址开始，读写指定字节，并把其当作一个值取出来。
* b表示单字节
* h表示双字节
* w表示四字节
* g表示八字节。

### &emsp;4.例如：
&emsp;&emsp;查看0x400054地址处的int整数数组：
```
x/d5w 0x400054   //查看前5个元素
```
&emsp;&emsp;查看0x400080地址出的字符串：
```
x/c20b 0x200080   //查看前20个字符
```
&emsp;&emsp;查看R[rdi]地址中的long整形数组(以十六进制的格式查看)：
```
x/x8g $rdi  //查看前8个元素
```