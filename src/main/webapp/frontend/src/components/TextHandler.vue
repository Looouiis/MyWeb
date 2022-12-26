<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import { marked } from 'marked'
import { debounce } from 'lodash-es'

export default {
  name: 'TextHandler',
  data: () => ({
    input: ''
  }),
  props:{
    default: String
  },
  computed: {
    output() {
      return marked(this.input)
    }
  },
  methods: {
    update: debounce(function (e) {
      this.input = e.target.value
    }, 100)
  },
  created(){
    if(this.default.indexOf('usr') !== -1){
      this.input = '# Hello World\n\n在这的留言是\*\*不会\*\*让其他人看到的'
    }
    else if(this.default.indexOf('ano') !== -1){
      
    }
  }
}
</script>

<template>
  <div class="editor">
    <textarea class="input" :value="input" @input="update"></textarea>
    <div class="output" v-html="output"></div>
  </div>
</template>

<style>
body {
  margin: 0;
}

.editor {
  height: 100vh;
  display: flex;
}

.input,
.output {
  overflow: auto;
  width: 50%;
  height: 100%;
  box-sizing: border-box;
  padding: 0 20px;
  text-align: left;
}

.input {
  border: none;
  border-right: 1px solid #ccc;
  border-top-left-radius: 1rem;
  border-bottom-left-radius: 1rem;
  resize: none;
  outline: none;
  background-color: #f6f6f6;
  font-size: 14px;
  font-family: 'Monaco', courier, monospace;
  padding: 20px;
}

code {
  color: #f66;
}
</style>