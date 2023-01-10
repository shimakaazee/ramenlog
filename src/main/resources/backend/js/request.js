(function (win) {
    axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
    const service = axios.create({
        baseURL: '/',
        timeout: 10000
    })
    // request拦截器
    service.interceptors.request.use(config => {

        if (config.method === 'get' && config.params) {
            let url = config.url + '?';
            for (const propName of Object.keys(config.params)) {
                const value = config.params[propName];
                var part = encodeURIComponent(propName) + "=";
                if (value !== null && typeof (value) !== "undefined") {
                    if (typeof value === 'object') {
                        for (const key of Object.keys(value)) {
                            let params = propName + '[' + key + ']';
                            var subPart = encodeURIComponent(params) + "=";
                            url += subPart + encodeURIComponent(value[key]) + "&";
                        }
                    } else {
                        url += part + encodeURIComponent(value) + "&";
                    }
                }
            }
            url = url.slice(0, -1);
            config.params = {};
            config.url = url;
        }
        return config
    }, error => {
        console.log(error)
        Promise.reject(error)
    })

    service.interceptors.response.use(res => {
            console.log('---filter---', res)
            // success
            const code = res.data.code;
            // get error msg
            const msg = res.data.msg
            console.log('---code---', code)
            if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {// go back to login page
                console.log('---/backend/page/login/login.html---', code)
                localStorage.removeItem('userInfo')
                window.top.location.href = '/backend/page/login/login.html'
            } else {
                return res.data
            }
        },
        error => {
            console.log('err' + error)
            let {message} = error;
            if (message == "Network Error") {
                message = "backend error";
            } else if (message.includes("timeout")) {
                message = "overtime";
            } else if (message.includes("Request failed with status code")) {
                message = "api" + message.substr(message.length - 3) + "error";
            }
            window.ELEMENT.Message({
                message: message,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject(error)
        }
    )
    win.$axios = service
})(window);
