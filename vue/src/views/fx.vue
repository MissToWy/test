<template>
  <div style="background-color: bisque;width: 100%;height: calc(100vh - 50px);">
    <p style="color: brown;font-size: large;">好书不怕难读，愿你能找到自己想要的书</p>
    <div style="width: 25%;margin: 20px auto auto;">

      <p style="font-size: xx-large;"><span style="float: left;">图书名称</span><span style="float: right;">作者</span></p><br/><br/><br/>
      <div v-for="i in sj" key="i">
        <p><span style="float: left;">{{ i.bookname }}</span><span style="float: right;margin-right: 10px;">{{i.zz}}</span></p>
        <br/><br/>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "fx",
  data(){
    return {
      sj:[],
    }
  },
  methods:{

  },
  created() {
    request.get('/book/fx').then(res =>{
      var s = res.data
      let len=s.length
      var sj=[]
      for (let i=0;i<len;i++){
        var valu = s[i]
        if (valu.fx=='是'){
          sj.push(valu)
        }
      }
      if (sj.length>10){
        for (let d=0;d<10;d++){
          this.sj.push(sj[d])
        }
      }else{
        for (let d=0;d<sj.length;d++){
          this.sj.push(sj[d])
        }
      }
    })

  }
}
</script>

<style scoped>

</style>