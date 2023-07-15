<template>
  <div class="cart-items">
    <h2 style="text-align: center">我的购物车</h2>
    <div class="container">
      <div class="cart-header "  v-for="(item,index) in cakelist" :key="index">
      <div class="cart-sec">
        <!--            蛋糕名字-->
        <div class="cart-item">
          <img :src="require(`../assets${item.images1}`)" class="img-responsive">
        </div>
        <!--            蛋糕单价-->
        <div class="cart-item-info">
          <h3>{{item.name}}</h3>
          <h3>
            <span>金额: ¥ {{item.total}}</span>
          </h3>
          <h3>
            <span>数量: {{item.amount}}</span>
          </h3>
          <el-button type="primary" icon="el-icon-remove-outline" @click="btn(false,index)">减少</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="btn(true,index)">增加</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="del(index)">删除</el-button>
        </div>
      </div>
    </div>
    </div>
      <h2 style="text-align: center;color: #ff5000;">支付费用:￥{{num_price}}</h2>
      <el-button type="danger" @click="Totalprice" style="margin-left:45%;margin-top: 8px;width: 160px;height: 50px;font-weight: bold"><i class="el-icon-goods"></i>立即下单</el-button>
<!--&lt;!&ndash;    底部&ndash;&gt;-->
<!--    <div style="width: 100%;height: 50px;background-color: gray" id="footer">-->
<!--      <span style="text-align: center;color: white;line-height: 50px"><h3>欢迎来到蛋糕商城购物车</h3></span>-->
<!--    </div>-->
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "cart",
  data(){
    return {
      num:0,
      price:0,
      show:'',
      token:'',
      cakename:'',
      num_price:0,
      cakelist:[],
    }
  },
  created() {
      this.token = localStorage.getItem("token")
      this.load()
  },
  methods:{
    Totalprice(){
      if(this.token=="null"){
        this.$message.error("请先登录！！")
        this.$router.push({path:'/login'})
        return;
      }
      let price = 0;
      this.cakelist.forEach((item,index)=>{
        price += item.total*item.amount
      })
      this.num_price = price.toFixed(2);
      axios.post("http://localhost:9090/order/OrderItem", this.cakelist).then(res => {
        console.log(res.data)
      })
      this.cakelist = ""
      this.$router.push({path:'/order'})
    },
    btn(bool,index){
      let shopIndex = this.cakelist[index]
      if(bool){
        shopIndex.amount++
      }else{
        if(shopIndex.num<=1){
          return
        }
        shopIndex.amount--
      }
      this.Totalprice
    },
    del(index){
      this.cakelist.splice(index,1)
    },
    load(){
      axios.get("http://localhost:9090/order/orderlist", {
      headers:{
        'Content-Type':"application/json;charset=UTF-8",
        'Authorization': this.token
      }
      }).then(res => {
        this.cakelist = res.data
      })
    }
  }
}
</script>

<style scoped>
#footer{
  width: 100%;
  height:100px;   /* footer的高度一定要是固定值*/
  position:absolute;
  bottom:0px;
  left:0px;
  background: #333;
}
.cart-items{
  width: 100%;
}
.container {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
.cart-header {
  width: 500px;
  height: 240px;
  position: relative;
  padding-left: 50px;
  padding-top: 50px;
  margin-left: 50px;
}
.cart-sec {
  margin-bottom: 3em;
}
.cart-item {
  width: 35%;
  float: left;
  margin-right: 5%;
}
.cart-item-info {
  width: 60%;
  float: left;
}
.img-responsive{
  display: block;
  max-width: 100%;
  height: auto;
}
.cart-item img {
  width: 100%;
}
.cart-item-info h3 span {
  display: block;
  font-weight: 400;
  font-size: 0.85em;
  margin: 0.7em 0;
}
</style>