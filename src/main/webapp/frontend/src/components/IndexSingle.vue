<template>
    <!-- <div class="img"></div> -->
    <img :src="imgPath" alt="">
    <div class="heading">
        <h1>{{ header }}</h1>
    </div>
    <div class="text">
        <h1 v-for="content in contents">{{ content }}</h1>
    </div>
</template>
<style lang="less">
.home{
    div{
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
        font-style: 30px;
        letter-spacing: 2px;
    }
    img{
        // background-image: url(`${imgPath}`);
        transform: translateZ(-1px) scale(1.6);
        background-size: cover;
        height: 100%;
        bottom: 0px;
        z-index: -1;
    }
    .heading{
        z-index: 0;
        transform: translateY(-30vh) translateZ(1px);
        color: #FFF;
        font-size: 30px;
    }
    .text{
        z-index: 2;
        height: 50vh;
        background-color: #FFF;
        display: flex;
        flex-direction: column;
        padding: 0 5rem;
        h1{
            color: #000;
        }
    }
}
@media (max-width: 870px) {
  .home{
    .heading{
      font-size: 1rem;
    }
    .text{
        font-size: .7rem;
    }
  }  
}
</style>
<script>
export default{
    name: 'IndexSingle',
    props:{
        imgPath: String,
        header: String,
        contents: Array
    }
}
</script>