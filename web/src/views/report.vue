<template>
    <el-container>
        <div class="about">
            <h1>这是统计页面</h1>
        </div>
        <div>
            <vue3-markdown-it :source='source' />
        </div>
    </el-container>
</template>

<script>
    import VueMarkdownIt from 'vue3-markdown-it';

    export default {
        name: "report",


        setup() {


            return {
                VueMarkdownIt,

                source: '# Hello World!'
            }
        }
    }
</script>

<style scoped>

</style>