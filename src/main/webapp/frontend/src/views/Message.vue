<!-- <template>
  
</template>
<script>
export default {
  name: 'Message'
  
}
</script> -->
<script>
import TextHandler from '@/components/TextHandler.vue'

export default {
  name: 'Message',
  components:{
    TextHandler
  },
  props:{
    default: String
  },
  data(){
    return{
      input: ''
    }
  },
  mounted(){
    this.$emit('response', this.default + 'message-mode noMsg')
  },
  methods: {
    
  }
}
</script>

<template>
  <div class="container">
    <h2>留言</h2>
    <div class="message-container">
      <TextHandler :default=this.default />
      <div class="btn">
        <button class="submit">提交</button>
        <button class="reset">清空</button>
      </div>
      <div class="cut"></div>
    </div>
    <div class="communication-container">
      <div class="single">
        <div class="userinfo">
          <div class="user">
            <p class="username">Looouiiis</p>
            <p class="time">2022-12-26 12:00:00</p>
          </div>
          <span class="menu">
            <svg t="1672044394941" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2693" width="200" height="200"><path d="M188.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2694"></path><path d="M516.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2695"></path><path d="M836.5 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2696"></path></svg>
          </span>
        </div>
        <div class="content">
          <p>测试测试测试测试测试测试测试测试测试测试测试测试</p>
        </div>
      </div>
      
      <div class="dotcut"></div>

      <div class="single">
        <div class="userinfo">
          <div class="user">
            <p class="username">Looouiiis</p>
            <p class="time">2022-12-26 12:00:00</p>
          </div>
          <span class="menu">
            <svg t="1672044394941" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2693" width="200" height="200"><path d="M188.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2694"></path><path d="M516.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2695"></path><path d="M836.5 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2696"></path></svg>
          </span>
        </div>
        <div class="content">
          <p>测试测试测试测试测试测试测试测试测试测试测试测试</p>
        </div>
      </div>

    </div>
  </div>
</template>

<style lang="less">
.background.message-mode{
  min-height: 95vh;
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
  min-height: 85%;
  /* margin-left: auto;
  margin-right: auto; */
  top: 7.5%;
  margin: auto;
  background-color: #FFF;
  box-shadow: 1px 1px 10px rgb(0 0 0 / 30%);
  border-radius: 1rem;
  h2{
    position: relative;
    padding: 2rem 2rem;
    display: flex;
    justify-content: flex-start;
  }
  .message-container{
    .editor{
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
    .userinfo{
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      .user{
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
        text-align: right;
      }
    }
    .dotcut{
      // border-radius: 50%;
      height: 0px;
      
      border: rgb(0 0 0 / 50%) dotted;
    }
  }
}
</style>