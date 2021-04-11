<template>

    <el-container class="docEditPlace" direction="vertical">
        <el-row>
            <el-col :span="20" :offset="2">
                <el-input placeholder="请输入标题" v-model="input1">
                    <template #prepend>Title:</template>
                </el-input>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="20" :offset="2">
                <el-input
                        placeholder="请输入文章描述"
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        v-model="input1">
                </el-input>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="20" :offset="2">
                <el-button type="primary" icon="el-icon-view">预览</el-button>

                <el-button type="primary" icon="el-icon-check">保存</el-button>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="20" :offset="2">
                <div id="content"></div>
            </el-col>
        </el-row>

    </el-container>


</template>

<script>
    // 管理员文档编辑界面

    import E from 'wangeditor';
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import { ElMessageBox } from 'element-plus';

    export default {
        name: "admin-doc-edit",

        setup() {
            /**
             * ------变量区------
             */
            const editor = new E('#content');

            const doc = ref();
            doc.value={};

            const content = ref();
            content.value = {};


            /**
             * ------函数区------
             */

            onMounted(() => {
                editor.create();
            });


            /**
             * ------返回------
             */
            return {

            }
        }
    }

</script>


<style scoped>
    .docEditPlace .el-row{
        margin-top: 20px;
    }

</style>