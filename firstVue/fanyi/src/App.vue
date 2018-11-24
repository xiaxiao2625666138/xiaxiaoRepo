<template>
  <div id="app">
    <h1>在线翻译</h1>
    <h4 class="text-muted">简单 · 易用 · 便捷</h4>
    <translateForm v-on:formSubmit="translateText"/>
    <translateOutput v-bind:ToTranslateText="toTranslateText" v-bind:TranslatedText="translatedText" />
  </div>
</template>

<script>
import TranslateForm from './components/TranslateForm'
import TranslateOutput from './components/TranslateOutput'
export default {
  name: 'App',
  data:function(){
    return {
      toTranslateText:"",
      translatedText:""
    }
  },
  components: {
    TranslateForm,
    TranslateOutput
  },
  methods: {
    translateText: function (text, language) {
      this.toTranslateText=text
      this.$http.get('https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20181123T061042Z.24422ee85994c031.13e29584706bd96f330f5e2745206d86a7fa9dd2&lang='+language+'&text='+text).then((response) => {
        this.translatedText=response.body.text[0]
      })
    }
  }
}
</script>

<style>
#app{
  text-align:center;
  line-height:10px;
  margin-top:15%;
}

h1 {
  font-family: "STZHongsong";
  font-size:40px;
  color:#505050;
}

h4{
    color:#E0E0E0;
}
</style>
