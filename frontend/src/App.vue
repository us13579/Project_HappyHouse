<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNav from "@/components/HeaderNav.vue";

export default {
  components: {
    HeaderNav,
  },
};
</script>

<style>
/* 구글 폰트 추가 */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap");
@import url("@/css/palette.css");

* {
  font-family: "Noto Sans KR", sans-serif;
  /* overflow-x: hidden; */
}

/* 폰트 굵기 변경 */
h1 {
  font-weight: 500;
}
h3 {
  font-weight: 400;
}
h4 {
  font-weight: 100;
}
</style>
