<script setup>
  import roadSignBoth from "@icon-park/vue-next/lib/icons/RoadSignBoth";
  import router from "@/router";
  import {loginAPI} from "@/api/userAPI"
  import {ref} from "vue";
  import {ElMessage} from "element-plus";
  import {currentUser} from "@/global";

  const account = ref("");
  const password = ref("");

  const login = async () => {
    const data = {
      "userId": account.value,
      "passWord": password.value
    }
    await loginAPI(data)
        .then((response) => {
          currentUser.value.id = response.id;
          currentUser.value.userName = response.userName;
          currentUser.value.type = response.type;
          router.push("/main");
        })
        .catch(() => {
          ElMessage.error("登录失败");
        })
  }
</script>

<template>
  <div id="main">
    <div style="background-image: url(/src/assets/road.jpg); width: 50%; height: 100%; background-size: cover; background-position-x: 30%"/>
    <div style="width: 50%; display: flex; flex-direction: column; justify-content: center">
      <div style="margin: -20% 0 5% 3%">
        <div style="display: flex; align-items: center">
          <road-sign-both theme="filled" size="48" fill="#409eff"/>
          <span style="font-size: 40px">分享之径</span>
        </div>
        <div style="max-width: 300px; color: rgb(200,200,200); margin-left: 15%">欢喜承受大自然的每一落笔，笔笔都是天意，生命没有败笔。</div>
      </div>
      <el-row class="login-row">
        <el-col :span="6" class="login-column"><div>账号:</div></el-col>
        <el-col :span="12"><el-input v-model="account" class="login-input"/></el-col>
      </el-row>
      <el-row class="login-row">
        <el-col :span="6" class="login-column"><div>密码:</div></el-col>
        <el-col :span="12"><el-input v-model="password" class="login-input"/></el-col>
      </el-row>
      <el-row style="display: flex; justify-content: center">
        <el-button type="primary" @click="login">登录</el-button>
      </el-row>
    </div>
  </div>
</template>

<style scoped>
  #main {
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    width: 60vw;
    height: 60vh;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.25);
    display: flex;
  }

  .login-row {
    margin: 20px;
  }

  .login-column {
    display: flex;
    align-items: center;
    justify-content: end;
  }

  .login-input {
    margin-left: 10%;
  }
</style>