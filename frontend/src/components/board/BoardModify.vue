<template>
  <b-container class="bv-example-row mt-3">
    <h3>글 수정</h3>
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardModify",
  components: { BoardInputItem },
};
</script>

<style scoped>
h3 {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 40px;
}
</style>
