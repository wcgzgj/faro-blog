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
                <el-button type="primary"
                           @click.native="handelPreview(),drawer = true"
                           icon="el-icon-view">
                    预览
                </el-button>

                <el-button type="primary"
                           @click.native="handelSave()"
                           icon="el-icon-check">
                    保存
                </el-button>
            </el-col>
        </el-row>

        <el-row class="artical-row">
            <el-col :span="20" :offset="2">
                <div id="content"></div>
            </el-col>
        </el-row>

    </el-container>

    <el-drawer
            title="文章预览"
            v-model="drawer"
            :direction="'rtl'"
            size="80%"
            center="true"
            class="previewDrawer"
            :before-close="handleDrawClose" destroy-on-close>

        <div style="height:100%;">
            <el-scrollbar style="height:100%">
                <div class="wangeditor" style="margin: 30px; width:700px;height:700px;" :innerHTML="previewHtml"></div>
            </el-scrollbar>
        </div>


    </el-drawer>


</template>

<script>
    // 管理员文档编辑界面

    import E from 'wangeditor';
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import { ElMessageBox } from 'element-plus';
    import {useRoute} from "vue-router";
    import {Tool} from "@/util/tool";
    import router from "../../router/index.ts";


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

            const drawer = ref();
            drawer.value =false;





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


            /**
             * 富文本预览
             */
            const previewHtml = ref();
            const handelPreview = () => {
                const html = editor.txt.html();
                previewHtml.value = html;
            }


            const handelSave = () => {
                //将文章信息存入 doc 中
                doc.value.content=editor.txt.html()

                console.log(doc.value);
                axios.post("/doc/save", doc.value).then( (resp)=> {
                    const data = resp.data;

                    if (data.success) {
                        ElMessage.success("保存成功");

                        if (Tool.isEmpty(docId.value)) {
                            router.push({ path: '/admin/doc' });
                        }

                    } else {
                        //CommonResp 传回来的错误信息
                        ElMessage.error(data.message);
                    }

                })
            }

            const handleDrawClose = (done) => {
                ElMessageBox.confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
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
                drawer,
                previewHtml,

                handelPreview,
                handelSave,
                handleDrawClose
            }
        }
    }

</script>


<style>
    .docEditPlace .el-row{
        margin-top: 20px;
    }

    .artical-row {
        z-index: 1;
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

    .previewDrawer {
        text-align: center;
    }

</style>