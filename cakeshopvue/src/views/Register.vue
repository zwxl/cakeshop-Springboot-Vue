<template>
  <div class="login clearfix">
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form ref="loginForm" :model="user" status-icon label-width="80px">
          <h3>注册</h3>
          <hr>
          <el-form-item prop="username" label="用户名">
            <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <el-input v-model="user.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="设置密码">
            <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item prop="name" label="真实姓名">
            <el-input v-model="user.name"  placeholder="请输入真实姓名"></el-input>
          </el-form-item>
          <el-form-item prop="phone" label="手机号码">
            <el-input v-model="user.phone"  placeholder="请输入手机号码"></el-input>
          </el-form-item>
          <el-form-item prop="address" label="地址">
            <el-input v-model="user.address"  placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon @click="doRegister()" style="margin: 0 auto">注册账号</el-button>
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
        email: "",
        password: "",
        name:"",
        phone:"",
        address:"",

      },
    };
  },
  created() {
    // console.log($);
    // console.log("1111");
  },
  methods: {
    doRegister() {
      if (!this.user.username) {
        this.$message.error("请输入用户名！");
        return;
      }
      else if (!this.user.email) {
        this.$message.error("请输入邮箱！");
        return;
      } else if (this.user.email != null) {
        var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(this.user.email)) {
          this.$message.error("请输入有效的邮箱！");
          return;
        } else if (!this.user.password) {
          this.$message.error("请输入密码！");
          return;
        }
      }else if(!this.user.name){
        this.$message.error("请输入真实姓名！");
        return;
      }else if(!this.user.phone){
        this.$message.error("请输入手机号码！");
        return;
      }else if(!this.user.address){
        this.$message.error("请输入地址！");
        return;
      }
        axios
            .post("http://localhost:9090/user/register/", {
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              name:this.user.name,
              phone:this.user.phone,
              address:this.user.address,
            })
            .then(res => {
              // console.log("输出response.data", res.data);
              // console.log("输出response.data.status", res.data.status);
              if (res.data.code == 200) {
                this.$message.success("恭喜你注册账号成功")
                // this.$router.push({ path: "/login" });
                this.$router.go(-1)
              } else if(res.data.code == 100){
                this.$message.error("该账号已经被注册")
                return
              }else{
                this.$message.error("请重新注册")
              }
            });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
  height: 400px;
  margin: 185px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 20px;
}

h3 {
  color: #0babeab8;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}

.el-button {
  width: 80%;
  margin-left: -50px;
}
</style>