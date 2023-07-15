<template>
  <div>

    <div style="margin: 10px 0;margin-left: 78%">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <el-table  tooltip-effect="dark" :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column prop="id" label="订单编号" width="140"></el-table-column>
      <el-table-column prop="total" label="商品总额" width="120"></el-table-column>
      <el-table-column prop="datetime" label="订单日期" width="140">
        <template slot-scope="scope">
          {{scope.row.datetime | dataFormat}}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="phone" label="手机号码"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="name1" label="商品名"></el-table-column>
      <el-table-column prop="status" label="状态">
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
          <el-button type="primary" @click="shipments(scope.row)">发货 <i class="el-icon-s-comment"></i></el-button>
          <el-button type="warning" @click="checkOrder(scope.row)">审核 <i class="el-icon-s-check"></i></el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="editOrder(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="deleteUser(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
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

    <el-dialog title="订单信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small" >
        <el-form-item label="订单编号" :required="true">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="收货人" :required="true">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" :required="true">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :required="true">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "OrderListAdmin",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      name: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg:'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods:{
    load() {
      axios.get("http://localhost:9090/orderadmin/pagefind", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name:this.name
        }
      }).then(res => {
        console.log(res.data)
        this.tableData = res.data.data
        this.total = res.data.total

      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    editOrder(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true;
      console.log(this.form)
    },
    save(){
      axios.post("http://localhost:9090/orderadmin",this.form).then(res=>{
        console.log(res.data)
        if(res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }

      })
    },
    checkOrder(row){
      console.log(row.status)
      if(row.status == 4){
        axios.get("http://localhost:9090/orderadmin/checkOrder/"+row.id).then(res => {
          console.log(res.data)
          if(res.data){
            this.$message.success("审核成功")
            this.load()
          }else {
            this.$message.error("审核失败")
          }
        })
      }else{
        this.$message.error("请不要重复操作，或者操作异常")
      }

    },
    shipments(row){
      if(row.status == 1){
        axios.get("http://localhost:9090/orderadmin/shipments/"+row.id).then(res => {
          console.log(res.data)
          if(res.data){
            this.$message.success("发货成功")
            this.load()
          }else {
            this.$message.error("发货失败")
          }
        })
      }else{
        this.$message.error("请不要重复操作，或者操作异常")
      }
    }
  }
}
</script>

<style scoped>
.headerBg {
  background: #eee!important;
}
</style>