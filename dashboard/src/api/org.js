import request from '@/utils/request'

export function getOrgs(query) {
  return request({
    url: '/org',
    method: 'get',
    params: query
  })
}

export function addOrg(data) {
  return request({
    url: '/org/add',
    method: 'post',
    data
  })
}

export function updateOrg(data) {
  return request({
    url: `/org/update`,
    method: 'put',
    data
  })
}

export function deleteOrg(id) {
  return request({
    url: `/org/delete/${id}`,
    method: 'delete'
  })
}
