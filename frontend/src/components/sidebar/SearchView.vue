<template>
  <div id="side-bar">
    <b-container class="container make-scroll" right shadow>
      <!-- 시, 구, 동 선택 -->
      <div id="search" class="mt-4">
        <!-- 시/도 -->
        <!-- v-model="selectedSido"  -->
        <div class="search-bar">
          <div class="full mr-3">
            <b-form-select
              v-model="isSelectedSido"
              :options="sidos"
              size="lg"
              class="sido"
              @change="gugunList"
            >
            </b-form-select>
          </div>
          <!-- 구/ 군 -->
          <div class="full mr-3">
            <b-form-select
              v-model="isSelectedGugun"
              :options="guguns"
              size="lg"
              class="gugun"
              @change="dongList"
            ></b-form-select>
          </div>
          <!-- 읍  / 면/ 동 -->
          <!-- :disabled="isSelected" 설정 -->
          <div class="full">
            <b-form-select
              v-model="isSelectedDong"
              :options="dongs"
              size="lg"
              class="dong"
            ></b-form-select>
          </div>
        </div>
        <div class="mr-2">
          <!-- 검색 버튼 -->
          <b-button @click="aptSearch" class="search-btn full mr-2" size="lg">
            검색</b-button
          >
        </div>
      </div>

      <div id="like-area" v-if="isLogin">
        <b-button-group
          size="sm"
          class="mr-2 mt-2"
          v-for="(area, index) in likeArea"
          :key="index"
        >
          <b-button class="like-btn" @click="likeAptList(area.dongCode)"
            >{{ area.gugunName }} {{ area.dongName }}</b-button
          >
          <b-button class="like-icon" @click="deleteArea(area.no)"
            ><font-awesome-icon icon="fa-solid fa-star" class="icon"
          /></b-button>
        </b-button-group>
      </div>
      <!-- url에 따라서 변경되는 화면  -->
      <router-view
        :key="$route.fullPath"
        @area-select-box="AreaSetInfo"
        @apt-select-box="AptSetInfo"
      ></router-view>
      <h1 id="back">
        <font-awesome-icon @click="back" icon="fa-solid fa-arrow-left" />
      </h1>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { deleteLikeArea } from "@/api/like.js";

const aptStore = "aptStore";
const userStore = "userStore";
const likeStore = "likeStore";
const mapStore = "mapStore";

export default {
  data() {
    return {
      // isSelected: false,
      isSelectedSido: null,
      isSelectedGugun: null,
      isSelectedDong: null,
    };
  },
  computed: {
    ...mapState(aptStore, [
      "sidos",
      "guguns",
      "houses",
      "dongs",
      "dongsPoint",
      "selectedSido",
      "selectedGugun",
      "selectedDong",
    ]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeArea"]),
  },
  watch: {
    likeArea() {},
    isSelectedSido() {
      this.SET_SELECTED_SIDO(this.isSelectedSido);
    },
    isSelectedGugun() {
      this.SET_SELECTED_GUGUN(this.isSelectedGugun);
    },
    isSelectedDong() {
      this.SET_SELECTED_DONG(this.selectedDong);
    },
  },
  created() {
    this.CLEAR_SIDO_LIST(); // 초기화
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido(); // sidolist 가져오기
  },
  mounted() {
    if (this.isLogin) {
      this.getLikeArea(this.userInfo.id); // 사용자 getLikeArea 가져오기
      this.getLikeApt(this.userInfo.id);
    }
  },
  methods: {
    ...mapActions(aptStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "areaName",
    ]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_SELECTED_SIDO",
      "SET_SELECTED_GUGUN",
      "SET_SELECTED_DONG",
    ]),
    ...mapMutations(mapStore, ["SET_LNG", "SET_LAT"]),
    ...mapActions(likeStore, ["getLikeArea", "getLikeApt"]),
    //  검색 버튼 눌렀을 때
    async aptSearch() {
      if (this.isSelectedDong) {
        this.getHouseList(this.isSelectedDong);
        this.areaName(this.isSelectedDong);
        // 현재 컴포넌트가 search 컴포넌트면 이동하지 않는다.
        if (this.$route.path !== "/search") {
          this.$router.push({
            name: "aptView",
          });
        }
      }
    },
    gugunList() {
      // this.isSelectedSido = e.target.value;
      this.CLEAR_GUGUN_LIST();
      this.isSelectedGugun = null;
      // if (this.isSelectedSido) this.getGugun(e.target.value);
      if (this.isSelectedSido) this.getGugun(this.isSelectedSido);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.isSelectedDong = null;
      if (this.isSelectedGugun) this.getDong(this.isSelectedGugun);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
    deleteArea(no) {
      deleteLikeArea(
        no,
        () => {
          this.getLikeArea(this.userInfo.id);
        },
        () => {}
      );
    },
    // 관심 지역 버튼 눌렀을 때 처리
    // select box 관심지역 처리
    // 검색 처리
    //
    likeAptList(dongCode) {
      let payload = {
        sido: dongCode.substr(0, 2),
        gugun: dongCode.substr(0, 5),
        dong: dongCode,
      };
      this.AreaSetInfo(payload);
    },
    // 관심 지역 이벤트 듣는 것
    async AreaSetInfo(payload) {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.isSelectedSido = payload.sido;
      await this.getGugun(payload.sido);
      this.isSelectedGugun = payload.gugun;
      await this.getDong(payload.gugun);
      this.isSelectedDong = payload.dong;
      // 검색 버튼 누를때 부르는 메소드 호출
      await this.aptSearch();
    },
    // 관심 아파트 이벤트 들음
    async AptSetInfo(payload) {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.isSelectedSido = payload.sido;
      await this.getGugun(payload.sido);
      this.isSelectedGugun = payload.gugun;
      await this.getDong(payload.gugun);
      this.isSelectedDong = payload.dong;
      // 아파트 검색 메소드 호출
      this.$router.push({
        name: "aptDetail",
        params: {
          aptCode: payload.aptCode,
        },
      });
    },
    back() {
      this.$router.back();
    },
  },
  components: {},
};
</script>

<style scoped>
#back {
  position: fixed;
  /* left: 0; */
  right: 40px;
  bottom: 40px;
  background-color: #0c3b2e;
  color: #ffba00;
  border-radius: 50%;
  padding: 4px 10px 8px 12px;
  text-align: center;
}
.container {
  height: 100vh;
  overflow: auto;
}
.container::-webkit-scrollbar {
  width: 10px;
}
.container::-webkit-scrollbar-thumb {
  background-color: #ffba00;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.container::-webkit-scrollbar-track {
  background-color: #0c3b2e;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
#like-area .like-btn {
  background-color: white;
  border-color: #ffba00;
  color: #0c3b3e;
}
#like-area .like-btn:hover {
  background-color: #ffba00;
  border-color: #ffba00;
  color: white;
}

#like-area .like-icon {
  background-color: white;
  border-color: #ffba00;
  color: #ffba00;
}

#like-area .like-icon:hover {
  background-color: #ffba00;
  border-color: #ffba00;
  color: white;
}

#search .search-btn {
  background-color: #0c3b3e;
}

#search .search-btn:hover {
  color: #0c3b3e;
  background-color: white;
}

#search .sido,
#search .gugun,
#search .dong {
  border-color: #0c3b3e;
  color: #0c3b3e;
  width: 100%;
}

#search .full {
  width: 100%;
}

#search {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  /* margin-top: 30px; */
}

#search .search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 80%;
}
</style>
