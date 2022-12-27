<template>
    <div class="single">
        <div class="userinfo">
            <div class="user">
            <p class="username">{{ username }}</p>
            <p class="time">{{ time }}</p>
            </div>
            <span class="menu">
            <svg t="1672044394941" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2693" width="200" height="200"><path d="M188.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2694"></path><path d="M516.2 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2695"></path><path d="M836.5 507.2m-91.5 0a91.5 91.5 0 1 0 183 0 91.5 91.5 0 1 0-183 0Z" fill="#58595B" p-id="2696"></path></svg>
            </span>
        </div>
        <div class="content">
            <Displayer :text="content"/>
        </div>
    </div>
</template>
<style lang="less">
.userinfo{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    .user{
        text-align: left;
        .username{
            font-size: 1.1rem;
            font-weight: bold;
        }
        .time{
            color: rgb(0 0 0 / 30%);
            font-size: 0.9rem;
        }
    }
    .menu{
        svg{
            width: 1.3rem;
            height: 1.3rem;
        }
    }
}
</style>
<script>
import Displayer from '@/components/message/Displayer.vue'

export default{
    name: 'SingleComment',
    props:{
        username: String,
        time: String,
        content: String
    },
    components:{
        Displayer
    }
}
</script>