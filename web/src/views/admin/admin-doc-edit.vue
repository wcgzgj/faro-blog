<template>

    <el-container class="docEditPlace" direction="vertical" v-loading="loading">
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
    import {useRoute} from "vue-router";


    export default {
        name: "admin-doc-edit",

        setup() {
            /**
             * ------变量区------
             */
            const editor = new E('#content');

            //文档信息
            const doc = ref();
            doc.value={};

            //文章内容信息
            const content = ref();
            content.value = {};

            //用来获取浏览器地址内容
            const route = useRoute();

            //记录浏览器中的 docId
            const docId=ref();
            docId.value=-1;

            //加载信息参数
            const loading = ref();
            loading.value=true;



            /**
             * ------函数区------
             */
            const handelOpen= () => {
                loading.value=true;
                axios.get("/doc/").then( (resp) => {
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


            onMounted(() => {
                editor.create();
            });


            /**
             * ------返回------
             */
            return {
                loading,
                doc,
                content
            }
        }
    }

</script>


<style scoped>
    .docEditPlace .el-row{
        margin-top: 20px;
    }

</style>