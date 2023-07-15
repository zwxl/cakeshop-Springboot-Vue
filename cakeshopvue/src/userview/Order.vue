<template>
  <div>
    <h3>订单管理</h3>
    <el-table  tooltip-effect="dark" :data="tableData" border stripe @selection-change="handleSelectionChange">
      <el-table-column prop="id" label="订单编号" width="140"></el-table-column>
      <el-table-column prop="name" label="商品名" width="140"></el-table-column>
      <el-table-column prop="total" label="价格" width="120"></el-table-column>
      <el-table-column prop="amount" label="商品数量"></el-table-column>
      <el-table-column prop="status" label="支付状态">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.status == 1">已付款</el-tag>
          <el-tag type="info" v-else-if="scope.row.status == 3">已发货</el-tag>
          <el-tag type="warning" v-else-if="scope.row.status == 2">待发货</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status == 0">未付款</el-tag>
          <el-tag type="success" v-else-if="scope.row.status == 5">审核通过</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status == 4">待审核</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="OrderItem(scope.row)">下单 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定退款吗？需要等待管理员审核"
              @confirm="deleteorder(scope.row)"
          >
            <el-button type="danger" slot="reference">退款 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0;text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Order",
  created() {
    this.token = localStorage.getItem("token")
    this.load()
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      token:''
    }
  },
  methods:{
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    load(){
      axios.post("http://localhost:9090/order", {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
      },{headers:{
          'Content-Type':"application/json;charset=UTF-8",
          'Authorization': this.token
        }}).then(res => {
          console.log(res.data.data)
        this.tableData = res.data.data
        this.total = res.data.total
      })
    },
    OrderItem(row){
      if(row.status == 1){
        this.$message.error("你已经支付，请不要重复支付")
        return ;
      }
      else if(row.status == 4){
        this.$message.error("你申请退款成功，不能重复退款")
        return ;
      }
      //
      this.$confirm('此操作将进行下单操作, 是否继续?', '下单提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        axios.get("http://localhost:9090/order/updateStatus/"+row.id).then(res => {
          console.log(res.data)
          if(res.data){
            this.$message.success("支付成功")
            this.load()
          }else {
            this.$message.error("支付失败")
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消支付'
        });
      });
      //
    },
    deleteorder(row){
     if(row.status == 4){
        this.$message.error("你已经申请退款，请等待管理审核")
        return ;
      }
      else if(row.status == 5){
        this.$message.error("你已经申请退款，请等待管理审核")
        return ;
      }
      axios.get("http://localhost:9090/order/updateStatus1/"+row.id).then(res => {
        console.log(res.data)
        if(res.data){
          this.$message.success("等待管理员审核")
          this.load()
        }else {
          this.$message.error("支付失败")
        }
      })
    }
  }
}
</script>

<style scoped>

h3{
  color: #8d3608;
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
  margin-bottom: 10px;
}
</style>