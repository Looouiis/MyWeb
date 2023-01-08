import router from "@/router"
import axios from "axios"

const request = axios.create()

request.interceptors.request.use(
    (config) => {
        // console.log(1)
        let token = localStorage.getItem('token')
        if(token != null && token != ''){
            config.headers.set('token',token)
        }
        else {
            let anoToken = localStorage.getItem('anoToken')
            if(anoToken != null && token != ''){
                config.headers.set('anoToken', anoToken)
            }
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

request.interceptors.response.use(
    (config) => {
        // console.log(config)
        return config
    },
    (error) => {
        if('code' in error && error.code === 'ERR_NETWORK'){
            console.log('与服务器的链接断开')
            return Promise.reject(error)
        }
        let data = {
            token: localStorage.getItem('token'),
            refreshToken: localStorage.getItem('refreshToken')
        }
        console.log(error)
        axios.put('http://localhost:722/users/token',data).then((res) => {
            if(res.data.status){
                localStorage.setItem('token', res.data.token)
                localStorage.setItem('refreshToken', res.data.refreshToken)
            }
            else{
                localStorage.removeItem('token')
                localStorage.removeItem('refreshToken')
                router.push('/LoginorRegister')
            }
        })
        return Promise.reject(error)
    }
)

export default request