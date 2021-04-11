<template>

  <el-container>
    <!--<el-aside width="200px" style="background-color: rgb(238, 241, 246)">-->
    <!--  <el-menu-->
    <!--          :uniqueOpened="true"-->
    <!--          default-active="2"-->
    <!--          class="el-menu-vertical-demo"-->
    <!--          @open="handleOpen"-->
    <!--          @close="handleClose"-->
    <!--          background-color="#545c64"-->
    <!--          text-color="#fff"-->
    <!--          active-text-color="#ffd04b">-->
    <!--    <el-submenu index="1">-->
    <!--      <template #title>-->
    <!--        <i class="el-icon-location"></i>-->
    <!--        <span>导航一</span>-->
    <!--      </template>-->
    <!--      <el-menu-item-group>-->
    <!--        <template #title>分组一</template>-->
    <!--        <el-menu-item index="1-1">选项1</el-menu-item>-->
    <!--        <el-menu-item index="1-2">选项2</el-menu-item>-->
    <!--      </el-menu-item-group>-->
    <!--      <el-menu-item-group title="分组2">-->
    <!--        <el-menu-item index="1-3">选项3</el-menu-item>-->
    <!--      </el-menu-item-group>-->
    <!--      <el-submenu index="1-4">-->
    <!--        <template #title>选项4</template>-->
    <!--        <el-menu-item index="1-4-1">选项1</el-menu-item>-->
    <!--      </el-submenu>-->
    <!--    </el-submenu>-->
    <!--    <el-menu-item index="2">-->
    <!--      <i class="el-icon-menu"></i>-->
    <!--      <template #title>导航二</template>-->
    <!--    </el-menu-item>-->
    <!--    <el-menu-item index="3" disabled>-->
    <!--      <i class="el-icon-document"></i>-->
    <!--      <template #title>导航三</template>-->
    <!--    </el-menu-item>-->
    <!--    <el-menu-item index="4">-->
    <!--      <i class="el-icon-setting"></i>-->
    <!--      <template #title>导航四</template>-->
    <!--    </el-menu-item>-->
    <!--    <el-submenu index="5">-->
    <!--      <template #title>-->
    <!--        <i class="el-icon-location"></i>-->
    <!--        <span>导航一</span>-->
    <!--      </template>-->
    <!--      <el-menu-item-group>-->
    <!--        <template #title>分组一</template>-->
    <!--        <el-menu-item index="5-1">选项1</el-menu-item>-->
    <!--        <el-menu-item index="5-2">选项2</el-menu-item>-->
    <!--      </el-menu-item-group>-->
    <!--      <el-menu-item-group title="分组2">-->
    <!--        <el-menu-item index="5-3">选项3</el-menu-item>-->
    <!--      </el-menu-item-group>-->
    <!--    </el-submenu>-->
    <!--  </el-menu>-->
    <!--</el-aside>-->

    <el-main>
      <el-row :gutter="20" v-for="(o, index) in 2" :key="o" :offset="index > 0 ? 2 : 0">

        <el-col :span="20" :offset="2">
          <el-card :body-style="{ padding: '0px' ,marginTop: '20px'}" class="artical-card">
            <el-container>
              <el-header style="height: 40px">SpringBoot 教程</el-header>
              <el-container>
                <el-aside width="200px">
                  <div class="block">
                    <el-image :src="'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'"></el-image>
                  </div>
                </el-aside>
                <el-main>
                  文章介绍
                </el-main>
              </el-container>
            </el-container>

          </el-card>
        </el-col>

      </el-row>
    </el-main>

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

export default defineComponent({
  name: 'Home',
  components: {

  },

  setup() {



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
</style>
