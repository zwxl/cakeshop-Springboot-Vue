<template>
  <div>
    <el-steps :active="num" style="width: 1200px;margin: 0 auto">
      <el-step title="输入邮箱" description=""></el-step>
      <el-step title="输入验证码" description=""></el-step>
      <el-step title="成功" description=""></el-step>
    </el-steps>
    <div class="form">
      <el-tag type="success">真实姓名:</el-tag>
      <el-input v-model="name" placeholder="请输入真实姓名" style="width: 250px"></el-input>
      <el-tag type="info">邮箱:</el-tag>
      <el-input v-model="email" placeholder="请输入注册的邮箱" style="width: 250px"></el-input>
    </div>
  </div>
</template>

<script>
export default {
  name: "emailLogin",
  data(){
    return{
      num:3,
      name:'',
      email:'',
    }
  }
}
</script>

<style scoped>
.form{
  background-color: #f4f4f4;
  border-radius: 10px;
  width: 500px;
  height: 500px;
  margin: 0 auto;
  margin-top: 10px;
}
</style>