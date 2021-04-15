<template>
    <el-container>

        <el-card class="box-card">
            <el-form ref="form" :model="user" label-width="80px">
                <el-form-item label="活动名称">
                    <el-input v-model="user.name"></el-input>
                </el-form-item>
                <el-form-item label="活动区域">
                    <el-select v-model="user.region" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>



                <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即创建</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-card>

    </el-container>

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



            return {
                user
            }
        }
    }
</script>

<style scoped>
    .box-card {
        width: 480px;
        height: 300px;
    }

</style>