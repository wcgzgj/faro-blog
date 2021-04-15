<template>

    <el-container>
        <el-main>
            <el-row>
                <el-col :span="18" :offset="3" style="margin-top: 30px">

                    <el-form :inline="true" :model="formInline" class="demo-form-inline">

                        <el-form-item>
                            <el-input v-model="searchElem" placeholder="请输入登录名"></el-input>
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
                            <el-button
                                    type="primary"
                                    @click.native="handelAdd"
                                    icon="el-icon-plus">
                                新增
                            </el-button>
                        </el-form-item>
                    </el-form>

                </el-col>


                <el-col :span="18" :offset="3" style="margin-top: 30px ; margin-bottom: 30px">
                    <el-table
                            v-loading="loading"
                            :data="userList"
                            border
                            style="width: 100%; text-align: center">
                        <el-table-column
                                label="登录名"
                        >
                            <template #default="scope">
                                <span style="margin-left: 10px">{{ scope.row.loginName }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="昵称"
                        >
                            <template #default="scope">
                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                            </template>
                        </el-table-column>

                        <el-table-column
                                label="密码"
                        >
                            <template #default="scope">
                                <span style="margin-left: 10px">{{ scope.row.password }}</span>
                            </template>
                        </el-table-column>

                        <el-table-column
                                label="操作"
                                width="260px"
                        >
                            <template #default="scope">

                                <el-button
                                        size="mini"
                                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                <el-button
                                        size="mini"
                                        @click="handleReset(scope.$index, scope.row)">重置密码</el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>
        </el-main>
    </el-container>




    <el-dialog title="用户编辑" v-model="editDialogFormVisible">
        <el-form :model="user">
            <el-form-item label="登录名" >
                <el-input v-model="user.loginName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称" >
                <el-input v-model="user.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" v-show="!user.id">
                <el-input v-model="user.password" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>

        <template #footer>
            <span class="dialog-footer">
              <el-button @click="editDialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handelEditModalOk">确 定</el-button>
            </span>
        </template>
    </el-dialog>


    <el-dialog title="用户新增" v-model="addDialogFormVisible">
        <el-form :model="newUser">
            <el-form-item label="登录名" >
                <el-input v-model="newUser.loginName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称" >
                <el-input v-model="newUser.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="newUser.password" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>

        <template #footer>
            <span class="dialog-footer">
              <el-button @click="addDialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handelAddModalOk">确 定</el-button>
            </span>
        </template>
    </el-dialog>


    <el-dialog title="重置密码" v-model="resetDialogFormVisible">
        <el-form :model="newPasswordUser">
            <el-form-item label="密码">
                <el-input v-model="newPasswordUser.password" autocomplete="off" placeholder="请输入新密码"></el-input>
            </el-form-item>
        </el-form>

        <template #footer>
            <span class="dialog-footer">
              <el-button @click="resetDialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handelResetModalOk">确 定</el-button>
            </span>
        </template>
    </el-dialog>


    


</template>

<script lang="ts">
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import { ElMessageBox } from 'element-plus';
    import {Tool} from "@/util/tool";


    declare let hexMd5;
    declare let KEY;

    export default {
        name: "admin-user",

        setup() {
            /**
             * ------数据定义------
             */
            const userList= ref();
            userList.value=[];

            const loading=ref();
            loading.value=true;

            const searchElem = ref();
            searchElem.value="";

            //用户修改模态框可视化
            const editDialogFormVisible = ref();
            editDialogFormVisible.value=false;

            //用户新增模态框可视化
            const addDialogFormVisible = ref();
            addDialogFormVisible.value=false;

            //重置密码模态框
            const resetDialogFormVisible = ref();
            resetDialogFormVisible.value = false;

            //模态框中的用户信息
            const user = ref();
            user.value={};

            //新增用户
            const newUser = ref();
            newUser.value = {};

            //新密码
            const newPasswordUser = ref();
            newPasswordUser.value = {};





            /**
             * ------方法------
             */

            /**
             * 初始打开页面时，进行查询操作
             */
            const handelOpen= () => {
                loading.value=true;
                axios.get("/user/list",{
                    params: {
                        page: 1,
                        size: 1000
                    }
                }).then( (resp) => {
                    const data = resp.data;
                    if (data.success) {
                        loading.value=false;
                        userList.value=data.content.list;

                        console.log(userList.value)
                    } else {
                        ElMessage("加载错误！")
                    }
                });
            }

            /**
             * 用户编辑按钮
             */
            const handleEdit=(index: any, row: any) =>{

               editDialogFormVisible.value=true;
               user.value = Tool.copy(row)
            }

            /**
             * 重置密码按钮
             */
            const handleReset=(index: any, row: any) =>{
                resetDialogFormVisible.value=true;
                newPasswordUser.value = Tool.copy(row)
                newPasswordUser.value.password="";
            }


            /**
             * 文章新增功能
             */
            const handelAdd = () => {
                addDialogFormVisible.value=true;
                newUser.value={};
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
                    axios.delete("/user/delete/"+row.id).then((resp)=> {
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


            /**
             * 文章检索功能
             */
            const handelSearch = () => {
                loading.value=true;
                axios.get("/user/list",{
                    params: {
                        loginName: searchElem.value
                    }
                }).then( (resp)=> {
                    const data = resp.data;
                    if (data.success) {
                        loading.value = false;
                        userList.value = data.content.list;
                    } else {
                        ElMessage.error("查询无果！");
                    }
                });
            }


            /**
             * 确认编辑用户模态框的修改
             */
            const handelEditModalOk = () => {
                axios.post("/user/save",user.value).then((resp)=>{
                    const data = resp.data;
                    if (data.success) {
                        editDialogFormVisible.value = false;
                        handelOpen();
                    } else {
                        editDialogFormVisible.value = false;
                        ElMessage.error(data.message);
                    }
                })
            }


            /**
             * 确认新增用户模态框的修改
             */
            const handelAddModalOk = () => {

                /**
                 * 前端调用 MD5 加密算法
                 * KEY 是盐值，让密码更为复杂
                 */
                newUser.value.password = hexMd5(newUser.value.password + KEY);

                axios.post("/user/save",newUser.value).then((resp)=>{
                    const data = resp.data;
                    if (data.success) {
                        addDialogFormVisible.value = false;
                        handelOpen();
                    } else {
                        addDialogFormVisible.value = false;
                        ElMessage.error(data.message);
                    }
                })
            }


            /**
             * 确认重置密码模态框的修改
             */
            const handelResetModalOk = () => {

                newPasswordUser.value.password = hexMd5(newPasswordUser.value.password + KEY);

                axios.post("/user/reset-password",newPasswordUser.value).then((resp)=>{
                    const data = resp.data;
                    if (data.success) {
                        resetDialogFormVisible.value = false;
                        handelOpen();
                    } else {
                        addDialogFormVisible.value = false;
                        ElMessage.error(data.message);
                    }
                })
            }


            onMounted(() => {
               handelOpen();
            });


            /**
             * ------返回------
             */
            return {
                userList,
                loading,
                searchElem,
                editDialogFormVisible,
                addDialogFormVisible,
                user,
                newUser,
                resetDialogFormVisible,
                newPasswordUser,

                handleEdit,
                handleDelete,
                handelSearch,
                handelEditModalOk,
                handelAddModalOk,
                handelResetModalOk,
                handelAdd,
                handleReset
            }
        }
    }
</script>

<style scoped>

</style>