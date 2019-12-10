import request from '@/utils/request'

export function getOrgTypes() {
  return request({
    url: '/settings/types/org',
    method: 'get'
  })
}

export function getIdTypes() {
  return request({
    url: '/settings/types/identity',
    method: 'get'
  })
}

export function getAdmin1(code) {
  return request({
    url: `/settings/admin1/${code}`,
    method: 'get'
  })
}

export function getAdmin1List() {
  return request({
    url: '/settings/admin1',
    method: 'get'
  })
}

export function getAdmin2(code) {
  return request({
    url: `/settings/admin2/${code}`,
    method: 'get'
  })
}

export function getAdmin2List(code) {
  return request({
    url: `/settings/admin1/${code}/admin2`,
    method: 'get'
  })
}
