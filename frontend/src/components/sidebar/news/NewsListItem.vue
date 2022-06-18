<template>
  <b-card
    class="m-1"
    bg-variant="light"
    text-variant="black"
    :title="news.newsTitle"
  >
    <b-card-text>
      {{ news.newsWriting }} | {{ news.newsDate || dateFormat }}
      <br />
      {{ news.newsContent }}
    </b-card-text>
    <b-button :href="news.newsLink" target="_blank" class="button"
      >본문 보기</b-button
    >
  </b-card>
</template>

<script>
import moment from "moment";

export default {
  name: "NewsListItem",
  data() {
    return {
      news: {
        newsTitle: this.newsTitle,
        newsContent: this.newsContent,
        newsLink: this.newsLink,
        newsWriting: this.newsWriting,
        newsDate: this.newsDate,
      },
    };
  },
  props: {
    newsTitle: String,
    newsContent: String,
    newsLink: String,
    newsWriting: String,
    newsDate: String,
  },
  filters: {
    dateFormat(newsDate) {
      return moment(new Date(newsDate)).format("YYYY년MM월DD일");
    },
  },
};
</script>

<style scoped>
.button {
  background-color: #0c3b2e;
}
</style>
