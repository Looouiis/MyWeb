<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import TextHandler from '@/components/TextHandler.vue'
import SingleMessage from '@/components/message/SingleMessage.vue'

export default {
  name: 'Message',
  components:{
    TextHandler,
    SingleMessage
  },
  props:{
    default: String,
    userId: Number,
    isMe: Boolean
  },
  watch:{
    isMe(lat,old){
      // alert(old)
      // alert(lat)
      if(lat){
        this.axios.get('http://localhost/myUnread').then((res) => {
          if(res.data.status){
            this.msgUsrList = res.data.content
          }
        })
        this.axios.get('http://localhost/users/true').then((res) => {
          // console.log(res.data)
          this.anoList = res.data.content.anoUsers
          this.usrList = res.data.content.users
          // console.log(this.usrList)
        })
      }
    }
  },
  data(){
    return{
      currentPage: -1,
      defaultNum: 3,
      msgList: Object,
      messageBy: String,
      myName: String,
      msgUsrList: Object,
      usrList: Object,
      anoList: Object,
      talkTo: null,
      talkToWho: String
    }
  },
  mounted(){
    this.$emit('response', this.default + 'message-mode noMsg')
    if(this.isMe){
      this.axios.get('http://localhost/myUnread').then((res) => {
        if(res.data.status){
          this.msgUsrList = res.data.content
        }
      })
      this.axios.get('http://localhost/users/true').then((res) => {
        // console.log(res.data)
        this.anoList = res.data.content.anoUsers
        this.usrList = res.data.content.users
        // console.log(this.usrList)
      })
    }
    else if(this.default.indexOf('usr') !== -1){
      this.axios.get('http://localhost/users/communication/'+this.defaultNum+'/'+this.currentPage+'/0').then((res) => {
        // console.log(res.data)
        if('status' in res.data && res.data.status){
          this.msgList = res.data.content.messages
          this.messageBy = res.data.content.messageBy
          this.myName = res.data.content.myName
        }
      })
    }
    else if(this.default.indexOf('ano') !== -1){
      this.axios.get('http://localhost/anonymous/communication/'+this.defaultNum+'/'+this.currentPage+'/0').then((res) => {
        // console.log(res.data)
        if('status' in res.data && res.data.status){
          this.msgList = res.data.content.messages
          this.messageBy = '匿名'+res.data.content.anoId
          this.myName = res.data.content.myName
        }
      })
    }
  },
  methods: {
    submit(content){
      if(this.isMe){
        // alert(this.isMe)
        if(this.talkTo === null)
        alert('请先选择对象')
        if(this.talkToWho === 'usr'){
          this.axios({
            method: 'POST',
            headers: { 'content-type': 'application/x-www-form-urlencoded' },
            data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
            url: 'http://localhost/users/reply/'+this.talkTo
          }).then((res) => {
            if('status' in res.data && res.data.status){
              this.$message.success(res.data.description)
            }
            else if('status' in res.data){
              this.$message.error(res.data.description)
            }
            else{
              this.$message.error(res.data.exceptionMessage)
            }
          })
        }
        else if(this.talkToWho === 'ano'){
          this.axios({
            method: 'POST',
            headers: { 'content-type': 'application/x-www-form-urlencoded' },
            data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
            url: 'http://localhost/anonymous/reply/'+this.talkTo
          }).then((res) => {
            if('status' in res.data && res.data.status){
              this.$message.success(res.data.description)
            }
            else if('status' in res.data){
              this.$message.error(res.data.description)
            }
            else{
              this.$message.error(res.data.exceptionMessage)
            }
          })
        }
      }
      else{
        if(this.default.indexOf('usr') !== -1){
          this.axios({
            method: 'POST',
            headers: { 'content-type': 'application/x-www-form-urlencoded' },
            data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
            url: 'http://localhost/users/communication'
          }).then((res) => {
            // console.log(res)
            if('status' in res.data && res.data.status){
              this.$message.success(res.data.description)
            }
            else if('status' in res.data){
              this.$message.error(res.data.description)
            }
            else{
              this.$message.error(res.data.exceptionMessage)
            }
          })
          // .post("http://localhost:801/users/communication','content:'+content)
        }
        else if(this.default.indexOf('ano') !== -1){
          this.axios({
            method: 'POST',
            headers: { 'content-type': 'application/x-www-form-urlencoded' },
            data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
            url: 'http://localhost/anonymous/communication'
          }).then((res) => {
            // console.log(res)
            if('status' in res.data && res.data.status){
              if(res.data.content !== null){
                localStorage.setItem('anoToken', res.data.content)
              }
              this.$message.success(res.data.description)
            }
            else if('status' in res.data){
              this.$message.error(res.data.description)
            }
            else{
              this.$message.error(res.data.exceptionMessage)
            }
          })
        }
      }
    },
    fetch(content){
      let id = content.match(/\d+/g)
      this.talkTo = id
      if(content.indexOf('usr') !== -1){
        this.talkToWho = 'usr'
        this.axios.get('http://localhost/users/communication/'+this.defaultNum+'/'+this.currentPage+'/'+id).then((res) => {
          // console.log(res.data)
          if('status' in res.data && res.data.status){
            this.msgList = res.data.content.messages
            this.messageBy = res.data.content.messageBy
            this.myName = res.data.content.myName
          }
        })
      }
      else if(content.indexOf('ano') !== -1){
        this.talkToWho = 'ano'
        this.axios.get('http://localhost/anonymous/communication/'+this.defaultNum+'/'+this.currentPage+'/'+id).then((res) => {
          // console.log(res.data)
          if('status' in res.data && res.data.status){
            this.msgList = res.data.content.messages
            this.messageBy = '匿名'+res.data.content.anoId
            this.myName = res.data.content.myName
          }
        })
      }
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="title">
      <h2>留言</h2>     
      <a href="/Markdown" target="_blank">Markdown?</a>
    </div>
    <div class="message-container">
      <TextHandler @submit="submit" @fetch="fetch"
        :default=this.default
        :inputDefault=true
        :isMe=isMe
        :selectorDisplay=true
        :msgUsrList=this.msgUsrList
        :usrList=this.usrList
        :anoList=this.anoList
      />
      <!-- <div class="btn">
        <button class="submit">提交</button>
        <button class="reset">清空</button>
      </div> -->
      <div class="cut"></div>
    </div>

    <div class="communication-container" v-for="msg in msgList" :key="msg.id">
      <SingleMessage
        :username="msg.message ? messageBy : myName"
        :time="msg.date"
        :content="msg.content"
        :default="default"
        :msgId="msg.id"
      />
    </div>

    <div class="bottom"></div>
  </div>
</template>

<style lang="less">
.background.message-mode{
  // min-height: 95vh;
  min-height: 919px;
  bottom: initial;
  &:before{
    content: '';
    right: 50%-50rem;
    width: 100rem;
    height: 100rem;
  }
}
.container{
  z-index: 10;
  display: flex;
  flex-direction: column;
  position: relative;
  width: 80%;
  min-height: 80vh;
  /* margin-left: auto;
  margin-right: auto; */
  // top: 7.5%;
  margin: 3rem auto;
  background-color: #FFF;
  box-shadow: 1px 1px 10px rgb(0 0 0 / 30%);
  border-radius: 1rem;
  .title{
    position: relative;
    padding: 2rem 2rem;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    a{
      margin-top: 5px;
      margin-left: 1rem;
    }
  }
  .message-container{
    .cut{
      margin: .5rem 2rem;
      height: 2px;
      border-radius: 50%;
      background-color: rgb(0 0 0 / 50%);
    }
  }
  .communication-container{
    display: flex;
    flex-direction: column;
    margin: 0 2rem;
  }
  .bottom{
    margin: 1rem;
  }
}
</style>