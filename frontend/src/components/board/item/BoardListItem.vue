<template>
  <b-table :items="this.boards" :fields="fields"></b-table>
</template>

<script>
import moment from "moment";
import { selectTag } from "@/api/board";

export default {
  name: "BoardListItem",
  data() {
    return {
      board: {
        no: this.no,
        title: this.title,
        user_id: this.user_id,
        registDate: this.registDate,
        view: this.view,
        tag_no: this.tag_no,
        tag_name: "",
      },
    };
  },
  props: {
    no: Number,
    title: String,
    user_id: String,
    registDate: String,
    view: Number,
    tag_no: String,
  },

  created() {
    selectTag(
      this.board.no,
      (response) => {
        this.board.tag_name = response.data;
      },
      (error) => {
        console.log("에러발생", error);
      }
    );
  },

  filters: {
    dateFormat(registDate) {
      return moment(new Date(registDate)).format("YY.MM.DD");
    },
    newArticle(format) {
      let today = moment(new Date()).format("YY.MM.DD");
      if (format == today) {
        return "new";
      } else {
        return null;
      }
    },
  },
  methods: {},
};
</script>

<style scoped>
.td {
  text-align: center;
}
.router {
  font-size: 17px;
  color: #6d9773;
}
.badge-style {
  background-color: #ffba00;
}
</style>
