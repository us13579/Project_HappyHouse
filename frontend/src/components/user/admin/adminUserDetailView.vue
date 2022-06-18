<template>
  <div class="mb-5">
    <h3 class="green-color">회원 정보</h3>
    <b-form-group
      label="아이디"
      label-for="id"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input id="id" disabled :value="user.id"></b-form-input>
    </b-form-group>
    <b-form-group
      label="비밀번호"
      label-for="password"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="password"
        placeholder="비밀번호를 입력해주세요."
        :value="user.password"
        v-model="user.password"
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label="이름"
      label-for="name"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="name"
        placeholder="이름을 입력해주세요."
        :value="user.name"
        v-model="user.name"
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label="이메일"
      label-for="email"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="email"
        placeholder="email을 입력해주세요."
        :value="user.email"
        v-model="user.email"
      ></b-form-input>
    </b-form-group>
    <div class="button-setting">
      <b-button
        id="login-btn"
        type="submit"
        class="mr-5 ml-5 mb-3"
        @click="updateUser"
        >수정</b-button
      >
      <b-button
        id="delete-btn"
        type="submit"
        class="mr-5 ml-5 mb-3"
        variant="outline-secondary"
        @click="deleteUser"
        >삭제</b-button
      >
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import { findById, updateUser, deleteUser } from "@/api/user.js";
const userStore = "userStore";

export default {
  props: {
    userDetail: Object,
  },
  data() {
    return {
      user: null,
    };
  },
  computed: {},
  created() {
    this.user = this.userDetail;
  },
  mounted() {},
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    updateUser() {
      updateUser(
        this.user,
        () => {
          findById(
            this.user.id,
            ({ data }) => {
              console.log(data);
              this.user = data.userInfo;
              alert("업데이트 완료");
              this.$router.push({ name: "admin" });
            },
            () => {}
          );
        },
        () => {}
      );
    },
    deleteUser() {
      deleteUser(
        this.user.id,
        () => {
          // this.SET_USER_INFO(null);
          // this.SET_IS_LOGIN(false);
          // sessionStorage.removeItem("access-token");
          alert("삭제 완료");
          this.$router.push({ name: "admin" });
        },
        () => {}
      );
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

#login-btn:hover,
#join-btn:hover {
  background-color: #0c3b2e;
  color: white;
  border: 1px solid #0c3b2e;
}
</style>
