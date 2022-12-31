<!-- <template>

</template>

<script>
export default {
  name: "Announce"
}
</script>

<style lang="less">

</style> -->
<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import TextHandler from '@/components/TextHandler.vue'
import SingleAnnounce from '@/components/SingleAnnounce.vue'

export default {
  name: 'Announce',
  components:{
    TextHandler,
    SingleAnnounce
  },
  props:{
    default: String,
    userId: Number,
    isMe: Boolean
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
    this.axios.get(location.origin+'/announce/0/-1').then((res) => {
      // console.log(res.data)
      if('status' in res.data && res.data.status){
        this.msgList = res.data.content
        this.messageBy = ''
        this.myName = res.data.description
      }
    })
    this.$emit('response', this.default+'announce-mode')
  },
  methods: {
    submit(content){
      if(this.isMe){
        this.axios({
          method: 'POST',
          headers: { 'content-type': 'application/x-www-form-urlencoded' },
          data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
          url: location.origin+'/announce'
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
      }
    }
  }
}
</script>

<template>
  <div class="container">
    <h2 class="title">公告</h2>
    <div class="submit-container" v-if="isMe">
      <TextHandler @submit="submit"
        :default=this.default
        :inputDefault=true
      />
      <div class="cut"></div>
    </div>

    <div class="announce-container" v-for="msg in msgList" :key="msg.id">
      <SingleAnnounce
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
.background.announce-mode{
  // min-height: 95vh;
  min-height: 919px;
  bottom: initial;
  &:before{
    content: '';
    right: 50%;
    width: 100rem;
    height: 100rem;
    top: 90rem;
  }
}
.container{
  z-index: 10;
  display: flex;
  flex-direction: column;
  position: relative;
  width: 80%;
  min-height: 80vh;
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
  .submit-container{
    .cut{
      margin: .5rem 2rem;
      height: 2px;
      border-radius: 50%;
      background-color: rgb(0 0 0 / 50%);
    }
  }
  .announce-container{
    display: flex;
    flex-direction: column;
    margin: 0 2rem;
  }
  .bottom{
    margin: 1rem;
  }
}
</style>