<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="changeCollapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/userhome'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{this.$route.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

<!--    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" style="margin-top: 5px;margin-right: 2px"></el-avatar>-->
    <el-upload
        class="avatar-uploader"
        action="http://localhost:9090/user/upload"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <el-dropdown style="width: 70px; cursor: pointer" @command="handleCommand">
      <span>{{name}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" >
<!--        <el-dropdown-item style="font-size: 14px; padding: 5px 0" >个人信息</el-dropdown-item>-->
        <el-dropdown-item style="font-size: 14px; padding: 5px 0" command="exit">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: "",
  },
  data(){
    return{
      token:'',
      name:'',
      imageUrl: ''
    }
  },
  created() {
    this.token = localStorage.getItem("token")
    console.log(this.token)
    this.load()
  },
  methods:{
    changeCollapse(){
      this.$store.commit("change")
    },
    exit(){
      this.$route.push({path:'/'})
      console.log("exit")
    },
    handleCommand(command) {
      // if(command=='a'){
      //   console.log("个人信息")
      // }
      // else{
        this.$message.success("退出成功");
        this.$router.push({path:'/login'})
      // }
    },
    load(){
      axios.get("http://localhost:9090/user/findUserId/", {headers:{
          'Content-Type':"application/json;charset=UTF-8",
          'Authorization': this.token
        }})
          .then(res => {
            this.name = res.data
            if(!this.name){
              this.$message.success("请先登录");
              this.$router.push({path:'/login'})
            }
          });
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }


}
</script>

<style scoped>

</style>