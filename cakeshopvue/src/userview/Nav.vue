<template>
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      router
      active-text-color="#ffd04b">
    <el-menu-item index="/userhome"><i class="el-icon-menu"></i>首页</el-menu-item>
    <el-submenu index="2">
      <template slot="title"><i class="el-icon-potato-strips"></i>蛋糕分类</template>
<!--                <el-menu-item index="/category">全部</el-menu-item>-->
               <div v-for="(item,index) in typelist" :key="index">
                 <el-menu-item index="/category" @click="jumpPage(item.id)">{{item.typename}}</el-menu-item>
               </div>
    </el-submenu>
    <el-menu-item index="/order"><i class="el-icon-coin"></i>订单管理</el-menu-item>
    <el-menu-item index="5"><i class="el-icon-s-promotion"></i><a href="https://www.ele.me" target="_blank">友情链接</a></el-menu-item>
    <el-menu-item index="/userCenter" style="float: right"><i class="el-icon-s-custom"></i>个人中心</el-menu-item>
    <el-menu-item index="/cart" style="float: right"><i class="el-icon-shopping-cart-full"></i>购物车</el-menu-item>
  </el-menu>
</template>

<script>
import axios from "axios";

export default {
  components:{
  },
  data() {
    return {
      activeIndex2: '1',
      typelist:[],
    };
  },
  created() {
    this.load()
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    load(){
      axios.get("http://localhost:9090/type").then(res => {
        console.log(res.data)
        this.typelist = res.data
      })
    },
      jumpPage(id) {
        this.$router.push({
          path: '/category',
          query: {
            id: id
          }
        })
      },
  }
}
</script>

<style scoped>

</style>