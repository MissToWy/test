<template>

  <el-menu  background-color="#333744" text-color="#fff"
            class="el-menu-vertical-demo"
            router
            :default-openeds="[1]"
            style="width: 200px;min-height: calc(100vh - 50px)"
  @open="handleOpen" @close="handleClose">
    <el-sub-menu index="1" v-if="gl==1">
      <template #title>系统管理</template>
      <el-menu-item-group>
        <el-menu-item index="user">用户管理</el-menu-item>
      </el-menu-item-group>

    </el-sub-menu>
    <el-menu-item-group>
      <el-menu-item index="book">书籍</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="fx">分享中心</el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Aside",
  data(){
    return{
      user:{},
      gl:0
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  created() {
    let userres=sessionStorage.getItem('user') || "{}"
    this.user=JSON.parse(userres)
     request.post('/user/sele',this.user.id).then(res =>{
        this.gl = res.data.gl
     })
  }
}

</script>

<style scoped>

</style>