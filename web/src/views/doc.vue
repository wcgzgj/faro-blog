<template>

    <el-container>

        <el-sider style="width: 30%">
            <el-card class="box-card">
                <template #header>
                    <div class="card-header">
                        <span>卡片名称</span>
                        <el-button class="button" type="text">操作按钮</el-button>
                    </div>
                </template>
                <div v-for="o in 4" :key="o" class="text item">
                    {{'列表内容 ' + o }}
                </div>
            </el-card>
        </el-sider>

        <el-main style="width: 70%">
            <!--文章页面-->
            <el-row >
                <el-col :span="18" :offset="3" >
                    <div>
                        <vue3-markdown-it :source='docContent' />
                    </div>
                </el-col>
            </el-row>

            <el-row style="text-align: center">
                <el-col :span="14" :offset="5" >
                    <!--<comment :comments="commentData"></comment>-->
                </el-col>
            </el-row>

        </el-main>

    </el-container>


</template>

<script>
    import E from 'wangeditor';
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import {useRoute} from "vue-router";
    import {Tool} from "@/util/tool";
    import router from "../router/index.ts";

    import 'highlight.js/styles/monokai.css';
    import VueMarkdownIt from 'vue3-markdown-it';


    export default {
        name: "doc",

        setup() {
            /**
             * ------变量区------
             */

            //文档信息
            const doc = ref();
            doc.value={};

            //用来获取浏览器地址内容
            const route = useRoute();

            //记录浏览器中的 docId
            const docId=ref();
            docId.value=-1;

            //加载信息参数
            const loading = ref();
            loading.value=true;

            //显示文档内容
            const docContent = ref();


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
                        docContent.value=data.content;
                    } else {
                        ElMessage("文章内容加载错误！")
                    }
                });
            }


            onMounted(() => {
                handelOpen();
            });


            /**
             * ------返回------
             */
            return {
                loading,
                doc,
                docContent,


                handelOpen,
                VueMarkdownIt
            }

        }
    }
</script>

<style>

    .box-card {
        height: 100%;
    }


</style>