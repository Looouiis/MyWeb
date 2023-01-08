<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <iframe class="kurt" src="//player.bilibili.com/player.html?aid=413628523&bvid=BV1sV411k749&cid=243827381&page=33&danmaku=0" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe>
    <div class="img" :style="backgroundImg"></div>
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
      :contents="['仅有的功能','用户/匿名留言','公告']"
    />
    <IndexSingle
      :imgPath="bird"
      header="对网站最满意的地方"
      :contents="['开发正好用时一坤（时间单位）']"
    />
    <IndexSingle
      :imgPath="point"
      header="你还在看什么"
      :contents="['这页没东西了','去给我留言吧，或者可以去康康我去年写的Maiden Work！']"
    />
  </div>
</template>

<script>
// @ is an alias to /src
import IndexSingle from '@/components/IndexSingle.vue'
import png1 from "../assets/ikun.png"
import png2 from "../assets/cat.jpg"
import squirrel from "../assets/squirrel.jpg"
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
      squirrel: squirrel,
      bird: bird,
      point: point,
      backgroundImg:{
        backgroundImage : 'url(' + png2 + ')'
      }
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
  .kurt{
    position: absolute;
    z-index: 1;
    left: 3rem;
    top: 1rem;
    width: 17vh;
    height: 9vh;
    transition: .5s 0s ease-in-out;
    &:hover{
      width: 30rem;
      height: 23rem;
    }
  }
  iframe{
    position: absolute;
  }
  .img{
        // background-image: url('../assets/cat.jpg');
        transform: translateZ(-1px) scale(1.6);
        background-size: cover;
        height: 100%;
        bottom: 0px;
        z-index: -1;
    }
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
