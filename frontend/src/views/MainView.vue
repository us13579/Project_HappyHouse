<template>
  <div>
    <b-row>
      <b-col sm="8"><kakao-map></kakao-map></b-col>
      <b-col sm="4" id="right-view"><search-view></search-view></b-col>
    </b-row>
  </div>
</template>

<script>
import KakaoMap from "@/components/KakaoMap.vue";
import SearchView from "@/components/sidebar/SearchView.vue";

export default {
  data() {
    return {
      state: "button",
    };
  },
  components: { KakaoMap, SearchView },
};
</script>

<style></style>
