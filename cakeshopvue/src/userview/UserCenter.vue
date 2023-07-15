<template>
  <div>
    <el-menu class="el-menu-demo" mode="horizontal"  background-color="#545c64" text-color="#fff">
      <el-menu-item @click="routing" style="float: right">后台管理<i class="el-icon-s-promotion"></i></el-menu-item>
    </el-menu>
    <div class="show">
      <el-descriptions title="用户信息">
        <el-descriptions-item label="用户名">{{userinfo.username}}</el-descriptions-item>
        <el-descriptions-item label="手机号">{{userinfo.phone}}</el-descriptions-item>
        <el-descriptions-item label="居住地">{{userinfo.address}}</el-descriptions-item>
        <el-descriptions-item label="邮箱">{{userinfo.email}}</el-descriptions-item>
        <el-descriptions-item label="备注">
          <el-tag size="small">学校</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="联系地址">{{userinfo.address}}</el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserCenter",
  data(){
    return{
      token:'',
      userinfo:[]
    }
  },
  created() {
    this.token = localStorage.getItem("token")
    if(this.token==null){
      this.$router.push ({path:'/login'})
    }
    console.log(this.token)
    this.load()
  },
  methods:{
    load(){
      axios.get("http://localhost:9090/user/findOneUser",{
        headers:{
          'Content-Type':"application/json;charset=UTF-8",
          'Authorization': this.token
        }
      }).then(res => {
        console.log(res.data)
        this.userinfo = res.data
      })
    },
    routing(){
      if(this.userinfo.isadmin){
        this.$message.success("欢迎你，管理员")
        this.$router.push ({path:'/user'})
      }
      else{
        this.$message.error("你没有管理员权限")
      }
    }
  }
}
</script>

<style scoped>
.show{
  margin: 100px auto;
  width: 60%;
  height: 400px;
  border: 5px solid lightcyan;
  transition: all 0.9s;
  border-radius: 10px;
}

.show:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-top: 90px;
}
</style>