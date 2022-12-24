<template>
        <div class="form-background">
            <div class="LogInandRegister">

        
                <Login class="form" id="login"/>
                <Register class="form" id="register"/>
  
            <!-- <form action="">
                <div class="title">LogIn</div>
                <div class="input-text">
                <a>Username</a><input type="text" v-model="user.username"/>
                </div>
                <div class="input-text">
                <a>Password</a><input id="pwd" type="password" v-model="user.password"/>
                </div>
                <button class="btn" @click="login" type="button">登录</button>
            </form>
            <RouterLink class="router-to-register" to='/Register'>注册账号</RouterLink> -->
  
        </div>
        <div class="tips">
            <div class="tip left">
                <div class="content">
                    <h3>想注册辣？😍</h3>
                    <p>来注册吧，注册后与我的匿名留言将会转移到您的新账号下，此后我将通过这个账号和您交流</p>
                    <button class="btnRegister trans" @click="add">去注册</button>
                </div>
            </div>
            <div class="tip right">
                <div class="content">
                    <h3>已有账号辣？</h3>
                    <p>回去登录吧</p>
                    <button class="btnLogin trans" @click="remove">去登录</button>
                </div>
            </div>
        </div>

    </div>
      
</template>

<style lang="less">
// .background{
//   position: relative;
//   width: 100%;
//   height: 95vh;
//   background-color: #fff;
//   overflow: hidden;
//   &:before{
//     content: '';
//     position: absolute;
//     width: 2000px;
//     height: 2000px;
//     border-radius: 50%;
//     background:#0AD182;
//     top: 15%;
//     right: 39%;
//     transform: translateY(-50%);
//     z-index: 6;
//     transition: 1.8s ease-in-out;
//   }
// }
.background.register-mode{
    &:before{
    content: '';
    width: 2000px;
    height: 2000px;
    transform: translate(100%, -50%);
    right: 50%;
    top: 95%;
    background: #1ABBFE;
  }
  .msg{
    display: none;
  }
  .left{
    pointer-events: none;
    .content{
        transform: translateX(-800px);
    }
  }
  .right{
    pointer-events: all;
    .content{
        transform: translateX(0px);
    }
  }
  .LogInandRegister{
    left: 30%;
  }
  .form{
    &#login{
        z-index: 1;
        opacity: 0;
    }
    &#register{
        z-index: 2;
        opacity: 1;
    }
  }
}
.background.log-in-mode{
    &:before{
    content: '';
    width: 2000px;
    height: 2000px;
    transform: translateY(-50%);
    top: 15%;
    right: 39%;
    background: #0AD182;
  }
  .msg{
    display: none;
  }
  .left{
    pointer-events: all;
    .content{
        transform: translateX(0px);
    }
  }
  .right{
    pointer-events: none;
    .content{
        transform: translateX(800px);
    }
  }
  .LogInandRegister{
    left: 75%;
  }
  .form{
    &#login{
        z-index: 2;
        opacity: 1;
    }
    &#register{
        z-index: 1;
        opacity: 0;
    }
  }
}
.form-background{
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}
.LogInandRegister{
    position: absolute;
    top: 50%;
    left: 75%;
    transform: translate(-50%, -50%);
    width: 50%;
    display: grid;
    grid-template-columns: 1fr;
    z-index: 5;
    transition: 1s 0.7s ease-in-out;
}
.tips{
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    display: grid;
    grid-template-columns: repeat(2,1fr);
}
.tip{
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    justify-content: space-around;
    text-align: center;
    z-index: 7;
    .content{
        transition: .9s .6s ease-in-out;
    }
    h3{
        font-weight: 666;
        line-height: 1;
        font-size: 1.5rem;
    }
}
.left{
    pointer-events: all;
    padding: 0rem 17% 2rem 12%;
    .content{
        transition: .9s .6s ease-in-out;
    }
}
.right{
    pointer-events: none;
    padding: 3rem 22% 2rem 17%;
    .content{
        transform: translateX(800px);
    }
}
.form{
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 5rem;
    overflow: hidden;
    grid-column: 1 / 2;
    grid-row: 1 / 2;
    transition: 0.2s 0.7s ease-in-out;
    &#login{
        z-index: 2;
    }
    &#register{
        z-index: 1;
        opacity: 0;
    }
}
@media (max-width: 870px){
    .background{
        // min-height: 800px;
        height: 95vh;
        &.log-in-mode, &.register-mode{
            .msg{
                display: none;
            }
            .LogInandRegister{
                left: 50%;
                width: 100%;
                top: 95%;
                transform: translate(-50%, -100%);
                transition: 1s 0.8s ease-in-out;
            }
            .tip{
                .left{
                    padding-right: 20%;
                }
                .right{
                    padding-left: 30%;
                }
            }
        }
        &.register-mode{
            &:before{
                top: 190%;
                right: 260%;
            }
            .left .content{
                transform: translateY(-300px);
            }
            .right .content{
                transform: translateY(0px);
            }
            .LogInandRegister{
                top: 5%;
                transform: translate(-50%, 0);
            }
        }
        &.log-in-mode{
            &:before{
                width: 1500px;
                height: 1500px;
                top: -55%;
                right: -90%;
            }
            .left .content{
                transform: translateY(0px);
            }
            .right .content{
                transform: translateY(300px);
            }
            .LogInandRegister{
                top: 95%;
                transform: translate(-50%, -100%);
            }
        }
    }
    .LogInandRegister{
        left: 50%;
        width: 100%;
        top: 95%;
        transform: translate(-50%, -100%);
        transition: 1s 0.8s ease-in-out;
    }
    // .tips{
    //     z-index: 10;
    //     .right{
    //         .content{
    //             transform: translateX(0px);
    //         }
    //     }
    // }
    .tips{
        .tip{
            padding-right: 15%;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            padding: 2.5rem 8%;
        }
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 2fr 1fr;
        .left{
            grid-row: 1 / 2;
            .content{
                transform: translate(0px);
            }
        }
        .right{
            grid-row: 3 / 4;
            .content{
                transform: translateX(0px);
            }
        }
    }
    
}
</style>

<script>
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
// import { bottom } from '@popperjs/core'
import { remove } from 'lodash'

export default{
    name: 'Handler',
    components: {
        Login,
        Register,
        // bottom
    },
    props:{
      default: String
    },
    data(){
        return{
            signUpMode: 'background'
        }
    },
    methods:{
        add(){
            this.$emit('response', this.default+'register-mode')
        },
        remove(){
            this.$emit('response', this.default+'log-in-mode')
        }
    },
    created(){
        this.$emit('response', this.default+'log-in-mode')
    }
}
</script>