<template>
  <el-container>
    <el-main>

      <el-row>
        <el-col :span="20" :offset="2">
          <el-card :body-style="{ padding: '0px' }" class="me-card">
            <img src="../assets/mount.jpg" class="me-card-pic">
          </el-card>
        </el-col>
      </el-row>

    </el-main>


    <el-aside width="30%">

      <el-row>
        <el-col style="margin-top: 20px">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>个人介绍</span>
              </div>
            </template>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag>
                  技术热情:
                </el-tag>
              </el-col>
              <el-col :span="16">主职 java 后端开发；对技术细节有极大的兴趣，不拘泥于应用，喜欢研究源码</el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag>
                  兴趣爱好:
                </el-tag>
              </el-col>
              <el-col :span="16">喜爱音乐，喜爱骑行；对游戏有极强的天赋；喜好激烈的竞技性项目</el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag>
                  未来规划:
                </el-tag>
              </el-col>
              <el-col :span="16">25岁前，java 中级开发，月薪 20k；30岁前，java 高级开发，月薪 50k；35岁前，架构师，月薪 100k；时刻保持技术热情，持续学习新知，并向外输出优质内容；争做斜杠青年</el-col>
            </el-row>

          </el-card>
        </el-col>

        <el-col style="margin-top: 20px">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>与我联系</span>
              </div>
            </template>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag type="info">
                  qq:
                </el-tag>
              </el-col>
              <el-col :span="16">1724003472</el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag type="info">
                  邮箱:
                </el-tag>
              </el-col>
              <el-col :span="16">zhangjia_good@126.com</el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top: 20px">
              <el-col :span="8">
                <el-tag type="info">
                  电话:
                </el-tag>
              </el-col>
              <el-col :span="16">18012224600</el-col>
            </el-row>
          </el-card>
        </el-col>

      </el-row>


    </el-aside>
  </el-container>

</template>

<script>


</script>

<style scoped>

  .bg-purple-dark {
    background: #99a9bf;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .me-card {
    margin-top: 3%;
    padding: 15px;
  }

  .me-card-pic {
    width: 100%;
    display: block;
    border-radius: 8px;
  }

  .box-card {
    margin-top: 20px;
    margin-right: 30px;
    margin-left: 30px;
  }

  /*.box-card:hover {*/
  /*  margin-top: -5px;*/
  /*}*/
  /*.el-card {*/
  /*  min-width: 380px;*/
  /*  margin-right: 20px;*/
  /*  transition: all .5s;*/
  /*}*/

  .card-header {
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    font-family: Snell Roundhand, cursive;
  }


</style>