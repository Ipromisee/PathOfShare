<script setup>
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import {currentUser} from "@/global";
import { addBlogAPI } from "@/api/blogAPI";
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {ElMessage} from "element-plus";

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('Hello, World!')
const titleInput = ref("")

const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

const handleChange = (editor) =>{
}

const postBlog = async () => {
  const data = {
    "content": valueHtml.value,
    "title": titleInput.value
  }
  await addBlogAPI(data)
      .then(() => {
        ElMessage.success("发布成功")
      })
}

</script>

<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
        @onChange="handleChange"
    />
  </div>

  <div id="footer">
    <div style="width: 10%; text-align: end">标题：</div>
    <el-input v-model="titleInput" style="width: 30%; margin-right: 10px"/>
    <el-button type="primary" size="large" @click="postBlog">发布</el-button>
  </div>
</template>

<style scoped>
  #footer {
    display: flex;
    justify-content: end;
    margin-top: 1%;
    align-items: center;
  }
</style>