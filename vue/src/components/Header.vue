<template>
  <div id="header" style="height: 50px;line-height: 50px; border-bottom: 1px solid black;display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold;color: aqua">菜单</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;padding-top: 20px">
      <el-dropdown>
        <span class="el-dropdown-link" style="padding-top: 10px">
          {{ user.nikeName }}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/tr')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
           </el-dropdown-menu>
        </template>
      </el-dropdown>

    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      user:{},
    }
  },
  created() {
    let userres=sessionStorage.getItem('user') || "{}"
    this.user=JSON.parse(userres)
  },



}
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>