function getApplicationList (params) {
    return $axios({
        url: '/application/page',
        method: 'get',
        params
    })
}

function changeApplyStatus (params) {
    return $axios({
        url: '/application',
        method: 'put',
        data: { ...params }
    })
}