<template>
  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>
      <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="AddUser">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload
          action="http://localhost:9090/user/import" style="display: inline-block"
          :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess"
      >
        <el-button type="primary" style="margin-left: 6px">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" style="margin-left: 6px">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table  tooltip-effect="dark" :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column  type="selection" prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="password" label="密码" width="120"></el-table-column>
      <el-table-column prop="name" label="真实姓名"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="手机号码"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="isadmin" label="管理员" :formatter="formatBoolean"></el-table-column>
      <el-table-column prop="isvalidate" label="失效" :formatter="formatBoolean"></el-table-column>

      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="editUser(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="form.isadmin" placeholder="请选择">
            <el-option
                v-for="item in options"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="失效">
          <el-select v-model="form.isvalidate" placeholder="请选择">
            <el-option
                v-for="item in options1"
                :value="item.value">
            </el-option>
          </el-select>
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

  name: "User",
  data() {
    return {
      imageUrl: '',
      options: [{
        value: "true",
        label: '1'
      }, {
        value: "false",
        label: '2'
      }],
      options1: [{
        value: "false",
        label: '1'
      }, {
        value: "true",
        label: '2'
      }],
      value: '',
      value1: '',
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg:'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods: {
    /*布尔值格式化：cellValue为后台返回的值*/
    formatBoolean: function (row, column, cellValue) {
      var ret = ''  //你想在页面展示的值
      if (cellValue) {
        ret = "是"  //根据自己的需求设定
      } else {
        ret = "否"
      }
      return ret;
    },
    load() {
      // axios.get("http://localhost:9090/user/").then(res=>{
      //   this.tableData = res.data
      // })
     axios.get("http://localhost:9090/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email:this.email,
          address:this.address,
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
    AddUser(){
      this.dialogFormVisible = true
      this.form={}
    },
    editUser(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true;
      console.log(this.tableData)
    },
    deleteUser(id) {
        axios.delete("http://localhost:9090/user/"+id).then(res=>{
            if(res.data==1){
              this.$message.success("删除成功")
              this.load()
            }else{
              this.$message.error("删除失败")
            }
        })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
     axios.post("http://localhost:9090/user/del/batch", ids).then(res => {
       console.log("批量删除="+res.data);
        if (res.data) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    exp(){
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    },
    save(){
      axios.post("http://localhost:9090/user",this.form).then(res=>{
        if(res.data){
            this.$message.success("保存成功")
            this.dialogFormVisible = false
            this.load()
        }else{
          this.$message.error("保存失败")
        }

      })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>