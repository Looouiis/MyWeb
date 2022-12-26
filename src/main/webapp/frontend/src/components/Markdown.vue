<script>
import { marked } from 'marked'
import { debounce } from 'lodash-es'

export default {
  name: 'Markdown',
  data: () => ({
    input: "**简陋介绍一下markdown语法**\n<br><br>\n“#”+一个空格开头表示为**标题**的特殊符号，**n个“#”代表n级标题**\n<br><br>\n“\\\*\\\*”+您的内容+“\\\*\\\*”会将“您的内容”标为**粗体**；&emsp;“\\\*”+您的内容+“\\\*”会将您的内容标为*斜体*；&emsp;“\\\~”+您的内容+“\\\~”则为~删除线~\n<br><br>\n在**新的段落开头**输入“\\\*”+一个空格表示无编号要点\n<br>\n在**新的段落开头**输入数字+“.”+一个空格表示有编号要点（输入框中另起一行且新行开头**不超过三个空格**则会自动纠正为顺编号的顺序，超过三个空格表示为子要点（具体效果可前往**typora**等专业markdown软件中查看））\n<br><br>\n若**不想**使用markdown语法，只需记得**两个回车为新起一段**即可，内容**直接写**"
  }),
  computed: {
    output() {
      return marked(this.input)
    }
  },
  methods: {
    update: debounce(function (e) {
      this.input = e.target.value
    }, 100)
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