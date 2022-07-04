<template>

  <div style="padding-left: 10px;height: calc(100vh - 50px)">
    <div style="margin: 10px 0;float: left">
      <el-button type="primary" @click="add">增加</el-button>
    </div>
    <div style=" margin: 10px 0; padding-right: 10px; float: right">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 200px" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%;">
<!--      border:边框 ; stripe:是否为斑马纹table;-->
      <el-table-column prop="id" label="id" ></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="nikeName" label="昵称"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column fixed="right"  label="操作">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
               <el-button type="text" size="small" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
      <el-dialog title="请填写信息" v-model="dialogFormVisible"  width="30%">
        <el-form v-model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nikeName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
              <el-radio v-model="form.sex" label="男">男</el-radio>
              <el-radio v-model="form.sex" label="女">女</el-radio>
              <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer" style="margin-left: 160px">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save" >确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export  default {
  name:'Home',
  components:{

  },
  data() {
    return {
      form:{},
      dialogFormVisible:false,
      search:'',
      currentPage:1,
      pageSize:5,
      total:0,
      tableData:[],
      gl:0
    }
  },
  created() {
    this.load();
  },
  methods: {
    add() {
      this.dialogFormVisible=true
      this.form={}
    },
    save() {
      if (this.form.id){
        request.put("/user",this.form).then(res =>{
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:'success',
              message:'修改成功'
            })
          }else {
            this.$message({
              type:'error',
              message:res.msg
            })
          }
          this.load()
          this.dialogFormVisible=false
        })
      }else{
        request.post("/user",this.form).then(res =>{
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:'success',
              message:'新增成功'
            })
          }else {
            this.$message({
              type:'error',
              message:res.msg
            })
          }
          this.load()
          this.dialogFormVisible=false
        })
      }

    },
    load() {
      request.get("/user",{
        params: {
        pageNum:this.currentPage,
        pageSize:this.pageSize,
        search:this.search  }
      }).then(res =>{
        this.tableData=res.data.records
        this.total=res.data.total
        console.log(res)
      })

    },
    handleEdit(row) {
      this.form=JSON.parse(JSON.stringify(row))//深拷贝
      this.dialogFormVisible=true

    },
    handleDelete(id) {
      request.delete('/user',{params:{id:id}
      }).then( res =>{
        console.log(res)
        if (res.code==='0'){
          this.$message({
            type:"success",
            message:'删除成功'
          })
        }else {
          this.$message({
            type:"error",
            message:this.msg
          })
        }
        this.load()
      })
    },
    handleSizeChange() {
      this.load()
    },
    handleCurrentChange() {
      this.load()
    }

  }
}

</script>
