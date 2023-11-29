<script setup>
  import {ref, defineProps} from "vue";

  const props = defineProps(['title', 'date'])

  const date = ref(new Date());
</script>

<template>
  <div id="card-body">
    <div class="title-font">{{props.title}}</div>
    <div class="date-font">{{props.date.toLocaleString()}}</div>
  </div>
</template>

<style scoped>
  #card-body {
    background-color: var(--el-color-primary-light-3);
    cursor: pointer;
    height: 100px;
    width: 95%;
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 10px;
    padding-right: 10px;
    margin-bottom: 15px;
  }
  #card-body:hover {
    background-color: var(--el-color-primary);
  }

  .title-font {
    color: white;
    font-size: 30px;
  }

  .date-font {
    color: white;
    font-size: 24px;
  }
</style>