import request from '@/utils/request'

export function listDrinks(query) {
  return request({
    url: '/admin/products/list',
    method: 'get',
    params: query
  })
}

export function deleteDrinks(data) {
  return request({
    url: '/goods/delete',
    method: 'post',
    data
  })
}

export function publishDrinks(data) {
  return request({
    url: '/goods/create',
    method: 'post',
    data
  })
}

export function detailGoods(id) {
  return request({
    url: '/goods/detail',
    method: 'get',
    params: { id }
  })
}

export function editGoods(data) {
  return request({
    url: '/goods/update',
    method: 'post',
    data
  })
}

export function listCatAndBrand() {
  return request({
    url: '/goods/catAndBrand',
    method: 'get'
  })
}
