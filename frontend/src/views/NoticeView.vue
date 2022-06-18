<template>
  <div>
    <div class="noticetitle">
      <h1>사용자 게시판</h1>
    </div>
    <notice-list></notice-list>
  </div>
</template>

<script>
import NoticeList from "@/components/notice/NoticeList.vue";

export default {
  components: {
    NoticeList,
  },
};
</script>

<style>
.noticetitle {
  margin-top: 60px;
}

h1 {
  text-align: center;
}
</style>
