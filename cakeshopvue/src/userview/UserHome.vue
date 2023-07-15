<template>
  <div>

    <el-carousel :interval="4000" type="card" height="300px" style="margin-top: 0">
      <el-carousel-item v-for="item in imgwrap" :key="item.url">
        <img :src="item.url"style="width: 100%"/>
      </el-carousel-item>
    </el-carousel>

    <h3 class="recommand">
      <span class="rh-title">热销推荐</span>
      <img class="logo" src="../assets/images/recommand.png" alt="猜你喜欢"/>
    </h3>
    <!--    商品-->
    <div class="goods_list">
      <div v-for="(item,index) in cakelist" :key="index">
<!--        <router-link to="/goodsDetailsView?"+{{item.id}}>-->
          <div class="goods">
            <div class="img-wrapper">
              <img :src="require(`../assets${item.images1}`)" @click="jumpPage(item.id)">
            </div>
            <div class="info-wrapper">
              <div class="title">
                <h3 style="color: #ff5000">{{item.name}}</h3>
                <h5 style="margin-left: 2px;margin-top: 15px">库存: {{item.stock}}</h5>
              </div>
            </div>
            <div class="price-wrapper">
              <h5 style="margin-left: 2px;display: inline-block">单价: </h5>
              <em>￥</em>
              <span>{{item.price}}</span>
            </div>
            <el-button icon="el-icon-minus" circle @click="btn(false,index)" style="margin-right: 10px"></el-button>
              <span>数量: {{item.amount}}</span>
            <el-button icon="el-icon-plus" circle style="margin-left: 10px"  @click="btn(true,index)"></el-button><br/>
            <el-button type="danger" style="margin-top: 5px" @click="buynow">立即购买</el-button>
            <el-button type="primary" style="margin-left: 10px" @click="Order(index)">加入购物车</el-button>
          </div>
      </div>
    </div>
    <!--    尾页-->
    <div class="page"></div>
    <div style="width: 100%;height: 50px;background-color: gray" id="footer">
      <span style="text-align: center;color: white;line-height: 50px"><h3>欢迎来到蛋糕商城首页</h3></span>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserHome",
  data() {
    return {
      activeIndex2: '1',
      imgwrap:[
        {url:require("../assets/images/l1.jpg")},
        {url:require("../assets/images/l2.jpg")},
        {url:require("../assets/images/l3.jpg")},
      ],
      cakelist:[],
      num_price:0,
      token:''
    };
  },
  created() {
    this.token = localStorage.getItem("token")
    this.load()
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    Totalprice(){
      let price = 0;
      this.cakelist.forEach((item,index)=>{
        price += item.price*item.num
      })
      this.num_price = price.toFixed(2);
      console.log(this.num_price)
    },
    load(){
      axios.get("http://localhost:9090/goods/goodsfind/").then(res => {
        console.log(res.data)
        this.cakelist = res.data
      })
    },
    jumpPage(id) {
       this.$router.push({
       path: '/goodsDetailsView',
         query: {
            id: id
        }
      })
    },
    btn(bool,index){
      let shopIndex = this.cakelist[index]
      if(bool){
        shopIndex.amount++
      }else{
        if(shopIndex.num<=1){
          return
        }
       if( shopIndex.amount<=0){
         return
       }
        shopIndex.amount--
      }
      this.Totalprice
    },
    Order(index){
      let orderIndex = this.cakelist[index]
      orderIndex.price = (orderIndex.price * orderIndex.amount).toFixed(2)
      orderIndex.stock = orderIndex.stock - orderIndex.amount
      if(orderIndex.stock<=0){
        this.$message.error("库存不足！！！")
        return
      }
      axios.post("http://localhost:9090/goods/addOrder", orderIndex,{
        headers:{
          'Content-Type':"application/json;charset=UTF-8",
          'Authorization': this.token
        }
      }).then(res => {
        const h = this.$createElement;
        this.$message.success("操作成功")
        this.$notify({
          title: '费用',
          message: h('i', { style: 'color: teal'}, '支付费用'+orderIndex.price)
        });
      })
    },
    buynow(index){
      this.Order(index)
      this.$router.push ({path:'/order'})
    }
  }
}
</script>

<style scoped>
.page{
  margin-bottom: 200px;
}
#footer{
  width: 100%;
  height:100px;   /* footer的高度一定要是固定值*/
  bottom:0px;
  left:0px;
  background: #333;
}
.goods_list{
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
}
.info-wrapper .title {
  display: inline-block;
  max-height: 146px;
  margin: 6px 0 2px 0;
  color: #333;
  font-size: 16px;
  line-height: 23px;
  position: relative;
}
.info-wrapper {
  height: 80px;
  margin-left: 162px;
  overflow: hidden;
}

.price-wrapper {
  margin-bottom: 20px;
  margin-left: 162px;
  color: #ff5000;
}
.img-wrapper {
  background: #f4f4f4;
  overflow: hidden;
  float: left;
}
.img-wrapper img{
  width: 150px;
  height: 150px;
  margin: 10px;
  border-radius: 20px;
  vertical-align: auto;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}
.recommand{
  height: 24px;
  margin-bottom: 20px;
  margin-left: 6px;
}

.recommand .rh-title{
  color: black;
  float: left;
  font-weight: bold;
  font-size: 24px;
  line-height: 24px;
}

.logo{
  height: 20px;
  margin: 4px 0 0 6px;
}

.goods{
  position: relative;
  float: left;
  width: 372px;
  height: 200px;
  overflow: hidden;
  color: #333;
  background-color: #f7f9fa;
  border: 1px solid #f7f9fa;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.5s;
  margin-top: 30px;
  margin-left: 70px;
  margin-right: 40px;
}
</style>