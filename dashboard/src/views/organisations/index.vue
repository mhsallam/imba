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
        v-model="listQuery.type"
        :placeholder="tr('generic.type', 'Type')"
        clearable
        class="filter-item"
        style="width: 130px"
      >
        <el-option
          v-for="item in orgTypeOptions"
          :key="item.key"
          :label="item.key + ': ' + item.displayName"
          :value="item.key"
        />
      </el-select>
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
        v-if="authorized(['ROLE_ADMIN'])"
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
      <el-table-column :label="tr('generic.name', 'Name')" min-width="150px" align="left">
        <template slot-scope="{ row }">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tr('organisation.acronym', 'Acronym')" align="center" width="95">
        <template slot-scope="{ row }">
          <span>{{ row.acronym }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tr('generic.type', 'Type')" align="center" width="95">
        <template slot-scope="{ row }">
          <span>{{ row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column
        v-if="authorized(['ROLE_ADMIN'])"
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
        <el-form-item label="Type" prop="type">
          <el-select
            v-model="temp.type"
            class="filter-item"
            placeholder="Please select"
          >
            <el-option
              v-for="item in orgTypeOptions"
              :key="item.key"
              :label="item.displayName"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Name" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="Acronym" prop="acronym">
          <el-input v-model="temp.acronym" />
        </el-form-item>
        <el-form-item :label="tr('generic.remarks', 'Remarks')">
          <el-input
            v-model="temp.remarks"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            :placeholder="tr('generic.remarks', 'Remarks')"
          />
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
import { getOrgs, addOrg, updateOrg, deleteOrg } from '@/api/org'
// import { getOrgTypes } from '@/api/settings'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import checkPermission from '@/utils/permission'

export default {
  name: 'Organisations',
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
      orgTypes: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 10,
        sort: 'id,asc',
        name: undefined,
        acronym: undefined,
        type: undefined
      },
      orgTypeOptions: [
        { key: 'UN', displayName: this.tr('enum.organiationType.un', 'UN') },
        { key: 'INGO', displayName: this.tr('enum.organiationType.ingo', 'INGO') },
        { key: 'NNGO', displayName: this.tr('enum.organiationType.nngo', 'NNGO') },
        { key: 'GOV', displayName: this.tr('enum.organiationType.gov', 'GOV') }
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
        acronym: '',
        type: 'NNGO',
        remarks: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'edit',
        create: 'create'
      },
      rules: {
        type: [
          { required: true, message: 'type is required', trigger: 'change' }
        ],
        acronym: [
          { required: true, message: 'acronym is required', trigger: 'blur' }
        ],
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
    authorized(value) {
      return checkPermission(value)
    },
    getList() {
      this.listLoading = true
      getOrgs(this.listQuery).then(response => {
        this.list = response.content
        this.total = response.totalElements
        this.page = response.page + 1

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
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
      const { prop, order } = data
      this.listQuery.sort = `${prop},${order === 'ascending' ? 'asc' : 'desc'}`
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remarks: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
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
          addOrg(this.temp).then(() => {
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
          console.log(tempData)
          updateOrg(tempData).then(() => {
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
      if (this.temp.id > 0) {
        this.$confirm('Confirm to remove the role?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(async() => {
          deleteOrg(this.temp.id).then(() => {
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
