<template>
    <div id="header">
      <nav>
        <router-link to="/">Home</router-link> |
        <router-link to="/About">About</router-link> |
        <router-link to="/LoginorRegister">Login</router-link> |
        <!-- <router-link to="/Register">Register</router-link> | -->
        <router-link to="/Update">Update</router-link>
      </nav>
    </div>
  <router-view/>
</template>

<style lang="less">
*{
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#header{
  height: 50px;
  background-color: #42b983;
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
