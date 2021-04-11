<template>
    <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
        <el-menu-item index="1" text-color="#303133">
            <el-link :underline="false" icon="el-icon-video-camera-solid">
                FARO_Z的博客
            </el-link>
        </el-menu-item>

        <el-submenu index="2">
            <template #title>我的工作台</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
                <template #title>选项4</template>
                <el-menu-item index="2-4-1">选项1</el-menu-item>
                <el-menu-item index="2-4-2">选项2</el-menu-item>
                <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
        </el-submenu>
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
</template>

<script>
    import { defineComponent, onMounted, ref, createVNode } from 'vue';
    import axios from 'axios';
    export default {
        name: "the-header",

        setup() {

            onMounted(()=> {

            });

            return {

            }
        }
    }
</script>

<style scoped>

</style>