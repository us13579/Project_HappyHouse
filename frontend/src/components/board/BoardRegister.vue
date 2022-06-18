<template>
  <b-container class="container bv-example-row mt-3">
    <h3>글 작성</h3>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardRegister",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 40px;
}
</style>
