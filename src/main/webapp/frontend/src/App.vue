<template>
    <div id="header">
      
    <button @click="clear" style="left: 0px;">重置</button>
      <nav>
    <p>{{ signUpMode }}</p>
        <router-link to="/">Home</router-link> |
        <router-link to="/About">About</router-link> |
        <router-link to="/LoginorRegister">Login</router-link> |
        <!-- <router-link to="/Register">Register</router-link> | -->
        <router-link to="/Update">Update</router-link>
      </nav>
    </div>
  <div :class="signUpMode">
    <router-view @response="(msg) => signUpMode = msg"/>
  </div>

</template>

<style lang="less">
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.background{
  position: relative;
  width: 100%;
  height: 95vh;
  background-color: #fff;
  overflow: hidden;
  &:before{
    content: '1';
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
    top: 5%;
    right: 5%;
    transform: translateY(-50%);
    z-index: 6;
    transition: 1.8s ease-in-out;
  }
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#header{
  display: flex;
  height: 5vh;
  background-color: rgba(255,255,255,0.7);
  align-items: center;
  justify-content: center;
  border-bottom: solid 1px rgba(0,0,0,0.3);
}

nav {
  // padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
<script>
  export default{
    data(){
        return{
            signUpMode: 'background log-in-mode',
            flag: true
        }
    },
    methods:{
        clear(){
          if(this.flag){
            this.signUpMode = 'background'
            this.flag = false
          }else{
            this.signUpMode = 'background log-in-mode'
            this.flag = true
          }
        }
    }
  }
</script>