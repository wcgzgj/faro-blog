<template>
    <el-menu
            class="the-header"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            router
            active-text-color="#ffd04b">
        <el-menu-item index="/" text-color="#303133">
            <el-link :underline="false" >
                FARO_Z的博客
            </el-link>
        </el-menu-item>

        <el-menu-item index="/" >
            <el-link :underline="false" icon="el-icon-s-home">
                首页
            </el-link>
        </el-menu-item>
        <el-menu-item index="/admin/doc">
            <el-link :underline="false" icon="el-icon-s-management">
                文章管理
            </el-link>
        </el-menu-item>
        <el-menu-item index="/admin/user">
            <el-link :underline="false" icon="el-icon-user-solid">
                用户管理
            </el-link>
        </el-menu-item>
        <el-menu-item index="/report">
            <el-link :underline="false" icon="el-icon-s-marketing">
                报表统计
            </el-link>
        </el-menu-item>
        <el-menu-item index="/about">
            <el-link :underline="false" icon="el-icon-info">
                关于我
            </el-link>
        </el-menu-item>


        <!--登录及用户名显示-->
        <div style="margin-right: 40px">
            <router-link to="#" class="login-menu" v-show="user.id">
                <span>你好: {{user.name}}</span>
            </router-link>

            <router-link to="/login" class="login-menu" @click="showLoginModal" v-show="!user.id">
                <span>登录</span>
            </router-link>
        </div>

    </el-menu>





</template>

<script>
    import { defineComponent, onMounted, ref, createVNode ,computed} from 'vue';
    import axios from 'axios';
    import store from '@/store'
    import { ElMessage } from 'element-plus'
    export default {
        name: "the-header",


        setup() {
            const user = computed(() => store.state.user);



            /**
             * ------方法区------
             */


            /**
             * 退出登录
             */
            const logout = () => {
                console.log("退出登录!")

                axios.get("/user/logout/"+user.value.token).then((response)=>{
                    const data = response.data;
                    if (data.success) {
                        loginModalVisible.value=false;
                        ElMessage.success("退出登录成功!")

                        /**
                         * 退出登录时
                         * 将 sessionStorage 中对应 user的信息清空
                         * 因为
                         */
                        store.commit("setUser", {});
                    } else {
                        /**
                         * 使用 antd 的组件，弹出错误信息
                         */
                        ElMessage.error(data.message);
                    }
                });
            }

            onMounted(()=> {

            });

            return {
                user

            }
        }
    }
</script>

<style scoped>
    .login-menu {
        float: right;
        color: white;
        padding-left: 10px;
        text-decoration: none;
    }

</style>