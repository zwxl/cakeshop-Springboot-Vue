<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc;">
<!--        <div style="flex: 1; font-size: 20px">-->
<!--          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>-->
<!--        </div>-->
<!--        <el-dropdown style="width: 70px; cursor: pointer">-->
<!--          <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
<!--          <el-dropdown-menu slot="dropdown" >-->
<!--            <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>-->
<!--            <el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </el-dropdown>-->
        <Header :collapseBtnClass="collapseBtnClass" :collapse="isCollapse" />
      </el-header>

      <el-main>
          <router-view/>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default {
  name: 'Manage',
  components: {Aside,Header},
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
  }
}
</script>

<style>

</style>