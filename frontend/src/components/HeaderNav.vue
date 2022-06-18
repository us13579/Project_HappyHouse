<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-light">
      <!-- Brand/logo -->
      <router-link
        class="navbar-brand flex-row d-flex align-items-center"
        to="/"
      >
        <img src="@/assets/logo2.png" alt="logo" class="brand-logo pt-1" />
        <div class="text-center yellow-color">HappyHouse</div>
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target=".collapsibleNavbar"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Links -->
      <div class="collapse navbar-collapse collapsibleNavbar">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/board" class="nav-link text-white"
              >커뮤니티</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/board" class="nav-link text-white"></router-link>
          </li>
        </ul>
      </div>
      <div
        class="collapse navbar-collapse collapsibleNavbar justify-content-end"
      >
        <ul class="navbar-nav">
          <!-- 로그인 사용자 이름 출력 -> 누르면 회원정보 수정으로 이동 -->
          <li class="nav-item" v-if="isLogin">
            <router-link
              class="nav-link text-white"
              :to="{ name: 'detail', params: { id: userInfo.id } }"
              >{{ userInfo.name }}</router-link
            >
          </li>
          <!-- 관리자 로그인 시 회원 정보 관리 창 표시 -->
          <li class="nav-item" v-if="isAdmin">
            <router-link class="nav-link text-white" to="/admin"
              >회원정보</router-link
            >
          </li>
          <li class="nav-item" v-if="!isLogin">
            <router-link class="nav-link text-white" to="/user/login"
              >로그인</router-link
            >
          </li>
          <li class="nav-item text-white" v-if="isLogin" @click="logout">
            <router-link class="nav-link text-white" to="/"
              >로그아웃</router-link
            >
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";
const likeStroe = "likeStore";

export default {
  computed: {
    ...mapState(userStore, ["isAdmin", "isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_IS_ADMIN",
    ]),
    ...mapMutations(likeStroe, ["CLEAR_LIKE_AREA_APT"]),

    // 로그아웃 시 데이터 지우기
    logout() {
      this.SET_USER_INFO(null);
      this.SET_IS_LOGIN(false);
      this.SET_IS_ADMIN(false);
      this.CLEAR_LIKE_AREA_APT(); // like 정보 삭제
      sessionStorage.removeItem("access-token");
    },
  },
};
</script>

<style>
.navbar {
  padding: 15px;
  background-color: #0c3b2e;
}
.nav-link {
  font-size: 17px;
  font-weight: 400;
}

.brand-logo {
  width: 40px;
  margin-right: 1rem;
}
</style>
