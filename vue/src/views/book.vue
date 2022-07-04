<template>

  <div style="padding-left: 10px;height: calc(100vh - 50px)">
    <div style="margin: 10px 0;float: left">
      <el-button type="primary" @click="add">增加</el-button>
    </div>
    <div style="margin: 10px 0; padding-right: 10px; float: right">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 200px" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%;">
<!--      border:边框 ; stripe:是否为斑马纹table;-->
      <el-table-column prop="bookname" label="名称"></el-table-column>
      <el-table-column prop="zz" label="作者"></el-table-column>
      <el-table-column prop="money" label="单价"></el-table-column>
      <el-table-column prop="time" label="收藏时间"></el-table-column>
      <el-table-column prop="fx" label="是否共享"></el-table-column>
      <el-table-column fixed="right"  label="操作">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.bh)">
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
      <el-dialog title="请输入图书信息" v-model="dialogFormVisible"  width="30%">
        <el-form v-model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.bookname" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.zz" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="单价">
            <el-input v-model="form.money" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="是否共享">
            <el-radio v-model="form.fx" label="是">是</el-radio>
            <el-radio v-model="form.fx" label="否">否</el-radio>
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
      user:{},
    }
  },
  created() {
    let userres=sessionStorage.getItem('user') || "{}"
    this.user=JSON.parse(userres)
    this.load();
  },
  methods: {
    add() {
      this.dialogFormVisible=true
      this.form={}
    },
    save() {
      if (this.form.bh){
        request.put("/book",{bh:this.form.bh,zz:this.form.zz,fx:this.form.fx,bookname:this.form.bookname,money:this.form.money,guishu:this.user.id,time:new Date()}).then(res =>{
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
        request.post("/book",{zz:this.form.zz,bookname:this.form.bookname,fx:this.form.fx,money:this.form.money,guishu:this.user.id,time:new Date()}).then(res =>{
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
      request.get("/book",{
        params: {
        pageNum:this.currentPage,
        pageSize:this.pageSize,
        search:this.search,
        bh:this.user.bh}
      }).then(res =>{
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    handleEdit(row) {
      this.form=JSON.parse(JSON.stringify(row))//深拷贝
      this.dialogFormVisible=true

    },
    handleDelete(bh) {
      request.delete('/book',{params:{bh:bh}
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
