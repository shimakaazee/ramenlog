function getMemberList (params) {
  return $axios({
    url: '/employee/page',
    method: 'get',
    params
  })
}

// 修改---启用禁用接口
function enableOrDisableEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'put',
    data: { ...params }
  })
}

// add staff
function addEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'post',
    data: { ...params }
  })
}

// edit staff
function editEmployee (params) {
  return $axios({
    url: '/employee',
    method: 'put',
    data: { ...params }
  })
}

function queryEmployeeById (id) {
  return $axios({
    url: `/employee/${id}`,
    method: 'get'
  })
}