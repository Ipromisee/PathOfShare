<script setup>
  import blogCardView from "./BlogCardView.vue"
  import blogDisplayView from "./BlogDisplayView.vue"
  import returnIcon from "@icon-park/vue-next/lib/icons/Return"
  import {ref} from "vue";

  const blogs = ref([
    {
      id: 0,
      title: "title1",
      date: new Date()
    },
    {
      id: 1,
      title: "title2",
      date: new Date()
    }
  ])

  const currentMode = ref("brief")

  const changeMode = (mode) => {
    currentMode.value = mode;
  }
</script>

<template>
  <blog-card-view v-if="currentMode==='brief'" v-for="blog in blogs" :title="blog.title" :date="blog.date" @click="changeMode('detail')"/>
  <div class="blog-display" v-else>
    <div id="back-btn" @click="changeMode('brief')">
      <return-icon class="icon" theme="outline" size="24" fill="#ffffff"/>
    </div>
    <blog-display-view/>
  </div>
</template>

<style scoped>
  .blog-display {
    display: flex;
    flex-direction: column;
  }

  #back-btn {
    cursor: pointer;
    background-color: var(--el-color-primary);
    border-radius: 5px;
    height: 30px;
    width: 30px;
    margin-bottom: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 3px;
  }
</style>