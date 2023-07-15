<template>
  <div>
    <el-table  tooltip-effect="dark" :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column  type="selection" prop="id" label="商品ID" width="80"></el-table-column>
      <el-table-column prop="id" label="商品编号" width="140"></el-table-column>
      <el-table-column prop="images1" label="图片" width="140">
        <!-- 通过 Scoped slot 可以获取到 row, column, $index 和 store（table 内部的状态管理）的数据，用法参考 demo。 -->
        <!-- slot插槽可以传递任何属性或html元素，但是在调用组件的页面中我们可以使用 template slot-scope="props"来获取插槽上的属性值，获取到的值是一个对象。 -->
        <template slot-scope="scope">
          <!-- scope.row获取当前行所有数据 作用域插槽获取当前行的数据（可以是图片连接如下：:src="scope.row.iconImgLarge"） -->
          <el-image style="width: 100px; height: 100px" :src=" require(`../assets${scope.row.images1}`)" fit=""></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品名" width="140"></el-table-column>
      <el-table-column prop="price" label="价格" width="120"></el-table-column>
      <el-table-column prop="intro" label="商品介绍"></el-table-column>
      <el-table-column prop="stock" label="库存"></el-table-column>
      <el-table-column prop="typeid" label="类型编号"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="editGoods(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="deleteGoods(scope.row.id)"
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
    <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="商品名">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品介绍">
          <el-input v-model="form.intro" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model="form.stock" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型编号">
          <el-input v-model="form.typeid" autocomplete="off"></el-input>
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
  name: "Goods",
  created() {
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
      headerBg:'headerBg',
    }
  },
  methods:{
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    load() {
      axios.get("http://localhost:9090/goods", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
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
    editGoods(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true;
    },
    deleteGoods(id){
      axios.delete("http://localhost:9090/goods/"+id).then(res=>{
        if(res.data.code == 200){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.success("删除失败")
        }
      })
    },
    save(){
      //正则表达式判断输入金额
      var r = /^-?[1-9]\d*\.\d+$|^-?0\.\d+$|^-?[1-9]\d*$|^0$/
      var r1 = /^\+?[1-9][0-9]*$/;
       if(!r.test(this.form.price)){
          this.$message.error("请正确输入金额！！！！")
          return;
       }
      if(!r1.test(this.form.stock)){
        this.$message.error("请正确输入库存数量，库存数量只能是整数！！！！")
        return;
      }
      if(!r1.test(this.form.typeid)){
        this.$message.error("请正确输入类型编号，类型编号只能说整数！！！！")
        return;
      }
      axios.put("http://localhost:9090/goods",this.form).then(res=>{
        if(res.data.code==200){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.success("保存失败")
        }

      })
    }
  }
}
</script>

<style scoped>
.headerBg {
  background: #eee!important;
}
</style>