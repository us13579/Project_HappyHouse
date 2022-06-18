<template>
  <b-container class="container bv-example-row mt-3">
    <b-row id="community">
      <b-form-select
        class="select"
        v-model="selected"
        :options="options"
        @change="onChange($event)"
      ></b-form-select>
      <b-button class="button ml-2" @click="moveWrite()">글 쓰기</b-button>

      <b-table
        hover
        responsive
        class="mt-3 text-center"
        id="my-table-all"
        :fields="fields"
        :items="items"
        :current-page="currentPage"
        :per-page="perPage"
        @row-clicked="click"
        v-if="selected == 0"
      >
      </b-table>
      <b-table
        hover
        responsive
        class="mt-3 text-center"
        id="my-table"
        :items="tagitems"
        :current-page="currentPage"
        :per-page="perPage"
        :fields="fields"
        @row-clicked="click"
        v-else
      >
      </b-table>
    </b-row>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
      class="mt-3"
      id="page-navi"
    ></b-pagination>
  </b-container>
</template>

<script>
import { listBoard, selectTag } from "@/api/board.js";
//import BoardListItem from "@/components/board/item/BoardListItem.vue";
import { mapState } from "vuex";
import moment from "moment";

const userStore = "userStore";

export default {
  name: "BoardList",
  components: {
    //BoardListItem,
  },
  data() {
    return {
      fields: [
        {
          key: "no",
          label: "번호",
          sortable: true,
          thStyle: {
            width: "10%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
        {
          key: "tag_name",
          label: "글종류",
          thStyle: {
            width: "10%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
        {
          key: "title",
          label: "제목",
          thStyle: {
            width: "50%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
        {
          key: "user_id",
          label: "작성자",
          thStyle: {
            width: "10%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
        {
          key: "registDate",
          label: "날짜",
          sortable: true,
          thStyle: {
            width: "10%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
        {
          key: "view",
          label: "조회수",
          sortable: true,
          thStyle: {
            width: "10%",
            "background-color": "#6d9773",
            color: "white",
          },
        },
      ],
      items: [],
      tagitems: [],
      selected: 0,
      options: [
        { value: 0, text: "글 종류" },
        { value: 1, text: "공지사항" },
        { value: 2, text: "잡담" },
        { value: 3, text: "꿀팁" },
      ],
      currentPage: 1,
      perPage: 10,
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listBoard(
      param,
      (response) => {
        response.data.forEach((element) => {
          let tag_name = "";

          selectTag(
            element.no,
            (response) => {
              tag_name = response.data;
              let item = {
                no: element.no,
                tag_name: tag_name,
                title: element.title,
                user_id: element.user_id,
                registDate: moment(new Date(element.registDate)).format(
                  "YY.MM.DD"
                ),
                view: element.view,
              };
              this.items.push(item);
            },
            (error) => {
              console.log("에러발생", error);
            }
          );
        });
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    rows() {
      return this.items.length;
    },
  },
  methods: {
    // 글쓰기 누르면 글 작성 component로 이동
    moveWrite() {
      if (this.userInfo === null) {
        alert("로그인을 해주십시오");
      } else {
        this.$router.push({ name: "boardRegister" });
      }
    },
    // 원하는 글 종류만 출력
    onChange(event) {
      let list = this.items.filter((item) => {
        let str = item.tag_name;
        if (str.includes(this.options[event].text)) {
          return true;
        }
      });
      this.tagitems = list;
    },
    click(record) {
      this.$router.push({ name: "boardDetail", params: { no: record.no } });
    },
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
};
</script>

<style scoped>
/* #page-nav {
  position: fixed;
  bottom: 30px;
} */

.select {
  width: 120px;
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
  color: white;
}
.title {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
  color: white;
}

.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>
