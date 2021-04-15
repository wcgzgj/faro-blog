<template>

    <el-container>
        <el-main>
            <el-row>
                <el-col :span="18" :offset="3" style="margin-top: 30px">



                    &nbsp;&nbsp;

                    <el-form :inline="true" :model="formInline" class="demo-form-inline">

                        <el-form-item>
                            <el-input v-model="searchElem" placeholder="请输入内容"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button
                                    type="primary"
                                    @click.native="handelSearch"
                                    icon="el-icon-search">
                                搜索
                            </el-button>
                        </el-form-item>

                        <el-form-item>
                            <router-link :to="'/admin/docEdit'">
                                <el-button type="primary" icon="el-icon-plus">
                                    新增
                                </el-button>
                            </router-link>
                        </el-form-item>

                    </el-form>
                </el-col>
                <el-col :span="18" :offset="3" style="margin-top: 30px ; margin-bottom: 30px">
                    <el-table
                            v-loading="loading"
                            :data="docList"
                            border
                            style="width: 100%; text-align: center">
                        <el-table-column
                                label="名称"
                        >
                            <template #default="scope">
                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="介绍"
                        >
                            <template #default="scope">
                                <span style="margin-left: 10px">{{ scope.row.description }}</span>
                            </template>
                        </el-table-column>

                        <el-table-column
                                label="操作"
                        >
                            <template #default="scope">

                                <router-link :to="'/admin/docEdit?docId='+scope.row.id">
                                    <el-button
                                            size="mini"
                                            @click="">编辑</el-button>
                                </router-link>
                                &nbsp;&nbsp;
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>

            <!--分页-->
            <el-row :gutter="20" style="margin-bottom: 35px">
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

    </el-container>




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

            const searchElem = ref();
            searchElem.value="";



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
            pageSize.value = 5;


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


            const handleEdit=(index: any, row: any) =>{
               /**
                * 点击编辑按钮，会直接路由跳转
                * 跳转到编辑页面
                * 所以用不到 handleEdit 函数
                * 但是放在这里，是为了方便以后如果要复制这个组件
                * 可以再复用
                */
            }

            /**
             * 文章删除功能
             */
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
                            handelOpen(1,5);
                        } else {
                            ElMessage.error("删除失败！")
                        }
                    });
                }).catch(() => {
                    ElMessage("已取消删除")
                });
            }


            /**
             * 文章检索功能
             */
            const handelSearch = () => {
                loading.value=true;
                axios.get("/doc/list",{
                    params: {
                        page: 1,
                        size: 1000,
                        name: searchElem.value
                    }
                }).then( (resp)=> {
                    const data = resp.data;
                    if (data.success) {
                        loading.value = false;
                        docList.value = data.content.list;
                        total.value=data.content.total;
                        alert(pageSize.value)
                    } else {
                        ElMessage.error("查询无果！");
                    }
                });
            }


            onMounted(() => {
               handelOpen(pageNum.value,pageSize.value);
            });


            /**
             * ------返回------
             */
            return {
                docList,
                loading,
                searchElem,
                pageNum,
                pageSize,
                total,


                handleEdit,
                handleDelete,
                handelSearch,
                handleCurrentChange
            }
        }
    }
</script>

<style scoped>

</style>