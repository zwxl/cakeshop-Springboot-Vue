<template>
  <div class="goods_list">
    <!--      蛋糕列表-->
    <div v-for="(item,index) in cakelist" :key="index">
      <div class="goods">
        <div class="img-wrapper">
          <img :src="require(`../assets${item.images1}`)">
        </div>
        <div class="info-wrapper">
          <div class="title">
            <h3 style="color: #ff5000">{{item.name}}</h3>
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
        <el-button type="danger" style="margin-top: 5px">立即购买</el-button>
        <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Category",
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
    };
  },
  created() {
    this.load(this.$route.query.id)
  },
  methods:{
    load(id){
      axios.get("http://localhost:9090/type/typefind/"+id).then(res => {
        this.cakelist = res.data
        console.log(res.data)
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
  },
  watch:{
    $route:function(){
      this.load(this.$route.query.id)
    }
  }

}
</script>

<style scoped>
.goods_list{
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
}
.info-wrapper .title {
  display: inline-block;
  max-height: 110px;
  margin: 2px 0 2px 0;
  /*overflow: hidden;*/
  color: #333;
  font-size: 16px;
  line-height: 23px;
  position: relative;
}
.info-wrapper {
  height: 55px;
  margin-left: 162px;
  overflow: hidden;
}

.price-wrapper {
  margin-bottom: 10px;
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

.recommand .rh-title{
  color: black;
  float: left;
  font-weight: bold;
  font-size: 24px;
  line-height: 24px;
}


.goods{
  position: relative;
  float: left;
  width: 372px;
  height: 174px;
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