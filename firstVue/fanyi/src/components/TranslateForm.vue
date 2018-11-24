<template>
  <div id="translateForm">
    <form v-on:submit="formSubmit">
      <input id="input_text" 
      v-bind:class="{input_text: active, mouseover_input:alive_input}" 
      @mouseover="light_input" @mouseout="dark_input"
      type="text" v-model="textToTranslate" 
       placeholder="输入需要翻译的内容" >
      <select  v-model="language" 
      v-bind:class="{select_:active, mouseover_select:alive_select}"
      @mouseover="light_select" @mouseout="dark_select">
        <option disabled value="">language</option>
        <option value="en">English</option>
        <option value="it">Italian</option>
        <option value="fr">French</option>
        <option value="zh">Chinese</option>
        <option value="la">Latin</option>
        <option value="ja">Japanese</option>
      </select>
      <input id="input_submit" type="submit" value="翻译" 
      v-bind:class="{input_submit:active, mouseover_submit:alive_submit}"
      @mouseover="light_submit" @mouseout="dark_submit" 
      @mousedown="dark_submit" @mouseup="light_submit">
    </form>
  </div>
</template>

<script>
export default {
  name: 'translateForm',
  data: function () {
    return {
      textToTranslate: '',
      language:"",
      active:true,
      alive_input:false,
      alive_select:false,
      alive_submit:false
    }
  },
  methods: {
    formSubmit: function (event) {
      if(this.language!="" && this.textToTranslate!=""){
        this.$emit('formSubmit', this.textToTranslate, this.language)
      }else if(this.language==""){
        alert("Please choose language!");
      }
      event.preventDefault()
    },
    light_input:function(){
      this.alive_input=true;
    },
    dark_input:function(){
      this.alive_input=false;
    },
    light_select:function(){
      this.alive_select=true;
    },
    dark_select:function(){
      this.alive_select=false;
    },
    light_submit:function(){
      this.alive_submit=true;
    },
    dark_submit:function(){
      this.alive_submit=false;
    }
  }
}

</script>

<style scoped>
#translateForm{
  margin:auto;
  background-color:rgb(243, 237, 237);
  border-color:lightslategrey;
  border-width:5px;
  border-radius:7px;
  max-height:100px;
  width:600px;
  padding-top:20px;
  padding-bottom:15px;
}

.input_text{
  border-left-style:none;
  border-right-style:none;
  border-top-style:none;
  background-color:rgb(243, 237, 237);
  border-bottom-color:rgb(246, 251, 252);
  border-bottom-width:1px;
  height:40px;
  width:200px;
}

.mouseover_input{
  border-bottom-color:rgb(24, 182, 245);
  border-width:3px;
}

#input_text::-ms-input-placeholder{
  color:LightGrey;
}

.select_{
  font-family:KaiTi;
  font-size:15px;
  border-left-style:none;
  border-right-style:none;
  border-top-style:none;
  background-color:rgb(243, 237, 237);
  border-bottom-color:rgb(176, 185, 187);
  border-bottom-width:1px;
  color:rgb(33, 92, 83);
  cursor:pointer;
  height:42px;
}


.mouseover_select{
  border-bottom-color:rgb(24, 182, 245);
  border-width:3px;
}

.input_submit{
  background:rgb(1, 176, 245);
  width:100px;
  height:42px;
  border-radius:3px;
  border-width:0px;
  outline:none;
  cursor:pointer;
  color:white;
  font-size:17px;
  font-family:STKaiti;
}

.mouseover_submit{
  background:rgb(1, 137, 190);
}
</style>
