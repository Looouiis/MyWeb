<template>
  <!-- <div class="background">
    <div class="form-background">
      <div class="LogInandRegister"> -->

        <form action="">
          <div class="title">LogIn</div>
          <div class="input-text">
            <a>Username</a><input type="text" v-model="user.username"/>
          </div>
          <div class="input-text">
            <a>Password</a><input id="pwd" type="password" v-model="user.password"/>
          </div>
          <button class="btnLogin" @click="login" type="button">登录</button>
          <!-- <RouterLink class="router-to-register" to='/Register'>注册账号</RouterLink> -->
        </form>
        

      <!-- </div>
    </div>
  </div> -->
    
</template>
  
<style lang="less">
.btnLogin{
  width: 150px;
  height: 49px;
  border: none;
  outline: none;
  border-radius: 25px;
  margin: 17px 0;
  cursor: pointer;
  background-color: #0AD182;
  font-size: 1.5rem;
  &:hover{
    background-color: #1ABBFE;
  }
}
</style>

<script>
import axios from 'axios'


  export default {
    name: 'Login',
    data(){
        return{
            user:{
                username: '',
                password: ''
            }
        }
    },
    methods:{
        login(){
            axios.post('http://localhost:722/users',this.user).then((res)=>{
                if(res.data.status){
                    localStorage.setItem("token", res.data.token)
                    localStorage.setItem("refreshToken", res.data.refreshToken)
                    this.$message.success(res.data.description)
                    this.$emit('reload')
                    this.$router.push('/')
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