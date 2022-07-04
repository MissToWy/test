<template>
  <div style="width: 100%;height: 100vh; background-color: darkslateblue;overflow: hidden">
    <div style="width: 400px;margin: 150px auto;">
      <div style="color: aquamarine;font-size: 30px;text-align: center;padding: 30px 0;">欢迎注册</div>
      <el-form size="normal" ref="form" v-model="form" >
        <el-form-item>
          <el-input placeholder="请输入用户名" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="请输入年龄" v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="请输入昵称" v-model="form.nikeName"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="未知">未知</el-radio>
        </el-form-item>
        <el-form-item>
          <el-input  placeholder="请输入密码" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input  placeholder="请再次输入密码" v-model="da"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="请输入地址" type="textarea" v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;" type="primary" @click="Register">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      da:null,
      form:{},
    }
  },
  methods: {
    Register() {
      if (this.form.password==null){
        this.$message({
          type:'error',
          message:'密码未填写，请填写'
        })
        return
      }
      if (this.form.password!==this.da){
        console.log()
        this.$message({
          type:'error',
          message:'两次密码不相同，请重试'
        })
        return
      }else if(this.form.username==null){
        this.$message({
          type:'error',
          message:'用户名未填写，请填写'
        })
        return
      }else if(this.form.age==null){
        this.$message({
          type:'error',
          message:'年龄未填写，请填写'
        })
        return
      }else if(this.form.nikeName==null){
        this.$message({
          type:'error',
          message:'昵称未填写，请填写'
        })
        return
      }else if(this.form.sex==null){
        this.$message({
          type:'error',
          message:'性别未填写，请填写'
        })
        return
      }else if(this.form.address==null){
        this.$message({
          type:'error',
          message:'地址未填写，请填写'
        })
        return
      }else{
        request.post('/user/Register',this.form).then(res => {
          if(res.code==='0'){
            this.$message({
              type:'success',
              message:'注册成功'
            })
            this.$router.push('/login')
          }else if (res.code==='-1'){
            this.$message({
              type:'error',
              message:res.msg
            })
          }else {
            this.$message({
              type:'error',
              message:res.msg
            })
          }
        })
      }
      }


  }
}
</script>

<style scoped>

</style>