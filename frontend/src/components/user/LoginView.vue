<template>
  <div class="mb-5">
    <h3 class="green-color">로그인</h3>
    <b-alert show v-if="isLoginError" class="mr-5 mt-3 mb-3 ml-5 alert-style"
      >아이디 또는 비밀번호를 확인하세요.</b-alert
    >
    <b-form-group
      label="아이디"
      label-for="id"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="id"
        placeholder="아이디를 입력해주세요."
        v-model="user.id"
        required
        @keyup.enter="confirm"
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label="비밀번호"
      label-for="password"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        type="password"
        id="password"
        placeholder="비밀번호를 입력해주세요."
        v-model="user.password"
        required
        @keyup.enter="confirm"
      ></b-form-input>
    </b-form-group>

    <div class="button-setting">
      <b-button
        id="login-btn"
        type="button"
        class="mr-5 ml-5 mb-3"
        @click="confirm"
        >로그인</b-button
      >
      <b-button
        id="join-btn"
        type="button"
        class="mr-5 ml-5 mb-3"
        @click="moveJoin"
        >회원가입</b-button
      >
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const likeStroe = "likeStore";

export default {
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    // state 가져오기
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    // action 가져오기
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    ...mapActions(likeStroe, ["getLikeArea", "getLikeApt"]),
    async confirm() {
      // userConfim Action에 user 데이터 전송
      await this.userConfirm(this.user);
      // 접근 토큰 저장
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token); // token을 사용해서 userInfo를 얻어온다
        // 정보얻어오기
        this.$router.push({ name: "main" }); // main으로 이동
      }
    },
    moveJoin() {
      this.$router.push("/user/join");
    },
  },
};
</script>

<style scoped>
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

.alert-style {
  background-color: #6d9773;
  color: white;
}
</style>
