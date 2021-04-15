<template>
  <el-container direction="vertical">
    <the-header></the-header>
    <router-view/>
    <the-footer></the-footer>
  </el-container>

</template>

<script lang="ts">
  import { defineComponent} from 'vue';
  import TheFooter from './components/the-footer.vue'
  import TheHeader from "./components/the-header.vue";

  export default defineComponent({
    name: 'App',
    components: {
      TheHeader,
      TheFooter
    },
  });

</script>


<style lang="scss">
  .the-footer, .the-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    margin: 0px;
  }

  /*.el-aside {*/
  /*  background-color: #D3DCE6;*/
  /*  color: #333;*/
  /*  text-align: center;*/
  /*  line-height: 200px;*/
  /*}*/

  /*.el-main {*/
  /*  background-color: #E9EEF3;*/
  /*  color: #333;*/
  /*  text-align: center;*/
  /*  line-height: 160px;*/
  /*}*/

  /*
	找到html标签、body标签，和挂载的标签
	都给他们统一设置样式
  */
  html,body,#app,.el-container{
    /*设置内部填充为0，几个布局元素之间没有间距*/
    padding: 0px;
    /*外部间距也是如此设置*/
    margin: 0px;
    /*统一设置高度为100%*/
    height: 100%;
  }

</style>
