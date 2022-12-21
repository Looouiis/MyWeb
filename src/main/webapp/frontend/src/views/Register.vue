<template>
    <button @click="deleteToken">点我清空token</button>
  </template>
  
  <script>
  
  export default {
    name: 'Register',
    methods:{
        deleteToken(){
            localStorage.removeItem('token')
            console.log(localStorage.getItem('token'))
        }
    }
  }
  </script>
  