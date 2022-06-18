<template>
  <b-container>
    <b-row class="">
      <b-col cols="3"></b-col>
      <b-col id="login" class="mt-5 shadow text-center border border-1 rounded">
        <!-- 서비스 이름 -->
        <div class="green-color-bg logo-bg">
          <img class="logo" src="@/assets/logo2.png" alt="logo" />
        </div>
        <router-view></router-view>
      </b-col>
      <b-col cols="3"></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    login() {
      console.log("로그인 처리");
    },
  },
};
</script>

<style scoped>
#login {
  padding: 0;
}

.logo-bg {
  padding-top: 3rem;
  margin-bottom: 2rem;
}

.logo {
  width: 10rem;
}

.button-setting {
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin-top: 2rem;
}

#login-btn,
#join-btn {
  background-color: white;
  color: #0c3b2e;
  border: 1px solid #0c3b2e;
}

#login-btn:hover {
  background-color: #0c3b2e;
  color: white;
  border: 1px solid #0c3b2e;
}

#join-btn:hover {
  background-color: #6d9773;
  color: white;
  border: 1px solid #6d9773;
}
</style>
