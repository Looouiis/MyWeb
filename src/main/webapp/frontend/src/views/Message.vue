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
    userId: Number
  },
  data(){
    return{
      currentPage: 0,
      defaultNum: 3,
      msgList: Object,
      // msgList: {
      //   content: String,
      //   date: String,
      //   id: Number,
      //   local: Boolean,
      //   message: Boolean,
      //   userId: Number
      // },
      messageBy: String,
      myName: String
    }
  },
  mounted(){
    this.$emit('response', this.default + 'message-mode noMsg')
    if(this.default.indexOf('usr') !== -1){
      this.axios.get('http://localhost:801/users/communication').then((res) => {
        console.log(res.data)
        if('status' in res.data && res.data.status){
          this.msgList = res.data.content.messages
          this.messageBy = res.data.content.messageBy
          this.myName = res.data.content.myName
        }
      })
    }
    else if(this.default.indexOf('ano') !== -1){
      this.axios.get('http://localhost:801/anonymous/communication').then((res) => {
        console.log(res.data)
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
      if(this.default.indexOf('usr') !== -1){
        this.axios({
          method: 'POST',
          headers: { 'content-type': 'application/x-www-form-urlencoded' },
          data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
          url: 'http://localhost:801/users/communication'
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
        // .post('http://localhost:801/users/communication','content:'+content)
      }
      else if(this.default.indexOf('ano') !== -1){
        this.axios({
          method: 'POST',
          headers: { 'content-type': 'application/x-www-form-urlencoded' },
          data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
          url: 'http://localhost:801/anonymous/communication'
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
  }
}
</script>

<template>
  <div class="container">
    <h2 class="title">留言</h2>
    <div class="message-container">
      <TextHandler @submit="submit"
        :default=this.default
        :inputDefault=true
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
  }
  .message-container{
    // .editor{
    //   height: 30vh;
    //   margin: 0 2rem;
    //   border: solid .2rem rgb(0 0 0 / 30%);
    //   border-radius: 1rem;
    // }
    // .btn{
    //   display: flex;
    //   justify-content: flex-start;
    //   padding: 1rem 2rem;
    //   padding-top: 0;
    //   padding-bottom: 0;
    //   button{
    //     margin: .3rem .3rem;
    //     width: 3.5rem;
    //     height: 1.5rem;
    //     border: none;
    //     outline: none;
    //     border-radius: 5px;
    //     cursor: pointer;
    //     &.submit{
    //       background-color: #27516e;
    //       color: #FFF;
    //       &:hover{
    //         background-color: #7c9388;
    //         color: #000;
    //       }
    //     }
    //     &.reset{
    //       background-color: #FFF;
    //       border: #000 solid 1px;
    //     }
    //   }
    // }
    .cut{
      margin: .5rem 2rem;
      // padding: 1rem;
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