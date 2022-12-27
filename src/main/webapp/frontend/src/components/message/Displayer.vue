<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import { marked } from 'marked'

export default {
  name: 'Displayer',
  data: () => ({
    input: ''
  }),
  props:{
    text: String
  },
  computed: {
    output() {
      return marked(this.text)
    }
  }
}
</script>

<template>
    <div class="displayer" v-html="output"></div>
</template>

<style lang="less">
body {
  margin: 0;
}

.displayer {
  overflow: auto;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 1rem 0.5rem;
  text-align: left;
  background-color: rgb(0 0 0 / 3%);
  border-radius: .3rem;
}

code {
  color: #f66;
}
</style>