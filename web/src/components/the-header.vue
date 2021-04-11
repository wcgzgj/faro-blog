<template>
    <el-menu
            class="the-header"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            router
            active-text-color="#ffd04b">
        <el-menu-item index="/" text-color="#303133">
            <el-link :underline="false" >
                FARO_Z的博客
            </el-link>
        </el-menu-item>

        <el-menu-item index="/" >
            <el-link :underline="false" icon="el-icon-s-home">
                首页
            </el-link>
        </el-menu-item>
        <el-menu-item index="/admin/doc">
            <el-link :underline="false" icon="el-icon-s-management">
                文章管理
            </el-link>
        </el-menu-item>
        <el-menu-item index="/admin/user">
            <el-link :underline="false" icon="el-icon-user-solid">
                用户管理
            </el-link>
        </el-menu-item>
        <el-menu-item index="/admin/status">
            <el-link :underline="false" icon="el-icon-s-marketing">
                报表统计
            </el-link>
        </el-menu-item>
        <el-menu-item index="/about">
            <el-link :underline="false" icon="el-icon-info">
                关于我
            </el-link>
        </el-menu-item>
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