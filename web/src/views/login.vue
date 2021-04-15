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

    <div class="login" clearfix >
        <div class="login-wrap">
            <el-row type="flex" justify="center">
                <el-form ref="loginForm" :model="user" :rules="rules" status-icon label-width="80px">
                    <h3>登录</h3>
                    <hr>
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="user.username" placeholder="请输入用户名" prefix-icon prop="name"></el-input>
                    </el-form-item>
                    <el-form-item id="password" prop="password" label="密码">
                        <el-input v-model="user.password" show-password placeholder="请输入密码" prop="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <router-link to="/">找回密码</router-link>
                        &nbsp;&nbsp;
                        <router-link to="/register">注册账号</router-link>
                    </el-form-item>

                    <el-form-item style="text-align: center ">
                        <el-button type="primary" @click="doLogin()" style="margin-bottom: 20px" >登 录</el-button>
                    </el-form-item>

                </el-form>
            </el-row>
        </div>
    </div>


</template>

<script>
    import { defineComponent,onMounted,ref } from 'vue';
    import axios from "axios";
    import { ElMessage } from 'element-plus'

    export default {
        name: "login",

        setup() {
            /**
             * ------变量区------
             */
            const user =ref();
            user.value={};


            const doLogin = () => {
                // if (!this.user.username) {
                //     this.$message.error("请输入用户名！");
                //     return;
                // } else if (!this.user.password) {
                //     this.$message.error("请输入密码！");
                //     return;
                // } else {
                //     //校验用户名和密码是否正确;
                //     // this.$router.push({ path: "/personal" });
                //     axios
                //         .post("/login/", {
                //             name: this.user.username,
                //             password: this.user.password
                //         })
                //         .then(res => {
                //             // console.log("输出response.data.status", res.data.status);
                //             if (res.data.status === 200) {
                //                 this.$router.push({ path: "/personal" });
                //             } else {
                //                 alert("您输入的用户名或密码错误！");
                //             }
                //         });
                // }
            }

            const validatePass = (rule, value, callback) => {
                // if (value === '') {
                //     callback(new Error('请输入密码'));
                // } else {
                //     if (user.value.password !== '') {
                //         this.$refs.ruleForm.validateField('checkPass');
                //     }
                //     callback();
                // }
            }

            const validatePass2 = (rule, value, callback) => {
                // if (value === '') {
                //     callback(new Error('请再次输入密码'));
                // } else if (value !== this.ruleForm.pass) {
                //     callback(new Error('两次输入密码不一致!'));
                // } else {
                //     callback();
                // }
            }




            return {
                user,

                doLogin,

                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                }
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
        height: 350px;
        margin: 120px auto;
        overflow: hidden;
        line-height: 40px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        border-radius: 4px;
        border: 2px;
        padding: 20px;
    }
    #password {
        margin-bottom: 5px;
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