<script setup>
import {ref, shallowRef, onBeforeUnmount} from "vue";
import {Editor, Toolbar} from "@wangeditor/editor-for-vue";
import '@wangeditor/editor/dist/css/style.css' // 引入 css

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('Hello, World!')

const toolbarConfig = {}
const editorConfig = {
  placeholder: '请输入内容...',
  readOnly: true
}

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

</script>

<template>
  <div style="border: 1px solid #ccc">
    <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
        @onChange="handleChange"
    />
  </div>
</template>

<style scoped>

</style>