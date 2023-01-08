<template>
    <div class="single-message">
        <div class="single">
          <div class="userinfo">
            <div class="user">
              <p class="username">{{ username }}</p>
              <p class="time">{{ time }}</p>
            </div>
            <span class="menu">
              <svg t="1672044394941" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2693" width="200" height="200"><path d="M188.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2694"></path><path d="M516.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2695"></path><path d="M836.5 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2696"></path></svg>
            </span>
          </div>
          <div class="content">
            <Displayer :text="content"/>
          </div>
        </div>
        <div class="comments">
            <div v-for="com in comList">
                <SingleComment
                    :username="com.message ? messageBy : myName"
                    :time="com.date"
                    :content="com.content"
                />
            </div>
        </div>
        <button class="commentBtn" @click="swh">回复</button>
        <Transition>
            <div class="comment-commit" v-if="flag">
                <TextHandler @submit="submit"
                    :default = this.default
                    :inputDefault = false
                />
                
            </div>
        </Transition>
    </div>
</template>
<script>
import TextHandler from '@/components/TextHandler.vue'
import SingleComment from '@/components/message/SingleComment.vue'
import Displayer from '@/components/message/Displayer.vue'

export default{
    name: 'SingleMessage',
    components:{
        SingleComment,
        TextHandler,
        Displayer
    },
    props:{
        username: String,
        time: String,
        content: String,
        default: String,
        msgId: Number
    },
    data(){
        return{
            currentPage: 1,
            defaultNum: 3,
            comList: Object,
            messageBy: String,
            myName: String,
            flag: false
        }
    },
    mounted(){
        if(this.default.indexOf('usr') !== -1){
            this.axios.get('http://localhost:722/users/comment/'+this.msgId).then((res) => {
              if('status' in res.data && res.data.status){
                this.comList = res.data.content.comments
                this.messageBy = res.data.content.messageBy
                this.myName = res.data.content.myName
              }
            })
        }
        else if(this.default.indexOf('ano') !== -1){
            this.axios.get('http://localhost:722/anonymous/comment/'+this.msgId).then((res) => {
              if('status' in res.data && res.data.status){
                this.comList = res.data.content.comments
                this.messageBy = '匿名'+res.data.content.messageBy
                this.myName = res.data.content.myName
              }
            })
        }
    },
    methods:{
        swh(){
            this.flag = !this.flag
        },
        submit(content){
            if(this.default.indexOf('usr') !== -1){
                this.axios({
                method: 'POST',
                headers: { 'content-type': 'application/x-www-form-urlencoded' },
                data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
                url: 'http://localhost:722/users/comment/'+this.msgId
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
                // .post('http://localhost:722/users/communication','content:'+content)
            }
            else if(this.default.indexOf('ano') !== -1){
                this.axios({
                method: 'POST',
                headers: { 'content-type': 'application/x-www-form-urlencoded' },
                data: 'content='+content,   // 用 qs 将js对象转换为字符串 'name=edward&age=25'
                url: 'http://localhost:722/anonymous/comment/'+this.msgId
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
<style lang="less">
.single-message{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    .single{
        padding: .5rem 0;
    }
    .userinfo{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        .user{
            text-align: left;
        .username{
            font-size: 1.1rem;
            font-weight: bold;
        }
        .time{
            color: rgb(0 0 0 / 30%);
            font-size: 0.9rem;
        }
    }
    .menu{
        svg{
            width: 1.3rem;
            height: 1.3rem;
        }
    }
}
.content{
    p{
        text-align: left;
    }
}
    // .dotcut{
    //   // border-radius: 50%;
    //   height: 0px;
    
    //   border: rgb(0 0 0 / 50%) dotted;
    // }
    .comments{
        margin: .5rem 0;
        margin-left: 1rem;
        // border-left: 1rem;
        border-left: solid .2rem rgb(0 0 0 / 30%);
        padding: 0 .5rem;
    }
    .commentBtn{
        margin: .3rem 0;
        width: 3.5rem;
        height: 1.5rem;
        border: none;
        outline: none;
        border-radius: 5px;
        cursor: pointer;
    }
}
.comment-commit{
    transition: 1s 0.8s ease-in-out;
    
}
.v-enter-from,.v-leave-to{
    opacity: 0;
    transform: translateY(800px);
}
.v-enter-active,.v-leave-active{
    transition: all 0.5S ease
}
</style>
