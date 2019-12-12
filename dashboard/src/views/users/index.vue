<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        :placeholder="tr('generic.name', 'Name')"
        style="width: 200px"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />
      <el-select
        v-model="listQuery.sort"
        style="width: 140px"
        class="filter-item"
        placeholder="Select"
        @change="handleFilter"
      >
        <el-option
          v-for="item in sortOptions"
          :key="item.key"
          :label="item.label"
          :value="item.key"
        />
      </el-select>
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >
        {{ tr('generic.search', 'Search') }}
      </el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >
        {{ tr('generic.add', 'Add') }}
      </el-button>
      <el-button
        v-waves
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >
        {{ tr('generic.export', 'Export') }}
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%"
      @sort-change="sortChange"
    >
      <el-table-column
        :label="tr('generic.id', 'ID')"
        prop="id"
        sortable="custom"
        align="center"
        width="80"
        :class-name="getSortClass('id')"
      >
        <template slot-scope="{ row }">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tr('user.avatar', 'Avatar')" align="center" width="95">
        <template slot-scope="{ row }">
          <img :src="row.avatar" width="40" class="user-avatar">
        </template>
      </el-table-column>
      <el-table-column :label="tr('generic.name', 'Name')" min-width="150px" align="left">
        <template slot-scope="{ row }">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tr('user.email', 'Email')" align="center" width="95">
        <template slot-scope="{ row }">
          <span>{{ row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="Actions"
        align="center"
        width="230"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="{ row }">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            {{ tr('generic.edit', 'Edit') }}
          </el-button>
          <el-button type="danger" size="mini" @click="handleDelete(row)">
            {{ tr('generic.delete', 'Delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.size"
      @pagination="getList"
    />

    <el-dialog :title="tr('generic.'+textMap[dialogStatus], textMap[dialogStatus])" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 400px margin-left:50px"
      >
        <el-form-item :label="tr('generic.name', 'Name')" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item :label="tr('user.email', 'Email')" prop="acronym">
          <el-input v-model="temp.email" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="dialogStatus === 'create' ? createData() : updateData()"
        >
          {{ tr('generic.okay', 'Okay') }}
        </el-button>
        <el-button @click="dialogFormVisible = false">
          {{ tr('generic.cancel', 'Cancel') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUsers, addUser, updateUser, deleteUser } from '@/api/user'
// import { getUserTypes } from '@/api/settings'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'Useranisations',
  components: { Pagination },
  directives: { waves },
  filters: {
    // orgTypeFilter(type) {
    //   // arr to obj, such as { US : 'USA' }
    //   const orgTypeKeyValue = this.orgTypeOptions.reduce((acc, cur) => {
    //     acc[cur.key] = cur.displayName
    //     return acc
    //   }, {})

    //   return orgTypeKeyValue[type]
    // }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      roleTypes: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 0,
        size: 10,
        sort: 'id,asc',
        name: undefined
      },
      roleOptions: [
        { key: 'ROLE_USER', displayName: this.tr('permission.roleUser', 'User') },
        { key: 'ROLE_ADMIN', displayName: this.tr('permission.roleAdmin', 'Admin') }
      ],
      sortOptions: [
        { label: this.tr('generic.idAscending', 'ID Ascending'), key: 'id,asc' },
        { label: this.tr('generic.idDescending', 'ID Descending'), key: 'id,desc' },
        { label: this.tr('generic.nameAscending', 'Name Ascending'), key: 'name,asc' },
        { label: this.tr('generic.nameDescending', 'Name Descending'), key: 'name,desc' }
      ],
      showReviewer: false,
      temp: {
        id: undefined,
        name: '',
        email: '',
        roles: ['ROLE_USER']
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'edit',
        create: 'create'
      },
      rules: {
        email: [{ required: true, message: 'email is required', trigger: 'blur' }],
        name: [{ required: true, message: 'name is required', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    tr(key, value) {
      const t = this.$i18n.t(key)
      return t !== key ? t : value
    },
    getList() {
      this.listLoading = true
      const query = Object.assign({}, this.listQuery);
      query.page = query.page - 1 < 0 ? 0 : query.page - 1
      getUsers(query).then(response => {
        this.list = response.content
        this.total = response.totalElements
        this.page = response.number

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 0
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: 'Success',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      console.log(data)
      const { prop, order } = data
      this.listQuery.sort = `${prop},${order === 'ascending' ? 'asc' : 'desc'}`
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        email: '',
        roles: ['ROLE_USER']
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          addUser(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateUser(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      if (row.id > 0) {
        this.$confirm('Confirm to remove this user?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(async() => {
          deleteUser(row.id).then(() => {
            this.$notify({
              title: 'Success',
              message: 'Delete Successfully',
              type: 'success',
              duration: 2000
            })
            const index = this.list.indexOf(row)
            this.list.splice(index, 1)
          })
        })
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['id', 'name', 'acronym', 'type', 'remarks']
        const filterVal = ['id', 'name', 'acronym', 'type', 'remarks']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v =>
        filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        })
      )
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `${key},asc`
        ? 'ascending'
        : sort === `-${key},desc`
          ? 'descending'
          : ''
    }
  }
}
</script>
