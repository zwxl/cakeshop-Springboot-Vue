<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/goods">
        <i class="el-icon-burger"></i>
        <span slot="title">商品管理</span>
      </el-menu-item>
      <el-menu-item index="/cateGoryAdmin">
        <i class="el-icon-box"></i>
        <span slot="title">分类管理</span>
      </el-menu-item>
      <el-menu-item index="/orderListAdmin">
        <i class="el-icon-dish-1"></i>
        <span slot="title">订单管理</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  // computed:{
  //   isCollapse(){
  //     return this.$store.state.collapse
  //   }
  // }
}
</script>

<style scoped>

</style>
