<template>
    <!--<el-container>-->

    <!--    <el-card class="box-card">-->
    <!--        <el-form ref="form" :model="user" label-width="80px">-->
    <!--            <el-form-item label="活动名称">-->
    <!--                <el-input v-model="user.name"></el-input>-->
    <!--            </el-form-item>-->
    <!--            <el-form-item label="活动区域">-->
    <!--                <el-select v-model="user.region" placeholder="请选择活动区域">-->
    <!--                    <el-option label="区域一" value="shanghai"></el-option>-->
    <!--                    <el-option label="区域二" value="beijing"></el-option>-->
    <!--                </el-select>-->
    <!--            </el-form-item>-->



    <!--            <el-form-item>-->
    <!--                <el-button type="primary" @click="onSubmit">立即创建</el-button>-->
    <!--                <el-button>取消</el-button>-->
    <!--            </el-form-item>-->
    <!--        </el-form>-->
    <!--    </el-card>-->

    <!--</el-container>-->

    <el-container>
        <div class="login" clearfix >
            <div class="login-wrap">
                <el-row type="flex" justify="center">
                    <el-form ref="loginForm" :model="user" status-icon label-width="80px">
                        <h3>登录</h3>
                        <hr>
                        <el-form-item  label="用户名">
                            <el-input v-model="user.loginName" placeholder="请输入用户名" prefix-icon ></el-input>
                        </el-form-item>

                        <el-form-item id="password" label="密码">
                            <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
                        </el-form-item>

                        <!--<el-form-item id="password2" label="确认密码">-->
                        <!--    <el-input v-model="user.password2" show-password placeholder="请确认密码"></el-input>-->
                        <!--</el-form-item>-->


                        <el-form-item>
                            <router-link to="/find-password">找回密码</router-link>
                            &nbsp;&nbsp;
                            <router-link to="/register">注册账号</router-link>
                        </el-form-item>

                        <el-form-item style="text-align: center ">
                            <el-button type="primary" @click="doLogin" style="margin-bottom: 20px" >登 录</el-button>
                        </el-form-item>

                    </el-form>
                </el-row>
            </div>
        </div>
    </el-container>


</template>

<script lang="ts">
    import { defineComponent,onMounted,ref } from 'vue';
    import axios from "axios";
    import { ElMessage } from 'element-plus'
    import store from "@/store";
    import {Tool} from "@/util/tool";

    declare let hexMd5;
    declare let KEY;

    export default {
        name: "login",

        setup() {
            /**
             * ------变量区------
             */
            const user =ref();
            user.value={};


            const doLogin = () => {
                console.log("开始登录!")

                //密码未输入，则不进行 MD5 加密
                if(!Tool.isEmpty(user.value.password)) {
                    user.value.password = hexMd5(user.value.password + KEY);
                }

                console.log("登录、前端加密后："+user.value.password)

                axios.post("/user/login",user.value).then((response)=>{

                    const data = response.data;
                    if (data.success) {

                        ElMessage.success("登录成功!")
                        /**
                         * setUser: vuex 中 mutations中的方法
                         *
                         * 后面的参数，都是我们在 mutations 中自定义方法的参数
                         * state 参数因为是自带的，所以没有必要写
                         */
                        store.commit("setUser",data.content);
                    } else {
                        ElMessage.error(data.message);
                    }
                });
            }



            return {
                user,

                doLogin
            }
        }
    }
</script>

<style scoped>
    /*.box-card {*/
    /*    width: 480px;*/
    /*    height: 300px;*/
    /*}*/


    .login {
        width: 100%;
        height: 740px;
        /*height: 100%;*/
        /*background: url("../assets/bg.jpg") no-repeat;*/
        background-size: cover;
        overflow: hidden;
    }
    .login-wrap {
        background-size: cover;
        width: 400px;
        height: 400px;
        margin: 120px auto;
        overflow: hidden;
        line-height: 40px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        border-radius: 4px;
        border: 2px;
        padding: 20px;
    }

    h3 {
        color: #0babeab8;
        font-size: 24px;
    }
    hr {
        background-color: #444;
        margin: 20px auto;
    }
    a {
        text-decoration: none;
        color: #aaa;
        font-size: 15px;
    }
    a:hover {
        color: coral;
    }


    .el-button {
        width: 80%;
        margin-left: -50px;
    }


</style>