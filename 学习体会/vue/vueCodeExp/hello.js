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