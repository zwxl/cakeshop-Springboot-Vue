<template>
  <div>
    <el-table  tooltip-effect="dark" :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column prop="id" label="商品分类编号" ></el-table-column>
      <el-table-column prop="typename" label="类型系列名称" ></el-table-column>
      <el-table-column label="操作"  align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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
      <el-table-column label="操作"  align="center">
        <template slot-scope="scope">
          <el-button type="warning" @click="Add">新增<i class="el-icon-plus"></i></el-button>
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
    <el-dialog title="分类信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="系列名称">
          <el-input v-model="form.typename" autocomplete="off"></el-input>
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
  name: "CateGoryAdmin",
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
      axios.get("http://localhost:9090/type/page", {
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
    Add(){
      this.dialogFormVisible = true
      this.form={}
    },
    edit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true;
    },
    deleteGoods(id){
      axios.delete("http://localhost:9090/type/"+id).then(res=>{
        console.log(res.data)
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    save(){

      axios.post("http://localhost:9090/type",this.form).then(res=>{
        console.log(res.data)
        if(res.data.code=='200'){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
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