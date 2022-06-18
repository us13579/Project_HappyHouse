<template>
  <div>
    <!-- 찜한 아파트 -->
    <div class="mr-2" v-if="isLogin">
      <b-button
        v-b-toggle
        href="#likeApt"
        @click.prevent
        block
        class="button mt-3 pt-3 show"
        >나의 관심 아파트</b-button
      >

      <b-collapse id="likeApt" visible>
        <b-card id="apt" class="mr-2">
          <b-list-group-item
            href="#"
            class="outline-light list-item"
            v-for="(apt, index) in likeApt"
            :key="index"
            @click="
              moveLikeApt(
                apt.aptCode,
                apt.dongCode,
                apt.lat,
                apt.lng,
                apt.apartmentName
              )
            "
          >
            {{ apt.apartmentName }}
            <span>
              <font-awesome-icon
                class="ml-2 selected-heart"
                icon="fa-solid fa-heart"
              />
            </span>
          </b-list-group-item>
        </b-card>
      </b-collapse>
    </div>

    <!-- 실시간 인기지역 -->

    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#popularArea"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >실시간 인기 지역</b-button
      >

      <b-collapse id="popularArea" visible>
        <b-card id="area" class="mr-2">
          <b-list-group class="mt-3">
            <b-list-group-item
              href="#"
              class="outline-light list-item"
              v-for="(area, index) in popularArea"
              :key="index"
              @click="popularAreaMove(area.dongCode)"
              hover
            >
              {{ index + 1 }}. {{ area.gugunName }} {{ area.dongName }}
            </b-list-group-item>
          </b-list-group>
        </b-card>
      </b-collapse>
    </div>

    <!-- 부동산 관련 뉴스 -->

    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#newsSide"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >오늘의 뉴스</b-button
      >
    </div>
    <b-collapse id="newsSide" visible>
      <b-list-group class="mr-2">
        <news-list-item
          v-for="news in newsList"
          :key="news.title"
          v-bind="news"
        >
        </news-list-item>
      </b-list-group>
    </b-collapse>
  </div>
</template>

<script>
import NewsListItem from "@/components/sidebar/news/NewsListItem.vue";
import { getNewsDatas } from "@/api/news";
import { getPopularArea } from "@/api/like";
import { mapState, mapActions, mapMutations } from "vuex";

const likeStore = "likeStore";
const userStore = "userStore";
const aptStore = "aptStore";
const mapStore = "mapStore";

export default {
  name: "InfoView",
  components: {
    NewsListItem,
  },
  data() {
    return {
      newsList: [],
      popularArea: [],
      pouplarAreaToggle: true,
      newsToggle: true,
      likeAptToggle: true,
    };
  },
  computed: {
    ...mapState(likeStore, ["likeApt"]),
    ...mapState(userStore, ["isLogin"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    getNewsDatas(
      param,
      (response) => {
        this.newsList = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    getPopularArea(
      ({ data }) => {
        this.popularArea = data;
      },
      () => {}
    );
  },
  methods: {
    ...mapActions(aptStore, ["changeSelect", "getDong", "getGugun"]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    ...mapMutations(mapStore, [
      "SET_LNG",
      "SET_LAT",
      "SET_MAP_INFO",
      "SET_TYPE",
    ]),
    AreaToggle() {
      console.log("areaToggle");
      this.pouplarAreaToggle = !this.pouplarAreaToggle;
    },
    NewsToggle() {
      this.newsToggle = !this.newsToggle;
    },
    AptToggle() {
      this.likeAptToggle = !this.likeAptToggle;
    },

    // 인기 지역 누르면 검색이벤트 발생시키는 메소드
    popularAreaMove(code) {
      let sido = code.substr(0, 2);
      let gugun = code.substr(0, 5);
      let dong = code;
      let payload = {
        sido,
        gugun,
        dong,
      };
      // 상위 SearchView로 emit 이벤트 발생 시킴
      // 지역코드를 payload로 넘겨준다.
      this.$emit("area-select-box", payload);
    },
    // 관심 아파트 누르면 검색 이벤트 발생시키는 메소드
    moveLikeApt(aptCode, dongCode, lat, lng, name) {
      let payload = {
        sido: dongCode.substr(0, 2),
        gugun: dongCode.substr(0, 5),
        dong: dongCode,
        aptCode: aptCode,
      };
      let info = [
        {
          lat: lat,
          lng: lng,
          name: name,
          type: 0,
          aptCode: aptCode,
        },
      ];
      this.SET_TYPE(0);
      this.SET_MAP_INFO(info);
      this.$emit("apt-select-box", payload);
    },
  },
};
</script>

<style scoped>
.b-button {
  width: 100px;
  background-color: #ffba00;
  border: #ffba00;
  color: white;
  font-weight: bold;
}

.button {
  background-color: rgb(109, 151, 115);
  border-color: #6d9773;
  height: 60px;
  font-size: 20px;
  font-weight: bold;
}
b-col {
  margin: 10px;
}
.icon {
  color: #6d9773;
}
.list-item {
  border: none;
}
.toggle-set {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
}
.non-selected-heart,
.selected-heart {
  color: #d62042;
}
</style>
