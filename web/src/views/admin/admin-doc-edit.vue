<template>

    <el-container class="docEditPlace" direction="vertical" v-loading="loading">
        <el-row>
            <el-col :span="20" :offset="2">
                <el-input placeholder="请输入标题" v-model="doc.name">
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
                        v-model="doc.description">
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

            // //文章内容信息
            // const content = ref();
            // content.value = {};

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
                //获得地址栏中的 docId
                docId.value = route.query.docId;
                queryDoc(docId.value);
                queryContent(docId.value);

                //给文章0.5秒的加载时间
                setTimeout(loading.value=false,500);
            }

            /**
             * 查找文章信息（不包括文章内容）
             */
            const queryDoc = (id) => {
                axios.get("/doc/find/"+id).then( (resp) => {
                    const data = resp.data;
                    if (data.success) {

                        doc.value = data.content;

                    } else {
                        ElMessage("文章加载错误！")
                    }
                });
            }


            /**
             * 查找文章内容
             */
            const queryContent = (id) => {
                axios.get("/doc/find-content/"+id).then( (resp) => {
                    const data = resp.data;
                    if (data.success) {
                        editor.txt.html(data.content);
                    } else {
                        ElMessage("文章内容加载错误！")
                    }
                });
            }



            onMounted(() => {
                editor.create();
                handelOpen();
            });


            /**
             * ------返回------
             */
            return {
                loading,
                doc,
            }
        }
    }

</script>


<style scoped>
    .docEditPlace .el-row{
        margin-top: 20px;
    }

</style>