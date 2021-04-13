<template>

    <el-container>

        <el-main>
            <!--文章页面-->
            <el-row >
                <el-col :span="14" :offset="5" >
                    <div class="innerHtml">
                        <div class="wangeditor" style="margin: 30px; " :innerHTML="html"></div>
                    </div>
                </el-col>
            </el-row>

            <el-row style="text-align: center">
                <el-col :span="14" :offset="5" >
                    <comment :comments="commentData"></comment>
                </el-col>
            </el-row>

        </el-main>


        <el-sider>
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
    </el-container>


</template>

<script>
    import E from 'wangeditor';
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import { ElMessageBox } from 'element-plus';
    import {useRoute} from "vue-router";
    import {Tool} from "@/util/tool";
    import router from "../router/index.ts";

    import * as CommentData from '../mockdata'
    import comment from '../components/comment.vue'


    export default {
        name: "doc",

        setup() {
            /**
             * ------变量区------
             */
            const editor = new E('#content');

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
            const html = ref();


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
                        html.value=data.content;
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
                html,
                CommentData,


                handelOpen,
            }

        }
    }
</script>

<style>
    .wangeditor {
        z-index: -1;
    }

    .box-card {
        height: 100%;
    }

    /* wangeditor默认样式, 参照: http://www.wangeditor.com/doc/pages/02-%E5%86%85%E5%AE%B9%E5%A4%84%E7%90%86/03-%E8%8E%B7%E5%8F%96html.html */
    /* table 样式 */
    .wangeditor table {
        border-top: 1px solid #ccc;
        border-left: 1px solid #ccc;
    }
    .wangeditor table td,
    .wangeditor table th {
        border-bottom: 1px solid #ccc;
        border-right: 1px solid #ccc;
        padding: 3px 5px;
    }
    .wangeditor table th {
        border-bottom: 2px solid #ccc;
        text-align: center;
    }

    /* blockquote 样式 */
    .wangeditor blockquote {
        display: block;
        border-left: 8px solid #d0e5f2;
        padding: 5px 10px;
        margin: 10px 0;
        line-height: 1.4;
        font-size: 100%;
        background-color: #f1f1f1;
    }

    /* code 样式 */
    .wangeditor code {
        display: inline-block;
        *display: inline;
        *zoom: 1;
        background-color: #f1f1f1;
        border-radius: 3px;
        padding: 3px 5px;
        margin: 0 3px;
    }
    .wangeditor pre code {
        display: block;
    }

    /* ul ol 样式 */
    .wangeditor ul, ol {
        margin: 10px 0 10px 20px;
    }

    /* 和antdv p冲突，覆盖掉 */
    .wangeditor blockquote p {
        font-family:"YouYuan";
        margin: 20px 10px !important;
        font-size: 16px !important;
        font-weight:600;
    }

    .innerHtml {
        background-color: #E9EEF3;
        padding: 20px;
        margin: 10px;
    }

</style>