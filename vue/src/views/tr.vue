<template>
  <div style="width: 100%;background-color: #409EFF;height: 100vh;">
  <center>

    <h2 style="padding-top: 100px;">个人信息</h2>
    <p>你是个人</p>
    <div style="width: 25%;margin: auto;padding-top: 20px;">
      <p style="color: lawngreen"><span style="float: left">用户名</span><span style="float: right">{{form.username}}</span></p><br/><br/>
      <p style="color: lawngreen"><span style="float: left">昵称</span><span style="float: right">{{form.nikeName}}</span></p><br/><br/>
      <p style="color: lawngreen"><span style="float: left">年龄</span><span style="float: right">{{form.age}}</span></p><br/><br/>
      <p style="color: lawngreen"><span style="float: left">性别</span><span style="float: right">{{form.sex}}</span></p><br/><br/>
      <p style="color: lawngreen"><span style="float: left">地址</span><span style="float: right">{{form.address}}</span></p><br/><br/>
      <p style="color: lawngreen"><span style="float: left">身份</span><span style="float: right">{{form.gl==0?'普通用户':'管理员'}}</span></p><br/><br/>
      <el-button type="primary" round style="color: lawngreen;" @click="this.dialogFormVisible=true">点击修改</el-button>
      <el-button type="primary" round style="color: lawngreen;" @click="$router.push('/')">返回首页</el-button>
    </div>
  </center>
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

</template>

<script>
import request from "@/utils/request";

export default {
  name: "tr",
  data(){
    return {
      user:{},
      form:{},
      dialogFormVisible:false,
    }
  },
  methods:{
    save() {
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
    },
    load(){
      request.post('/user/sele',this.user.id).then(res =>{
        this.form = res.data
      })
    }
  },
  created() {
    let userres=sessionStorage.getItem('user') || "{}"
    this.user=JSON.parse(userres)
    request.post('/user/sele',this.user.id).then(res =>{
      this.form = res.data
      this.user=res.data
    })
  }
}
</script>

<style scoped>

</style>