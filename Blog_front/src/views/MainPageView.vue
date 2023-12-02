<script setup>
  import write from "@icon-park/vue-next/lib/icons/Write";
  import roadSignBoth from "@icon-park/vue-next/lib/icons/RoadSignBoth";
  import readBook from "@icon-park/vue-next/lib/icons/ReadBook";
  import {ref} from "vue";
  import writingView from "@/components/WritingView.vue"
  import readingView from "@/components/ReadingView.vue"
  import {currentUser} from "../global";

  const currentTab = ref("writing");

  const changeTab = (type) => {
    currentTab.value = type;
  }
</script>

<template>
  <el-container>
    <el-header style="background-color: var(--el-color-primary); display: flex; align-items: center">
      <div style="display: flex; margin-left: 2%; justify-content: space-between; width: 100%">
        <div style="display: flex">
          <road-sign-both theme="filled" size="30" fill="#ffffff"/>
          <div style="font-size: 24px; color: white; font-weight: bolder">分享之径</div>
        </div>
        <div style="align-self: center; color: white; font-size: 25px">用户:{{currentUser.userName}}</div>
      </div>
    </el-header>

    <el-container id="main-body">
      <el-menu active-text-color="#409eff">
        <el-menu-item index="1" @click="changeTab('writing')">
          <div class="menu-item">
            <write class="icon" theme="outline" size="20" fill="#409eff"/>
            <span class="menu-item-text">写作</span>
          </div>
        </el-menu-item>
        <el-menu-item index="2" @click="changeTab('reading')">
          <div class="menu-item">
            <read-book class="icon" theme="outline" size="20" fill="#409eff"/>
            <span class="menu-item-text">浏览</span>
          </div>
        </el-menu-item>
      </el-menu>

      <el-main>
        <writing-view v-if="currentTab==='writing'"/>
        <reading-view v-else-if="currentTab==='reading'"/>
      </el-main>
    </el-container>
  </el-container>

</template>

<style scoped>
  #main-body {
    height: calc(100vh - 60px);
  }

  .menu-item {
    display: flex;
  }
  .menu-item-text {
    margin-left: 5px;
    font-size: 16px;
  }
</style>