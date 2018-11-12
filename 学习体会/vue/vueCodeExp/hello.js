var app1 = new Vue({
    el: "#app1",
    data: {
        msg: "hello vue!",
        op: "vue"
    },
    methods: {
        change_msg: function () {
            this.msg = (this.op == "vue" ? "hello world!" : "hello vue");
            this.op = (this.op == "vue" ? "world" : "vue");
        }
    }
})


var app2 = new Vue({
    el: "#app2",
    data: {
        msg: "hello vue!",
        op: "vue"
    },
    methods: {
        change_msg: function () {
            this.msg = (this.op == "vue" ? "hello world!" : "hello vue");
            this.op = (this.op == "vue" ? "world" : "vue");
        }
    }
})

var app3 = new Vue({
    el: "#app3",
    data: {
        rawHtml:'<span style="color:red">This should be red.</span>'
    }
})

var app4 = new Vue({
    el: "#app4",
    data: {
        app_style: {
            color: "red",
            fontSize: "40px"
        },
        as:1
    }
})

var app5 = new Vue({
    el: "#app5",
    data: {
        ok: 1,
        number:0
    },
    methods: {
        add: function () {
            this.number++;
        }
    }
})

var app6 = new Vue({
    el: "#app6",
    data: {
        message:"Hello Vue!"
    },
    computed: {
        reversedMessage: function () {
            return this.message.split('').reverse().join('')
        }
    }
})

var app7 = new Vue({
    el: "#app7",
    data: {
        firstname: "Zhou",
        lastname: "Xuezhen"
    },
    computed: {
        fullname: {
            get: function () {
                return this.firstname + " " + this.lastname
            },
            set: function (newname) {
                var names = newname.split(' ');
                this.firstname = names[0];
                this.lastname = names[1];
            }
        }
    },
    methods: {
        change_name: function () {
            this.fullname = "Li Xiaoxiao";
        }
    }
})

var app8 = new Vue({
    el: "#app8",
    data: {
        p_style: {
            color: "green",
            fontSize:"30px"
        },
        p_style_0: {
            color: "brown",
            fontSize: "40px"
        },
        now_style: {
            color: "blue",
            fontSize:"60px"
        },
        state:0
    },
    methods: {
        change_style: function () {
            this.state = !this.state;
            this.now_style =this.state ? this.p_style_0 : this.p_style;
        }
    }
})

//测试class的绑定(对象语法)
var app9 = new Vue({
    el: "#app9",
    data: {
        selfClass: {
            class1: true,
            class2: false
        },
        message:"Hello Vue! "
    },
    methods: {
        change: function () {
            this.selfClass.class1 = !this.selfClass.class1;
            this.selfClass.class2 = !this.selfClass.class2;

        }
    }
})

//测试class的绑定(对象语法2)
var app10 = new Vue({
    el: "#app10",
    data: {
        c1alive: true,
        c2alive: false,
        message:"Hello Vue"
    },
    methods:{
        change: function () {
            this.c1alive = !this.c1alive;
            this.c2alive = !this.c2alive
        }
    }
})

//class绑定(计算属性)
var app11 = new Vue({
    el: "#app11",
    data: {
        c1alive: true,
        message:"Hello Vue!"
    },
    computed: {
        selfClass: function () {
            return { class1:this.c1alive, class2:!this.c1alive}
        }
    },
    methods: {
        change: function () {
            this.c1alive=!this.c1alive
        }
    }
})

//内联style绑定测试
var app12 = new Vue({
    el: "#app12",
    data: {
        selfStyle: {
            textAlign:"center",
            color: "#222222",
            backgroundColor: "grey",
            width: "300px",
            height: "200px"
        },
        message: "Hello world",
        onmouse: 0
    },
    methods: {
        change: function () {
            this.onmouse = !this.onmouse;
            this.selfStyle.backgroundColor = this.onmouse ? "#992838" : "grey";
        }
    }

})

//测试v-for
var app13 = new Vue({
    el: "#app13",
    data: {
        parentMessage:"app13",
        items: [
            { message: 'apple' },
            { message: 'banana' },
            { message: 'orange' },
            { message: 'blue'}
        ]
    },
    methods: {
        exchange: function () {
            var v = this.items[0].message;
            this.items[0].message = this.items[1].message;
            this.items[1].message = this.items[2].message;
            this.items[2].message = this.items[3].message;
            this.items[3].message = v;
        }
    }
})

//测试对象的v-for
var app14 = new Vue({
    el: "#app14",
    data: {
        friend: {
            firstName: "龙",
            lastName: "泽宇",
        }
    }
})

app14.friend = Object.assign({}, app14.friend,{
    age: 23,
    like:"compute game"
})
