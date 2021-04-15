<template>

  <el-container v-loading="loading">

    <el-main>

      <!--文章内容显示-->
      <el-row :gutter="20" v-for="docItem in docList" :key="docItem.id">

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
                               :to="'/doc?docId='+docItem.id"
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

      <!--分页-->
      <el-row :gutter="20">
        <el-col :span="20" :offset="2">
          <!--分页-->
          <el-pagination
                  @current-change="handleCurrentChange"
                  :current-page="pageNum"
                  :page-size="pageSize"
                  background
                  layout="prev, pager, next"
                  :total="total">
          </el-pagination>


        </el-col>

      </el-row>

    </el-main>


    <!--侧边导航栏-->
    <el-sider style="width: 20%">

      <el-row>
        <el-col>
          <el-card :body-style="{ padding: '0px' }" style="text-align: center; margin: 20px">
            <img src="../assets/me.jpg" class="card-image">
          </el-card>
        </el-col>


        <el-col>
          <el-card :body-style="{ padding: '0px' }" style="text-align: center; margin: 20px">
            <div style="border-radius: 4px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); padding: 10px">
              我是练习时长一年左右的 java 练习生 FARO_Z，喜欢 web 开发，徒手造轮子，手撕源码
            </div>
          </el-card>
        </el-col>

        <el-divider><i class="el-icon-mobile-phone"></i>扫码关注我</el-divider>

        <el-col>
          <el-card :body-style="{ padding: '0px' }" style="text-align: center; margin: 20px">
            <img src="../assets/weixin-artical.jpg" class="card-image">
          </el-card>
        </el-col>


      </el-row>

    </el-sider>


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

    const dateValue = ref();
    dateValue.value = new Date();





    /**
     * ------方法------
     */


    /**
     * 分页功能
     */
    //总内容属，返回数据库中所有行的个数，方便页码的更新
    const total = ref();
    total.value = 5;

    const pageNum = ref();
    pageNum.value = 1;

    const pageSize = ref();
    pageSize.value = 4;


    const handleCurrentChange = (currentPage) => {
      pageNum.value = currentPage
      handelOpen(pageNum.value,pageSize.value)
    }

      //获取数据
    const handelOpen = (pageNum, pageSize) => {
      loading.value=true;
      axios.get("/doc/list",{
        params: {
          page: pageNum,
          size: pageSize
        }
      }).then( (resp) => {
        const data = resp.data;
        if (data.success) {
          loading.value=false;
          docList.value=data.content.list;
          total.value=data.content.total;
        } else {
          ElMessage("加载错误！")
        }
      });
    }


    const toMyGithub = () => {
      window.open("https://github.com/wcgzgj","_blank")
    }




    onMounted(()=> {
      handelOpen(pageNum.value,pageSize.value);
    })

    return {
      docList,
      loading,
      docPath,
      dateValue,
      total,
      pageNum,
      pageSize,

      handleCurrentChange,
      toMyGithub
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

  .me-image {
    margin: 4px;
    border-radius: 4%;
    width: 200px;
  }

  .card-image {
    width: 100%;
    display: block;
  }




</style>
