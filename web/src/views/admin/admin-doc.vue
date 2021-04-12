<template>
    <el-table
            v-loading="loading"
            :data="docList"
            style="width: 100%">
        <el-table-column
                label="名称"
                width="180">
            <template #default="scope">
                <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </template>
        </el-table-column>
        <el-table-column
                label="介绍"
                width="180">
            <template #default="scope">
                <span style="margin-left: 10px">{{ scope.row.description }}</span>
            </template>
        </el-table-column>

        <el-table-column label="操作">
            <template #default="scope">

                <router-link :to="'/admin/docEdit?docId='+scope.row.id">
                    <el-button
                            size="mini"
                            @click="">编辑</el-button>
                </router-link>
                <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>

</template>

<script lang="ts">
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import { ElMessageBox } from 'element-plus';

    export default {
        name: "admin-doc",

        setup() {
            /**
             * ------数据定义------
             */
            const docList= ref();
            docList.value=[];

            const loading=ref();
            loading.value=true;



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

                        console.log(docList.value)
                    } else {
                        ElMessage("加载错误！")
                    }
                });
            }

            const handleEdit=(index: any, row: any) =>{
               /**
                * 点击编辑按钮，会直接路由跳转
                * 跳转到编辑页面
                * 所以用不到 handleEdit 函数
                * 但是放在这里，是为了方便以后如果要复制这个组件
                * 可以再复用
                */
            }

            const handleDelete=(index: any, row: any) =>{
                ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete("/doc/delete/"+row.id).then((resp)=> {
                        const data=resp.data;
                        if (data.success) {
                            ElMessage.success("删除成功！");
                            handelOpen();
                        } else {
                            ElMessage.error("删除失败！")
                        }
                    });
                }).catch(() => {
                    ElMessage("已取消删除")
                });
            }


            onMounted(() => {
               handelOpen();

            });


            /**
             * ------返回------
             */
            return {
                docList,
                loading,

                handleEdit,
                handleDelete
            }
        }
    }
</script>

<style scoped>

</style>