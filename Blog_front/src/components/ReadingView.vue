<script setup>
  import blogCardView from "./BlogCardView.vue"
  import blogDisplayView from "./BlogDisplayView.vue"
  import returnIcon from "@icon-park/vue-next/lib/icons/Return"
  import {ref, onMounted} from "vue";
  import {getBlogAPI, getAllBlogAPI} from "@/api/blogAPI";
  import {getUserInfoAPI} from "@/api/userAPI";
  import {currentBlog, setCurrentBlog} from "@/global";

  const blogs = ref([]);

  onMounted(async () => {
    const response = await getAllBlogAPI();
    blogs.value.push.apply(blogs.value, response);
  })

  const currentMode = ref("brief")

  const changeMode = (mode) => {
    currentMode.value = mode;
  }

  const jumpToRead = async (blogId, title, content, userId) => {
    setCurrentBlog(blogId, title, content);
    await getUserInfoAPI(userId)
        .then((response) => {
          currentBlog.value.username = response.userName;
          currentBlog.value.userType = response.type;
        });
    changeMode('detail');
  }
</script>

<template>
  <blog-card-view v-if="currentMode==='brief'"
                  v-for="blog in blogs"
                  :title="blog.title"
                  :date="blog.time"
                  @click="jumpToRead(blog.blogId, blog.title, blog.content, blog.userId);"/>
  <div class="blog-display" v-else>
    <div style="display: flex; align-items: center">
      <div id="back-btn" @click="changeMode('brief')">
        <return-icon class="icon" theme="outline" size="24" fill="#ffffff"/>
      </div>
      <div style="margin-left: 50px">发布者：{{currentBlog.username}}</div>
      <div style="margin-left: 50px">发布者类型：{{currentBlog.userType}}</div>
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