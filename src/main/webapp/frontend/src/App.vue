<template>
    <div id="header">
      <nav>
        <!-- <p style="color: white;">{{ signUpMode }}</p> -->
        <a @click="toGithub" herf="https://github.com/looouiis" target="_blank">Looouiiis</a>
        <router-link to="/">首页</router-link>
        <router-link to="/Manage" v-if="user.isMe">管理</router-link>
        <router-link to="/Message">留言</router-link>
        <router-link to="/Announce">公告</router-link>
        <a v-if="this.isLogin()" @click="logOut">登出</a>
        <router-link to="/LoginorRegister" v-else>登录</router-link>
        <router-link to="/Update">账号</router-link>
        <!-- <router-link to="/MadrenWork">Madren Work</router-link> -->
        <a @click="toMaiden">Maiden Work</a>
      </nav>
      <p class="welcome">欢迎您{{ user.username === undefined ? ',未登录的用户' : user.username }}</p>
    </div>
  <div :class="signUpMode">
    <div class="msg">{{ msg }}</div>
    <router-view @response="(msg) => signUpMode = msg" @reload="load()" :default=this.default :userId=this.user.id :isMe=this.user.isMe />
  </div>

</template>

<style lang="less">
@font-face {
  font-family: 'iconfont';  /* Project id 2978077 */
  src: url('//at.alicdn.com/t/c/font_2978077_z1lp4slf1sf.woff2?t=1671850396092') format('woff2'),
       url('//at.alicdn.com/t/c/font_2978077_z1lp4slf1sf.woff?t=1671850396092') format('woff'),
       url('//at.alicdn.com/t/c/font_2978077_z1lp4slf1sf.ttf?t=1671850396092') format('truetype');
}
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.background{
  position: absolute;
  width: 100%;
  // min-height: 95vh;
  top: 50px;
  bottom: 0px;
  background-color: #fff;
  overflow: hidden;
  &.ano{
    &:before{
      content: '';
      background-color: #1ABBFE;
    }
    .msg{
      display: none;
    }
  }
  &.noMsg{
    .msg{
      display: none;
    }
  }
  &:before{
    content: '\e630';
    font-family: 'iconfont';
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.5rem;
    color: #fff;
    position: absolute;
    width: 2rem;
    height: 2rem;
    border-radius: 50%;
    background:#0AD182;
    top: 3rem;
    right: 3rem;
    transform: translateY(-50%);
    z-index: 6;
    transition: 1.8s ease-in-out;
  }
}
form{
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 5rem;
  overflow: hidden;
  transition: 0.2s 0.7s ease-in-out;
  .title{
    font-weight: bold;
    font-size: 2.2rem;
    margin-bottom: .5rem;
  }
  .input-text{
    align-items: center;
    max-width: 380px;
    width: 100%;
    height: 55px;
    margin: .3rem 0;
    padding: 0.3rem;
    display: grid;
    grid-template-columns: 2fr 8fr;
    gap: .1rem;
    // grid-template-columns: 20% 80%;
    a{
      font-size: 1.0rem;
      font-weight:bold;
    }
    input{
      align-items: center;
      height: 50px;
      background-color: rgba(0,0,0,0.1);
      // background-color: green;
      border-radius: 25px;
      width: 100%;
      // background-color: red;
      // background: none;
      outline: none;
      border: none;
      line-height: 1;
      font-weight: 600;
      font-size: 1.5rem;
      padding: 0 1rem 0 1rem;
      color: #000000;
      &::placeholder{
        color: rgba(0,0,0,0.3);
        font-size: 1.2rem;
      }
    }
  }
  .gender{
    display: flex;
    justify-content: space-evenly;
    div{
      display: flex;
      align-items: center;
      padding: 0.5rem 2rem;
    }
    input{
      width: 1rem;
      height: 2rem;
    }
    svg{
      width: 2rem;
      height: 2rem;
    }
  }
}
.msg{
    background: red;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1rem;
    color: #fff;
    position: absolute;
    width: 1.5rem;
    height: 1.5rem;
    border-radius: 50%;
    top: 1.9rem;
    right: 2.5rem;
    transform: translateY(-50%);
    z-index: 6;
  }
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#header{
  position: relative;
  display: flex;
  height: 50px;
  background-color: #333333;
  align-items: center;
  justify-content: space-evenly;
  .welcome{
    display: flex;
    position: absolute;
    right: 0;
    color: #FFF;
    position: relative;
    right: 0%;
  }
}

nav {
  &.router{
    padding: 0 12rem;
  }
  a {
    text-decoration: none;
    padding: 0 2rem;
    color: rgba(255,255,255,0.5);

    &.router-link-exact-active {
      color: #FFF;
    }
  }
}
</style>
<script>
  export default{
    data(){
        return{
            default: '',
            signUpMode: '',
            user:'',
            msg: 0,
            msgDisplay: 'none'
        }
    },
    methods:{
      load(){
        this.default = ''
        let token = localStorage.getItem('token')
        if(token !== null && token !== ''){
          this.axios.get('http://localhost:722/users/token').then((res) => {
            this.user = res.data.content
            // console.log(this.user)
            // console.log(res.data)
            if(res.data.content.isMe){
              this.axios.get('http://localhost:722/myUnread').then((res) => {
                // console.log(res)
                if(res.data.content != null){
                  for (let index = 0; index < res.data.content.length; index++) {
                    this.msg += res.data.content[index].num
                  }
                }
                if(this.msg === 0){
                  this.default += 'noMsg '
                }
              })
            }
            else{
              this.axios.get('http://localhost:722/users/reply').then((res) => {
                // console.log(res)
                if(res.data.content != null)
                  this.msg += res.data.content.num
                else
                  this.msg = 0
                if(this.msg === 0){
                  this.default += 'noMsg '
                }
              })
            }
          })
          this.default += 'background usr '
        }
        else{
          this.axios.get('http://localhost:722/anonymous/reply').then((res) => {
            // console.log(res)
            if(res.data.content != null)
              this.msg += res.data.content.num
            else
              this.msg = 0
              if(this.msg === 0){
                this.default += 'noMsg '
              }
          })
          this.default += 'background ano '
        }
        this.signUpMode = this.default
      },
      toGithub(){
        window.open('https://github.com/looouiis')
      },
      toMaiden(){
        window.open('http://maiden.looouiiis.me')
      },
      isMobile() {
        let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
        return flag;
      },
      isLogin(){
        let flag = (localStorage.getItem('token') !== null && localStorage.getItem('token') !== '')
        return flag
      },
      logOut(){
        localStorage.removeItem('token');
        localStorage.removeItem('refreshToken')
        alert('已退出登录')
        this.user = ''
        this.load()
      }
    },
    mounted(){
      if (this.isMobile()) {
        alert("手机端尚未适配完成，请使用电脑访问");
      }
      this.load()
    }
  }
  
</script>