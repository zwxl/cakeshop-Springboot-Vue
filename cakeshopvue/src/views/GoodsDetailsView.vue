<template>
  <div>
    <h3 style="text-align: center;color: #ff5000;font-weight: bold">商品详情页</h3>
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
          <div>
            <span>简介：{{item.intro}}</span>
          </div>
          <el-button icon="el-icon-minus" circle @click="btn(false,index)" style="margin-right: 10px"></el-button>
          <span>数量: {{item.amount}}</span>
          <el-button icon="el-icon-plus" circle style="margin-left: 10px"  @click="btn(true,index)"></el-button><br/>
          <el-button type="danger" style="margin-top: 5px" @click="Order(index)">加入购物车</el-button>
        </div>
        <!--        </router-link>-->
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "GoodsDetailsView",
  data(){
    return{
      cakelist:[],
      token:''
    }
  },
  created() {
    this.load(this.$route.query.id)
    this.token = localStorage.getItem("token")
  },
  methods:{
    Totalprice(){
      let price = 0;
      this.cakelist.forEach((item,index)=>{
        price += item.price*item.num
      })
      this.num_price = price.toFixed(2);
      console.log(this.num_price)
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
    load(id){
      axios.get("http://localhost:9090/goods/goodsfind/"+id).then(res => {
        console.log(res.data)
        this.cakelist = res.data
      })
    }
  }
}
</script>

<style scoped>
.goods_list{
  display: flex;
  flex-wrap: wrap;
  width: 300px;
}
.info-wrapper .title {
  display: inline-block;
  max-height: 146px;
  margin: 6px 0 2px 0;
  /*overflow: hidden;*/
  color: #333;
  font-size: 16px;
  line-height: 23px;
  position: relative;
}
.info-wrapper {
  height: 70px;
  margin-left: 162px;
  overflow: hidden;
}

.price-wrapper {
  margin-bottom: 5px;
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
.goods{
  position: relative;
  float: left;
  width: 500px;
  height: 200px;
  color: #333;
  background-color: #f7f9fa;
  border: 1px solid #f7f9fa;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.5s;
  margin-left: 100%;
  margin-top: 30px;
}
</style>