<template>
  <div>
    这是主页
    <el-switch
        v-model="value"
        active-color="#13ce66"
        inactive-color="#ff4949">
    </el-switch>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      value: 1
    }
  }
}
</script>

<style scoped>

</style>