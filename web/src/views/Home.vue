<template>

  <el-container v-loading="loading">

    <el-main>
      <el-row :gutter="20" v-for="docItem in docList">
        <el-col :span="20" :offset="2">

          <!--文章内容显示-->
          <el-card :body-style="{ padding: '0px' ,marginTop: '20px'}" class="artical-card">


            <el-container>

              <el-container>
                <el-header style="height: 40px ">
                  <!--<el-link href="#" target="_blank"-->
                  <!--         style=" font: 18px large ; font-weight: bold"-->
                  <!--         @click="handelDocClick(docItem.id)"-->
                  <!--&gt;-->

                  <!--</el-link>-->

                  <router-link
                               :to="'/admin/docEdit?docId='+docItem.id"
                               style=" font: 18px large ; font-weight: bold; text-decoration: none"
                  >
                    {{docItem.name}}
                  </router-link>

                </el-header>

                <el-main>
                  <span style="font-weight: bold ; color: cornflowerblue ">
                    文章介绍：
                  </span>
                  <br/>
                  <span>
                    {{docItem.description}}
                  </span>
                </el-main>

              </el-container>
              <el-aside width="200px">
                <div class="block">
                  <el-image :src="'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'" class="artical-pic">
                  </el-image>
                </div>
              </el-aside>
            </el-container>


          </el-card>
        </el-col>

      </el-row>

      <el-row :gutter="20">
        <el-col :span="20" :offset="2">
          <!--分页-->
          <el-pagination
                  background
                  layout="prev, pager, next"
                  :total="50">
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
import { ElMessage } from 'element-plus'

export default defineComponent({
  name: 'Home',
  components: {

  },

  setup() {

    /**
     * ------数据定义------
     */
    const loading=ref();
    loading.value=true;

    const docList= ref();
    docList.value={};

    const docPath = ref();
    docPath.value='/about';



    /**
     * ------方法------
     */

    /**
     * 初始打开页面时，进行查询操作
     */
    const handelOpen= () => {
      loading.value=true;
      axios.get("/doc/list").then( (resp) => {
        const data = resp.data;
        if (data.success) {
          loading.value=false;
          docList.value=data.content;
        } else {
          ElMessage("加载错误！")
        }
      });
    }


    onMounted(()=> {
      handelOpen();
    })

    return {
      docList,
      loading,
      docPath
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
    height: 200px;
    margin: 10px;
  }

  .artical-pic {
    margin: 20px;
    border-radius: 5%;
  }
</style>
