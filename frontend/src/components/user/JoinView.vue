<template>
  <div class="mb-5">
    <h3 class="green-color">회원가입</h3>
    <b-form-group
      label="아이디"
      label-for="id"
      class="text-left ml-5 mr-5 green-color"
    >
      <b-form-input
        id="id"
        placeholder="아이디를 입력해주세요."
        :value="user.id"
        v-model.trim="user.id"
        @keyup="isAvailableId"
      ></b-form-input>
      <b-form-text v-if="isId">{{ isIdText }} </b-form-text>
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
        @click="registUser"
        id="login-btn"
        type="button"
        class="mr-5 ml-5 mb-3"
        >확인</b-button
      >
    </div>
  </div>
</template>

<script>
import { joinUser, isUsedId } from "@/api/user.js";

export default {
  data() {
    return {
      isId: false,
      isIdText: null,
      user: {
        id: null,
        password: null,
        name: null,
        email: null,
      },
    };
  },
  methods: {
    registUser() {
      joinUser(
        this.user,
        ({ data }) => {
          if (data) {
            alert("회원가입을 완료했습니다.");
            this.$router.push({ name: "login" });
          } else {
            alert("회원가입을 실패했습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    isAvailableId() {
      if (this.user.id.length > 0) {
        isUsedId(
          this.user.id,
          ({ data }) => {
            if (data) {
              this.isId = true;
              this.isIdText = "사용 가능한 아이디입니다.";
            } else {
              this.isId = true;
              this.isIdText = "사용 불가능한 아이디입니다.";
            }
          },
          (error) => {
            console.error(error);
          }
        );
      } else {
        this.isId = false;
      }
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
