<template>
  <div class="login" clearfix>
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="user"  status-icon label-width="80px">
          <h3>登录</h3>
          <hr>
          <el-form-item prop="username" label="用户名">
            <el-input v-model="user.username" placeholder="请输入用户名" prefix-icon></el-input>
          </el-form-item>
          <el-form-item id="password" prop="password" label="密码">
            <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <router-link to="/" style="margin-left: 80px">找回密码</router-link>
          <router-link to="/register" style="margin-left: 80px">注册账号</router-link>
          <el-form-item>
            <el-button type="primary" icon="el-icon-upload" @click="doLogin()" style="margin-left: 10px;">登 录</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      token:"",
    };
  },
  created() {
    this.token = localStorage.getItem("token")
  },
  methods: {
    doLogin() {
      console.log(this.token)
      console.log(this.user)
      if (!this.user.username) {
        this.$message.error("请输入用户名！");
        return;
      } else if (!this.user.password) {
        this.$message.error("请输入密码！");
        return;
      } else {
        //校验用户名和密码是否正确;
        axios.post("http://localhost:9090/user/login/", {
              username: this.user.username,
              password: this.user.password
            },{headers:{
            'Content-Type':"application/json;charset=UTF-8",
            'Authorization': this.token
          }})
            .then(res => {
              console.log(res.data)
              if (res.data.code == 200) {
                this.$message.success("登录成功")
                localStorage.setItem("token",res.data.data)
                this.$router.push({ path: "/userhome" });
              } else if (res.data.code == 101){
                this.$message.error("你还没有注册，请先注册")
              }else if (res.data.code == 103){
                this.$message.error("你的账户已经失效，请联系管理员")
              }else{
                this.$message.error("账号或者密码错误")
              }
            });
      }
    }
  }
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 740px;
  background: url("../assets/images/login.jpg") no-repeat;
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
  background: url("../assets/images/login.jpg") no-repeat;
  background-size: cover;
  width: 400px;
  height: 300px;
  margin: 215px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 40px;
}
#password {
  margin-bottom: 5px;
}
h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}
a:hover {
  color: coral;
}
.el-button {
  width: 80%;
  margin-left: -50px;
}
</style>