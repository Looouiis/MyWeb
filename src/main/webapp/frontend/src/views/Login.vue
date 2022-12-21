<template>
    <form action="login">
        <input type="text" v-model="user.username" />
        <input type="password" v-model="user.password" />
        <button @click="login" type="button">登录</button>
    </form>
    <p>{{ user }}</p>
</template>
  
<script>
import axios from 'axios';

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
            axios.post("http://localhost:801/users",this.user).then((res)=>{
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