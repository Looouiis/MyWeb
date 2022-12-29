<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <img :src="topPng">
    <div class="top">
        <h1>LooouiiisWeb</h1>
    </div>
    <div class="text">
        <h1>v1.0发布于2023年1月1日</h1>
    </div>
    <IndexSingle
      :imgPath="ikun"
      header="个人介绍"
      :contents="['Looouiiis','男','活的']"
    />
    <IndexSingle
      :imgPath="squirrel"
      header="网页介绍"
      :contents="['网页后端采用SSM三大框架在SpringBoot上开发，前端采用VUE。为了赶在2023来临之时发布，本次开发时间较短，功能也很简陋（好像就只有留言、公告两个实用功能）。会在后续持续完善、补充的']"
    />
    <IndexSingle
      :imgPath="bird"
      header="对网站最满意的地方"
      :contents="['留言、公告都是手搓的，没有用Wordpress等（难怪这么简陋）','登录模块赶了趟潮使用了双token机制']"
    />
    <IndexSingle
      :imgPath="point"
      header="你还在看什么"
      :contents="['这页没东西了','去给我留言吧，或者可以去康康我去年写的Madren Work！']"
    />
  </div>
</template>

<script>
// @ is an alias to /src
import IndexSingle from '@/components/IndexSingle.vue'
import png1 from "../assets/ikun.png"
import png2 from "../assets/cat.jpg"
import png3 from "../assets/mybackground.png"
import squirrel from "../assets/squirrel.jpg"
import top from "../assets/top.png"
import bird from "../assets/bird.jpg"
import point from "../assets/point.jpeg"

export default {
  name: 'HomeView',
  components: {
    IndexSingle
  },
  props:{
    default: String
  },
  created(){
    this.$emit('response', this.default+'home-mode')
  },
  data(){
    return{
      topPng: png2,
      ikun: png1,
      cat: png2,
      mybackground: png3,
      squirrel: squirrel,
      top: top,
      bird: bird,
      point: point
      // contents:{
      //   'Looouiiis,男，活的'
      // }
    }
  }
}
</script>
<style lang="less">
.home{
  bottom: 0px;
  height: 100%;
  overflow-x: hidden;
  perspective: 3px;
  .top{
    z-index: 0;
    transform: translateY(-35vh) translateZ(1px);
    color: #FFF;
    font-size: 30px;
    h1{
      background-color: rgb(0 0 0 / 60%);
      padding: 1rem;
      border-radius: 1rem;
    }
  }
}
@media (max-width: 870px) {
  .home{
    .top{
      font-size: 1rem;
    }
  }  
}
</style>
