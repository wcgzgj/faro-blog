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


            /**
             * 点击保存按钮
             */
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

            /**
             * 预览窗口关闭
             */
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


    .previewDrawer {
        text-align: center;
    }

</style>