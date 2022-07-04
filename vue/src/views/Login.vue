<template>
<div style="width: 100%;height: 100vh; background-color: darkslateblue;overflow: hidden">
  <div style="width: 400px;margin: 150px auto;">
    <div style="color: aquamarine;font-size: 30px;text-align: center;padding: 30px 0;">欢迎登录</div>
    <el-form size="normal" ref="form" v-model="form">
      <el-form-item>
        <el-input placeholder="请输入用户名" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input  placeholder="请输入密码" v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%;" type="primary" @click="login">登 录</el-button>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%;" type="primary" @click="$router.push('/register')">注 册</el-button>
      </el-form-item>

    </el-form>
  </div>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return{
      form:{},
      // rules: {
      //   username: [
      //     {required:true,message:'请输入用户名',trigger:'blur'}
      //   ],
      //   password: [
      //     {required:true,message:'请输入密码',trigger:'blur'}
      //   ]
      // }
    }
  },
  created() {
    sessionStorage.removeItem('user')
  },
  methods: {
    login() {
      // this.$refs['form'].validate((valid) =>{
      //   if(valid) {
          request.post('/user/login',this.form).then(res => {
            if (res.code==='0') {
              this.$message({
                type:'success',
                message:'登录成功'
              })
              sessionStorage.setItem('user',JSON.stringify(res.data))
              this.$router.push('/')//登录成功后进行页面跳转
            }else {
              this.$message({
                type:'error',
                message:res.msg
              })
            }
          })
        }
    //   })
    // }
  }
}
</script>

<style scoped>

</style>