const getDishPage = (params) => {
    return $axios({
        url: '/shop/page',
        method: 'get',
        params
    })
}

const deleteDish = (ids) => {
    return $axios({
        url: `/shop/${ids}`,
        method: 'delete',
        params: {ids}
    })
}

const editDish = (params) => {
    return $axios({
        url: '/shop',
        method: 'put',
        data: {...params}
    })
}

const addDish = (params) => {
    return $axios({
        url: '/shop',
        method: 'post',
        data: {...params}
    })
}

const queryDishById = (id) => {
    return $axios({
        url: `/shop/${id}`,
        method: 'get'
    })
}

const getCategoryList = (params) => {
    return $axios({
        url: '/category/list',
        method: 'get',
        params
    })
}

const queryDishList = (params) => {
    return $axios({
        url: '/shop/list',
        method: 'get',
        params
    })
}

const commonDownload = (params) => {
    return $axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        url: '/common/download',
        method: 'get',
        params
    })
}


const dishStatusByStatus = (params) => {
    return $axios({
        url: `/shop/status`,
        method: 'put',
        params:
            {
                ids: params.id,
                status: params.status
            }
    })
}