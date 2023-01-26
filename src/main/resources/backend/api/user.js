function getMemberList(params) {
    return $axios({
        url: '/user/page',
        method: 'get',
        params
    })
}

function enableOrDisableEmployee(params) {
    return $axios({
        url: '/user',
        method: 'put',
        data: {...params}
    })
}


// edit staff
function editEmployee(params) {
    return $axios({
        url: '/user',
        method: 'put',
        data: {...params}
    })
}

function queryEmployeeById(id) {
    return $axios({
        url: `/user/${id}`,
        method: 'get'
    })
}

const queryDishById = (id) => {
    return $axios({
        url: `/shop/${id}`,
        method: 'get'
    })
}