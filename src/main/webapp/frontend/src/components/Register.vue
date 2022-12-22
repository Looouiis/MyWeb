<!-- <template>
  <form action="">
      <input type="text" v-model="user.username" placeholder="用户名"/>
      <input type="password" v-model="user.password" placeholder="密码"/>
      <input type="password" v-model="con" placeholder="确认密码"/>
      <input type="radio" name="gender" value="0" v-model="user.gender">♂
      <input type="radio" name="gender" value="1" v-model="user.gender">♀
      <button @click="register" type="button">注册</button>
  </form>
  <p>{{ user }}</p>
</template> -->

<template>
  <!-- <div class="background">
    <div class="form-background">
      <div class="LogInandRegister"> -->

        <form action="">
          <div class="title">Register</div>
          <div class="input-text">
            <a>Username</a><input type="text" v-model="user.username"/>
          </div>
          <div class="input-text">
            <a>Password</a><input type="password" v-model="user.password"/>
          </div>
          <div class="input-text">
            <a>Confirm</a><input type="password" v-model="con"/>
          </div>
          <button class="btnRegister" @click="register" type="button">注册</button>
        </form>

      <!-- </div>
    </div>
  </div> -->
    
</template>

<style lang="less">
// .background{
//   position: relative;
//   width: 100%;
//   min-height: 100vh;
//   background-color: #fff;
//   overflow: hidden;
// }
// .form-background{
//   position: absolute;
//   width: 100%;
//   height: 100%;
//   top: 0;
//   left: 0;
// }
// form{
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   flex-direction: column;
// }
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
  }
}
.btnRegister{
  width: 150px;
  height: 49px;
  border: none;
  outline: none;
  border-radius: 25px;
  margin: 17px 0;
  cursor: pointer;
  background-color: #1ABBFE;
  font-size: 1.5rem;
  &:hover{
    background-color: #0AD182;
  }
}
</style>

<script>
import axios from 'axios'


export default {
  name: 'Login',
  data(){
      return{
        con: '',
        user:{
              username: '',
              password: '',
              gender: 0
        }
      }
  },
  methods:{
      register(){
        if(this.user.password === this.con){
          axios.put("http://localhost:801/users",this.user).then((res)=>{
              if(res.data.status){
                  localStorage.setItem("token", res.data.token)
                  localStorage.setItem("refreshToken", res.data.refreshToken)
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
        else{
          this.$message.error('两次输入密码不一致')
        }
      }
  }
}
</script>

 <!-- <script>
export default {
data() {
  return {
    text: {
      data: '',
      why: ''
    }
  }
},
methods: {
  onInput(e) {
    this.text.data = e.target.value
  },
  onInput2(e){
      this.text.why = e.target.value
  }
}
}
</script>

<template>
  <form>
<input :value="text.data" @input="onInput" placeholder="Type here">
<input :value="text.why" @input="onInput2" placeholder="Type here">
  </form>
<p>{{ text }}</p>
</template> -->