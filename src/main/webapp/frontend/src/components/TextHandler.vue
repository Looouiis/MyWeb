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
    default: String,
    inputDefault: Boolean
  },
  computed: {
    output() {
      return marked(this.input)
    }
  },
  methods: {
    update: debounce(function (e) {
      this.input = e.target.value
    }, 100),
    clear(){
      this.input = ''
    },
    submit(){
      this.$emit('submit', this.input)
      this.clear
    }
  },
  created(){
    if(this.default.indexOf('usr') !== -1 && this.inputDefault){
      this.input = '# Hello World\n\n在这的留言是\*\*不会\*\*让其他人看到的'
    }
    else if(this.default.indexOf('ano') !== -1 && this.inputDefault){
      
    }
  },
  emits: ['submit']
}
</script>

<template>
  <div class="editor">
    <textarea class="input" :value="input" @input="update"></textarea>
    <div class="output" v-html="output"></div>
  </div>
  <div class="btn">
    <button class="submit" @click="submit">提交</button>
    <button class="reset" @click="clear">清空</button>
  </div>
</template>

<style lang="less">
body {
  margin: 0;
}

.editor{
  display: flex;
  height: 30vh;
  margin: 0 2rem;
  border: solid .2rem rgb(0 0 0 / 30%);
  border-radius: 1rem;
}
.btn{
  display: flex;
  justify-content: flex-start;
  padding: 1rem 2rem;
  padding-top: 0;
  padding-bottom: 0;
  button{
    margin: .3rem .3rem;
    width: 3.5rem;
    height: 1.5rem;
    border: none;
    outline: none;
    border-radius: 5px;
    cursor: pointer;
    &.submit{
        background-color: #27516e;
        color: #FFF;
        &:hover{
        background-color: #7c9388;
        color: #000;
        }
    }
    &.reset{
        background-color: #FFF;
        border: #000 solid 1px;
    }
  }
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