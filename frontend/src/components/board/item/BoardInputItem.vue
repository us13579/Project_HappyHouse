<template>
  <b-row id="row" class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <!-- 태그 start -->

        <!-- 글 작성 - 태그 -->

        <b-form-group
          id="tag-group"
          label="태그"
          label-for="tag"
          v-if="this.type === 'register'"
        >
          <b-form-select
            id="tag_no"
            v-model="board.tag_no"
            :options="options_a"
            class="mt-1"
            v-if="board.user_id == 'admin'"
          ></b-form-select>
          <b-form-select
            id="tag_no"
            v-model="board.tag_no"
            :options="options_u"
            class="mt-1"
            v-else
          ></b-form-select>
        </b-form-group>

        <!-- 글 수정 - 태그 -->
        <b-form-group id="tag-group" label="태그" label-for="tag" v-else>
          <b-form-select
            id="tag_no"
            v-model="board.tag_no"
            :options="options_a"
            class="mt-1"
            v-if="board.user_id == 'admin'"
          ></b-form-select>
          <b-form-select
            id="tag_no"
            v-model="board.tag_no"
            :options="options_u"
            class="mt-1"
            v-else
          ></b-form-select>
        </b-form-group>

        <!-- 글 수정 - 태그 -->

        <!-- 태그 end -->

        <!-- 작성자 start -->

        <b-form-group id="user_id-group" label="작성자" label-for="user_id">
          <b-form-input
            id="user_id"
            v-model="board.user_id"
            type="text"
            readonly
          ></b-form-input>
        </b-form-group>

        <!-- 작성자 end -->

        <!-- 제목 start -->

        <!-- 글 작성 - 제목 -->
        <b-form-group
          id="title-group"
          label="제목"
          label-for="title"
          v-if="this.type === 'register'"
        >
          <b-form-input
            id="title"
            v-model="board.title"
            type="text"
            required
            placeholder="제목을 입력하세요"
          ></b-form-input>
        </b-form-group>

        <!-- 글 수정 - 제목 -->
        <b-form-group id="title-group" label="제목" label-for="title" v-else>
          <b-form-input
            id="title"
            v-model="board.title"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <!-- 제목 end -->

        <!-- 내용 start -->

        <!-- 글 작성 - 내용 -->
        <b-form-group
          id="content-group"
          label="내용"
          label-for="content"
          v-if="this.type === 'register'"
        >
          <b-form-textarea
            id="content"
            v-model="board.content"
            placeholder="내용을 입력하세요"
            rows="10"
            max-rows="20"
          ></b-form-textarea>
        </b-form-group>

        <!-- 글 수정 - 내용 -->
        <b-form-group
          id="content-group"
          label="내용"
          label-for="content"
          v-else
        >
          <b-form-textarea
            id="content"
            v-model="board.content"
            required
            rows="10"
            max-rows="20"
          ></b-form-textarea>
        </b-form-group>

        <!-- 내용 end -->

        <!-- 버튼 -->

        <b-button
          type="submit"
          class="button m-1"
          v-if="this.type === 'register'"
          >글 작성</b-button
        >
        <b-button type="submit" class="button m-1" v-else>글 수정</b-button>
        <b-button type="reset" class="button m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { selectBoard, insertBoard, updateBoard } from "@/api/board";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      // 가져온 데이터 저장할 객체
      board: {
        no: 0,
        title: "",
        content: "",
        user_id: "",
        data: "",
        view: "",
        tag_no: "",
      },
      tag_no: null,
      options_a: [
        { value: null, text: "태그를 선택해 주세요" },
        { value: 1, text: "공지사항" },
        { value: 2, text: "잡담" },
        { value: 3, text: "꿀팁" },
      ],
      options_u: [
        { value: null, text: "태그를 선택해 주세요" },
        { value: 1, text: "공지사항", disabled: "disabled" },
        { value: 2, text: "잡담" },
        { value: 3, text: "꿀팁" },
      ],
    };
  },
  props: {
    // 글 작성인지 수정인지 확인
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  // 생성되자마자 데이터가져오기
  created() {
    this.board.user_id = this.userInfo.id;
    if (this.type === "modify") {
      selectBoard(
        this.$route.params.no,
        ({ data }) => {
          this.board = data;
        },
        (error) => {
          console.log("데이터 에러발생!!", error);
        }
      );
    }
  },
  methods: {
    // 버튼 눌렀을 때 작성하지 않은 부분에 커서
    onSubmit(event) {
      event.preventDefault();

      let error = true;
      let msg = "";
      !this.board.tag_no && ((msg = "태그를 선택해주세요!"), (error = false));
      error &&
        !this.board.title &&
        ((msg = "제목을 입력해주세요!"),
        (error = false),
        this.$ref.title.focus());
      error &&
        !this.board.content &&
        ((msg = "내용을 입력해주세요!"),
        (error = false),
        this.$ref.content.focus());

      // 만약 에러가 있을 경우 메세지 출력
      if (!error) alert(msg);
      // 에러가 없을 경우 입력 상태면 입력 함수를 수정 상태면 수정 함수를 호출
      else {
        this.type === "register" ? this.insertBoard() : this.updateBoard();
      }
    },
    onReset(event) {
      event.preventDefault();
      this.board.no = 0;
      this.board.tag_no = 0;
      this.board.title = "";
      this.board.content = "";
      this.$router.push({ name: "boardList" });
    },
    insertBoard() {
      console.log(this.board);
      insertBoard(
        {
          no: 0,
          title: this.board.title,
          content: this.board.content,
          user_id: this.board.user_id,
          date: "",
          view: 0,
          tag_no: this.board.tag_no,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateBoard() {
      console.log(this.board);
      console.log(typeof this.board.tag_no);
      updateBoard(
        {
          no: this.board.no,
          title: this.board.title,
          content: this.board.content,
          date: "",
          tag_no: this.board.tag_no,
        },
        (response) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (response.data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style scoped>
.button {
  background-color: #6d9773;
  border-color: white;
  border-radius: 12px;
}
</style>
