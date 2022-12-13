function getApplicationList (params) {
    return $axios({
        url: '/application/page',
        method: 'get',
        params
    })
}