<template>

    <el-container >

        <el-main >


            <div class="toTop">

                <!--文章页面-->
                <div class="artical-div">
                    <v-md-editor v-model="docContent"  mode="preview" ref="editor"/>
                </div>
            </div>

            <el-backtop target=".toTop"  :visibility-height="10" :bottom="100"></el-backtop>

        </el-main>


    </el-container>


</template>

<script>
    import { onMounted,ref } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import {useRoute} from "vue-router";
    import {Tool} from "@/util/tool";
    import router from "../router/index.ts";



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

                handelOpen
            }

        }
    }
</script>

<style>

    .box-card {
        height: 100%;
    }

    .artical-div {
        margin: auto;
        border-width: 10px;
        border-color: #333333;
        padding: 10px;
        background-color: #B3C0D1;
        border-radius: 4px;
        width: 60%;
    }

    .toTop {
        height: 100vh;
        overflow: scroll;
    }



</style>