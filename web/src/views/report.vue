<template>
    <el-container>
        <el-main>
            <html :ref="setRef"></html>
        </el-main>

    </el-container>
</template>


<script>
    import { onMounted,ref } from 'vue';

    export default {
        name: "report",

        setup() {
            const setRef = (el) => {
                myRef.value.push(el)
            }

            // 存储dom数组
            const myRef = ref([]);


            onMounted( () => {
                console.dir(myRef.value)
            })

            return {
                text: "# hello md",
                setRef
            }
        }

    }
</script>

<style scoped>

</style>