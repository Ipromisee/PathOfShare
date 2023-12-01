<script setup>
  import blogCardView from "./BlogCardView.vue"
  import blogDisplayView from "./BlogDisplayView.vue"
  import returnIcon from "@icon-park/vue-next/lib/icons/Return"
  import {ref, onMounted} from "vue";
  import {getBlogAPI, getAllBlogAPI} from "@/api/blogAPI";
  import {setCurrentBlog} from "@/global";

  const blogs = ref([]);

  onMounted(async () => {
    const response = await getAllBlogAPI();
    blogs.value.push.apply(blogs.value, response);
  })

  const currentMode = ref("brief")

  const changeMode = (mode) => {
    currentMode.value = mode;
  }
</script>

<template>
  <blog-card-view v-if="currentMode==='brief'"
                  v-for="blog in blogs"
                  :title="blog.title"
                  :date="blog.time"
                  @click="setCurrentBlog(blog.blogId, blog.title, blog.content);changeMode('detail')"/>
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