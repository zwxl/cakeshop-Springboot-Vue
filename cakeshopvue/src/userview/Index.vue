<template>
<!--      <el-menu-->
<!--          :default-active="activeIndex2"-->
<!--          class="el-menu-demo"-->
<!--          mode="horizontal"-->
<!--          @select="handleSelect"-->
<!--          background-color="#545c64"-->
<!--          text-color="#fff"-->
<!--          router-->
<!--          active-text-color="#ffd04b">-->
<!--        <el-menu-item index="/userhome"><i class="el-icon-menu"></i>首页-->
<!--            <UserHome/>-->
<!--        </el-menu-item>-->
<!--        <el-submenu index="2">-->
<!--          <template slot="title">商品管理</template>-->
<!--&lt;!&ndash;          <el-menu-item index="2-1">选项1</el-menu-item>&ndash;&gt;-->
<!--        </el-submenu>-->
<!--        <el-menu-item index="3">订单管理</el-menu-item>-->
<!--        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">友情链接</a></el-menu-item>-->
<!--      </el-menu>-->
<!--      <el-carousel :interval="4000" type="card" height="300px" style="margin-top: 0">-->
<!--        <el-carousel-item v-for="item in imgwrap" :key="item.url">-->
<!--          <img :src="item.url" style="width: 950px;height: 378px"/>-->
<!--        </el-carousel-item>-->
<!--      </el-carousel>-->

<!--     <h3 class="recommand">-->
<!--        <span class="rh-title">猜你喜欢</span>-->
<!--        <img class="logo" src="../assets/images/recommand.png" alt="猜你喜欢"/>-->
<!--     </h3>-->
<!--&lt;!&ndash;    商品&ndash;&gt;-->
<!--    <div class="goods_list">-->
<!--        <div class="goods">-->
<!--            <div class="img-wrapper">-->
<!--                <img src="../assets/images/8-2.jpg">-->
<!--            </div>-->
<!--            <div class="info-wrapper">-->
<!--                <div class="title">-->
<!--                    <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--                    <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--                </div>-->
<!--            </div>-->
<!--            <div class="price-wrapper">-->
<!--              <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--                <em>￥</em>-->
<!--                <span>159</span>-->
<!--            </div>-->
<!--            <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--            <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--        </div>-->
<!--        <div class="goods">-->
<!--          <div class="img-wrapper">-->
<!--            <img src="../assets/images/8-2.jpg">-->
<!--          </div>-->
<!--          <div class="info-wrapper">-->
<!--            <div class="title">-->
<!--              <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--              <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="price-wrapper">-->
<!--            <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--            <em>￥</em>-->
<!--            <span>159</span>-->
<!--          </div>-->
<!--          <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--          <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--        </div>-->
<!--        <div class="goods">-->
<!--        <div class="img-wrapper">-->
<!--          <img src="../assets/images/8-2.jpg">-->
<!--        </div>-->
<!--        <div class="info-wrapper">-->
<!--          <div class="title">-->
<!--            <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--            <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="price-wrapper">-->
<!--          <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--          <em>￥</em>-->
<!--          <span>159</span>-->
<!--        </div>-->
<!--        <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--        <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--      </div>-->
<!--        <div class="goods">-->
<!--        <div class="img-wrapper">-->
<!--          <img src="../assets/images/8-2.jpg">-->
<!--        </div>-->
<!--        <div class="info-wrapper">-->
<!--          <div class="title">-->
<!--            <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--            <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="price-wrapper">-->
<!--          <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--          <em>￥</em>-->
<!--          <span>159</span>-->
<!--        </div>-->
<!--        <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--        <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--      </div>-->
<!--        <div class="goods">-->
<!--          <div class="img-wrapper">-->
<!--            <img src="../assets/images/8-2.jpg">-->
<!--          </div>-->
<!--          <div class="info-wrapper">-->
<!--            <div class="title">-->
<!--              <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--              <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="price-wrapper">-->
<!--            <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--            <em>￥</em>-->
<!--            <span>159</span>-->
<!--          </div>-->
<!--          <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--          <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--        </div>-->
<!--        <div class="goods">-->
<!--          <div class="img-wrapper">-->
<!--            <img src="../assets/images/8-2.jpg">-->
<!--          </div>-->
<!--          <div class="info-wrapper">-->
<!--            <div class="title">-->
<!--              <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--              <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="price-wrapper">-->
<!--            <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--            <em>￥</em>-->
<!--            <span>159</span>-->
<!--          </div>-->
<!--          <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--          <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--        </div>-->
<!--        <div class="goods">-->
<!--        <div class="img-wrapper">-->
<!--          <img src="../assets/images/8-2.jpg">-->
<!--        </div>-->
<!--        <div class="info-wrapper">-->
<!--          <div class="title">-->
<!--            <h3 style="color: #ff5000">巧克力蛋糕</h3>-->
<!--            <h5 style="margin-left: 2px;margin-top: 15px">数量: </h5>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="price-wrapper">-->
<!--          <h5 style="margin-left: 2px;display: inline-block">单价: </h5>-->
<!--          <em>￥</em>-->
<!--          <span>159</span>-->
<!--        </div>-->
<!--        <el-button type="danger" style="margin-top: 5px">立即购买</el-button>-->
<!--        <el-button type="primary" style="margin-left: 10px">加入购物车</el-button>-->
<!--      </div>-->
<!--    </div>-->
<!--&lt;!&ndash;    尾页&ndash;&gt;-->
<!--    <div style="width: 100%;height: 50px;background-color: gray">-->
<!--        <span style="text-align: center;color: white;line-height: 50px"><h3>欢迎来到蛋糕商城首页</h3></span>-->
<!--    </div>-->
</template>

<script>
export default {
  name: "index",
  components:{
  }
//   data() {
//     return {
//       activeIndex2: '1',
//       // imgwrap:[
//       //   {url:require("../assets/images/l1.jpg")},
//       //   {url:require("../assets/images/l2.jpg")},
//       //   {url:require("../assets/images/l3.jpg")},
//       // ],
//     };
//   },
//   methods: {
//     handleSelect(key, keyPath) {
//       console.log(key, keyPath);
//     }
//   }
}
</script>

<!--<style scoped>-->
<!--.goods_list{-->
<!--  display: flex;-->
<!--  align-items: flex-start;-->
<!--  flex-wrap: wrap;-->
<!--}-->
<!--.tag-list {-->
<!--  height: 20px;-->
<!--  overflow: hidden;-->
<!--}-->
<!--.info-wrapper .title {-->
<!--  display: inline-block;-->
<!--  max-height: 146px;-->
<!--  margin: 6px 0 2px 0;-->
<!--  /*overflow: hidden;*/-->
<!--  color: #333;-->
<!--  font-size: 16px;-->
<!--  line-height: 23px;-->
<!--}-->
<!--.info-wrapper {-->
<!--  height: 80px;-->
<!--  margin-left: 162px;-->
<!--  overflow: hidden;-->
<!--}-->

<!--.price-wrapper {-->
<!--  margin-bottom: 20px;-->
<!--  margin-left: 162px;-->
<!--  color: #ff5000;-->
<!--}-->
<!--#index{-->
<!--  /*background: url("../assets/images/cakebg.jpg");*/-->
<!--  height: 100%;-->
<!--  width: 100%;-->
<!--  position: absolute;-->
<!--  background-size: 100% 100%;-->
<!--}-->
<!--.img-wrapper {-->
<!--  background: #f4f4f4;-->
<!--  overflow: hidden;-->
<!--  float: left;-->
<!--}-->
<!--.img-wrapper img{-->
<!--  width: 150px;-->
<!--  height: 150px;-->
<!--  margin: 10px;-->
<!--  border-radius: 20px;-->
<!--  vertical-align: auto;-->
<!--}-->
<!--.el-carousel__item h3 {-->
<!--  color: #475669;-->
<!--  font-size: 14px;-->
<!--  opacity: 0.75;-->
<!--  line-height: 200px;-->
<!--  margin: 0;-->
<!--}-->
<!--.recommand{-->
<!--  height: 24px;-->
<!--  margin-bottom: 20px;-->
<!--  margin-left: 6px;-->
<!--}-->

<!--.recommand .rh-title{-->
<!--  color: black;-->
<!--  float: left;-->
<!--  font-weight: bold;-->
<!--  font-size: 24px;-->
<!--  line-height: 24px;-->
<!--}-->

<!--.logo{-->
<!--  height: 20px;-->
<!--  margin: 4px 0 0 6px;-->
<!--}-->

<!--.goods{-->
<!--  position: relative;-->
<!--  float: left;-->
<!--  width: 372px;-->
<!--  height: 174px;-->
<!--  overflow: hidden;-->
<!--  color: #333;-->
<!--  background-color: #f7f9fa;-->
<!--  border: 1px solid #f7f9fa;-->
<!--  border-radius: 12px;-->
<!--  cursor: pointer;-->
<!--  transition: all 0.5s;-->
<!--}-->
<!--</style>-->