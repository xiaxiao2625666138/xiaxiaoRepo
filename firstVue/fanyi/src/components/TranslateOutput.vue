<template>
  <div id="translateOutput">
    <h2>{{ToTranslateText}}</h2>
    <h2>{{TranslatedText}}</h2>
  </div>
</template>

<script>
export default {
  name: 'translateOutput',
  props:["ToTranslateText","TranslatedText"]
}
</script>

<style scoped>
#translateOutput{
  color:rgb(5, 105, 145);
}
</style>