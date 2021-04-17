<template>
    <el-container class="chart">
        <el-main>
            <el-progress type="dashboard" :percentage="80">
                <template #default="{ percentage }">
                    <span class="percentage-label">博客开发进度</span>
                    <br/>
                    <span class="percentage-value">{{ percentage }}%</span>
                </template>
            </el-progress>
        </el-main>
    </el-container>
</template>


<script>
    import { onMounted,ref } from 'vue';

    export default {
        name: "report",

        setup() {


            onMounted( () => {
            })

            return {

            }
        }

    }
</script>

<style scoped>
    .chart {
        align-items: center;
        text-align: center;
    }

</style>