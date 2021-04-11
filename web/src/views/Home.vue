<template>

  <el-container>

    <el-main>
      <el-row :gutter="20" v-for="(o, index) in 8" :key="o" :offset="index > 0 ? 2 : 0">
        <el-col :span="20" :offset="2">

          <!--文章内容显示-->
          <el-card :body-style="{ padding: '0px' ,marginTop: '20px'}" class="artical-card">
            <el-container>
              <el-header style="height: 40px">
                <el-link href="https://element.eleme.io" target="_blank">
                  SpringBoot 教程
                </el-link>
              </el-header>
              <el-container>
                <el-aside width="200px">
                  <div class="block">
                    <el-image :src="'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'" class="artical-pic">
                    </el-image>
                  </div>
                </el-aside>
                <el-main>
                  文章介绍
                  <el-divider></el-divider>
                  <span>
                    这是一篇源于 SpringBoot的报告，讲述的，就是怎么使用 SpringBoot
                  </span>

                </el-main>
              </el-container>
            </el-container>

          </el-card>
        </el-col>

      </el-row>

      <el-row :gutter="20" style="float: right">
        <el-col :span="20" :offset="2">
          <!--分页-->
          <el-pagination
                  background
                  layout="prev, pager, next"
                  :total="1000">
          </el-pagination>
        </el-col>
      </el-row>

    </el-main>



    <!--侧边导航栏-->
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>卡片名称</span>
          <el-button class="button" type="text">操作按钮</el-button>
        </div>
      </template>
      <div v-for="o in 4" :key="o" class="text item">
        {{'列表内容 ' + o }}
      </div>
    </el-card>


  </el-container>



</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import axios from "axios";

export default defineComponent({
  name: 'Home',
  components: {

  },

  setup() {

    const docList= ref();
    docList.value={};
    /**
     * 初始打开页面时，进行查询操作
     */
    const handelOpen= () => {
      // axios.get()
    }

    onMounted(()=> {

    })

    return {

    }
  }
});
</script>

<style scoped>
  .el-menu-vertical-demo {
    height: 100%;
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .box-card {
    width: 20%;
  }

  .artical-card {
    height: 240px;
    margin: 10px;
  }

  .artical-pic {
    margin: 20px;
    border-radius: 5%;
  }
</style>
