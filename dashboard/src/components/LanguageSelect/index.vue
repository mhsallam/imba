<template>
  <el-dropdown trigger="click" @command="handleSetLanguage">
    <div>
      <svg-icon class-name="size-icon" icon-class="language" />
    </div>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item v-for="item of languageOptions" :key="item.value" :disabled="language===item.value" :command="item.value">
        {{ item.label }}
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
export default {
  data() {
    return {
      languageOptions: [
        { label: 'English', value: 'en' },
        { label: 'عربي', value: 'ar' }
      ]
    }
  },
  computed: {
    language() {
      return this.$store.getters.language
    }
  },
  methods: {
    handleSetLanguage(lang) {
      this.$ELEMENT.language = lang
      this.$i18n.locale = lang
      this.$store.dispatch('app/setLanguage', lang)
      this.refreshView()
      this.$message({
        message: 'Switch Language Success',
        type: 'success'
      })
    },
    refreshView() {
      // In order to make the cached page re-rendered
      this.$store.dispatch('tagsView/delAllCachedViews', this.$route)

      const { fullPath } = this.$route

      this.$nextTick(() => {
        this.$router.replace({
          path: '/redirect' + fullPath
        })
      })
    }
  }

}
</script>
