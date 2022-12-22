import axios from "axios"

const authrequest = axios.create({
    baseURL:'http://localhost:801/',
    timeout:5000
})

authrequest.interceptors.request.use(
    (config) => {
        console.log(1)
        return config
    },
    (error) => {
        console.log(2)
        return Promise.reject(error)
    })

export default authrequest