<template>
  <div>
    <b-container>
      <h1>회원정보 관리</h1>
    </b-container>
    <b-container class="container bv-example-row mt-5">
      <b-row>
        <b-table-simple hover responsive>
          <b-thead>
            <b-tr class="title" align="center">
              <b-th class="col-md-1 head"></b-th>
              <b-th class="col-md-2 head">아이디</b-th>
              <b-th class="col-md-2 head">비밀번호</b-th>
              <b-th class="col-md-3 head">이름</b-th>
              <b-th class="col-md-3 head">이메일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr
              v-for="(user, index) in userList"
              :key="index"
              align="center"
              @click="updateUser(index)"
            >
              <b-td>{{ index + 1 }}</b-td>
              <b-td>{{ user.id }}</b-td>
              <b-td>{{ user.password }}</b-td>
              <b-td>{{ user.name }}</b-td>
              <b-td>{{ user.email }}</b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getUserList, deleteUser } from "@/api/user.js";

export default {
  data() {
    return {
      userList: null,
    };
  },
  created() {
    getUserList(
      ({ data }) => {
        this.userList = data;
      },
      () => {}
    );
  },
  methods: {
    updateUser(index) {
      this.$router.push({
        name: "adminDetail",
        params: { userDetail: this.userList[index] },
      });
    },
    deleteUser(id) {
      let answer = confirm("진짜 삭제하시겠습니까?");
      console.log(answer);
      if (answer) {
        deleteUser(
          id,
          () => {
            getUserList(
              ({ data }) => {
                console.log(data);
                this.userList = data;
              },
              () => {}
            );
          },
          () => {}
        );
      }
    },
  },
};
</script>

<style>
h1 {
  text-align: center;
  margin-top: 60px;
}
</style>
