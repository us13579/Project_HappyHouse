<template>
  <div id="aptDetail">
    <!-- 아파트 이름 (관심 지역 추가) -->
    <b-row>
      <b-col class="mt-3 mb-2">
        <h4 class="green-color">
          {{ house.apartmentName }}
          <span class="ml-2" v-if="isLogin">
            <font-awesome-icon
              v-if="!isSelected"
              class="ml-2 non-selected-heart"
              icon="fa-regular fa-heart"
              @click="addApt"
            />
            <font-awesome-icon
              v-if="isSelected"
              class="ml-2 selected-heart"
              icon="fa-solid fa-heart"
              @click="deleteApt"
            />
          </span>
        </h4>
      </b-col>
    </b-row>
    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartDetail"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >아파트 정보</b-button
      >
    </div>
    <b-collapse id="apartDetail" visible>
      <b-card class="mr-2"
        ><b-table-simple striped class="mt-3 b-table-style">
          <b-thead>
            <b-tr>
              <b-th class="col-md-4 head">분류</b-th>
              <b-th class="col-md-5 head">정보</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr>
              <b-th class="col-md-3 head">도로명 주소</b-th>
              <b-th class="col-md-9 head"
                >{{ house.sidoName }} {{ house.gugunName }} {{ house.roadName
                }}<br />
                ({{ house.dongName }}, {{ house.apartmentName }})
              </b-th>
            </b-tr>
            <b-tr>
              <b-th class="col-md-2 head">법정동 주소</b-th>
              <b-th class="col-md-9 head"
                >{{ house.sidoName }} {{ house.gugunName }}
                {{ house.dongName }} {{ house.jibun }}<br />
                ({{ house.apartmentName }})
              </b-th>
            </b-tr>
            <b-tr>
              <b-th class="col-md-2 head">설립 연도</b-th>
              <b-th class="col-md-9 head">{{ house.buildYear }}년</b-th>
            </b-tr>
          </tbody>
        </b-table-simple></b-card
      >
    </b-collapse>

    <!-- 아파트 정보 end -->

    <!-- 거래내역그래프 start -->

    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartGraph"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >거래 내역 그래프</b-button
      >
    </div>

    <b-collapse id="apartGraph" visible>
      <b-card class="mr-2"
        ><div align="center">
          <b-button class="b-button ml-2 mr-2" @click="allList">5년</b-button>
          <b-button class="b-button ml-2 mr-2" @click="oneYearList"
            >1년</b-button
          >
          <b-button class="b-button ml-2 mr-2" @click="groupYearList"
            >연도별</b-button
          >
        </div>

        <!-- 그룹 출력 -->
        <div v-if="this.type === 'group'">
          <line-chart-view class="mt-4" :dealList="dealList" :type="this.type">
          </line-chart-view>
          <table class="table-style mt-4">
            <thead>
              <th>평균거래가격</th>
              <th>년</th>
              <th>거래량</th>
            </thead>
            <tbody>
              <tr v-for="(deal, index) in dealList" :key="index">
                <td>{{ deal.dealAmount | houseAmount }}</td>
                <td>{{ deal.dealYear }}</td>
                <td>{{ deal.count }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <!-- 기본 출력 -->
        <div v-else>
          <line-chart-view class="mt-4" :dealList="dealList" :type="this.type">
          </line-chart-view>
          <div class="chartAreaWrapper">
            <table class="table-style mt-4">
              <thead>
                <th>거래가격</th>
                <th>층</th>
                <th>전용면적</th>
                <th>년</th>
                <th>월</th>
                <th>일</th>
              </thead>
              <tbody>
                <tr v-for="(deal, index) in dealList" :key="index">
                  <td>{{ deal.dealAmount | houseAmount }}</td>
                  <td>{{ deal.floor }}</td>
                  <td>{{ deal.area | housesize }} 평</td>
                  <td>{{ deal.dealYear }}</td>
                  <td>{{ deal.dealMonth }}</td>
                  <td>{{ deal.dealDay }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </b-card>
    </b-collapse>

    <!-- 거래내역그래프 end -->

    <!-- 주변 편의 시설 start -->

    <div class="mr-2">
      <b-button
        v-b-toggle
        href="#apartSide"
        @click.prevent
        block
        class="button mt-3 pt-3"
        >주변 편의 시설</b-button
      >
    </div>
    <b-collapse id="apartSide" visible align="center">
      <b-card class="mr-2">
        <h5 class="green-color mt-3">
          편의 시설
          <font-awesome-icon icon="fa-solid fa-map-location-dot" class="icon" />
          <b-button class="b-button-one ml-5" @click="open(9)">초기화</b-button>
        </h5>
        <div>
          <b-button class="b-button mr-1 mt-3" @click="open(1)"
            >편의점</b-button
          >
          <b-button class="b-button mr-1 mt-3" @click="open(2)">카페</b-button>
          <b-button class="b-button mr-1 mt-3" @click="open(3)">학교</b-button>
          <b-button class="b-button mr-1 mt-3" @click="open(4)"
            >주유소</b-button
          >
        </div>
        <div>
          <b-button class="b-button mr-1 mt-3" @click="open(5)">병원</b-button>
          <b-button class="b-button mr-1 mt-3" @click="open(6)">약국</b-button>
          <b-button class="b-button mr-1 mt-3" @click="open(7)"
            >지하철</b-button
          >
          <b-button class="b-button mr-1 mt-3" @click="open(8)">은행</b-button>
        </div>
      </b-card>
    </b-collapse>
    <!-- 주변 편의 시설 end -->
  </div>
</template>

<script>
import LineChartView from "@/components/sidebar/LineChartView.vue";
import {
  selectHouse,
  listHouse,
  listHouseOneYear,
  listHouseYear,
} from "@/api/apt/";
import { mapActions, mapState, mapMutations } from "vuex";
import { addLikeApt, deleteLikeApt } from "@/api/like.js";

const userStore = "userStore";
const likeStore = "likeStore";
const mapStore = "mapStore";

export default {
  data() {
    return {
      name: "AptDetailView",
      components: { selectHouse, listHouse },
      house: {},
      stickyHeader: true,
      dealList: [],
      type: "",
      isSelected: false,
      aptCode: null,
      no: null,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(likeStore, ["likeApt"]),
  },
  components: {
    LineChartView,
  },
  created() {
    this.allList();
    selectHouse(
      this.$route.params.aptCode,
      (response) => {
        this.house = response.data[0];
      },
      (error) => {
        console.log("아파트정보를 가져오지 못했습니다.", error);
      }
    );
    this.aptCode = this.$route.params.aptCode;
    // 하트 아이콘 상태 처리
    this.isSelected = false;
    this.likeApt.forEach((element) => {
      if (element.aptCode == this.aptCode) {
        this.isSelected = true;
        this.no = element.no;
        return false;
      }
    });
  },
  watch: {
    likeApt() {
      console.log("likeApt 리스트 상태변경");
      this.isSelected = false;
      this.likeApt.forEach((element) => {
        if (element.aptCode == this.aptCode) {
          this.isSelected = true;
          return false;
        }
      });
    },
  },
  filters: {
    housesize(value) {
      return Math.round((value / 3.305785) * 100) / 100;
    },
    houseAmount(value) {
      let a = parseInt(value);
      let a1 = Math.floor(a / 10);
      let a2 = a % 10;

      if (a < 10) {
        return a + "천만원";
      } else {
        if (a2 == 0) {
          return a1 + "억";
        } else {
          return a1 + "억 " + a2 + "천만원";
        }
      }
    },
  },
  methods: {
    ...mapActions(likeStore, ["getLikeApt"]),
    ...mapMutations(mapStore, ["SET_TYPE"]),
    allList() {
      this.type = "all";
      listHouse(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },
    oneYearList() {
      this.type = "one";
      listHouseOneYear(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },
    groupYearList() {
      this.type = "group";
      listHouseYear(
        this.$route.params.aptCode,
        (response) => {
          this.dealList = response.data;
        },
        (error) => {
          console.log("아파트정보를 가져오지 못했습니다.", error);
        }
      );
    },
    open(type) {
      console.log("type을 선택했씁니다 : " + type);
      this.SET_TYPE(type);
    },

    async addApt() {
      const data = {
        user_id: this.userInfo.id,
        houseinfo_aptCode: this.aptCode,
      };
      await addLikeApt(
        data,
        () => {
          this.getLikeApt(this.userInfo.id);
        },
        () => {}
      );
    },
    async deleteApt() {
      console.log(this.no);
      await deleteLikeApt(
        this.no,
        () => {
          this.getLikeApt(this.userInfo.id);
        },
        () => {}
      );
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
.b-button-one {
  width: 100px;
  border: #ffba00;
  border-style: solid;
  border-width: 5px;
  color: black;
  font-weight: bold;
  background-color: white;
}

.button {
  background-color: #6d9773;
  border-color: #6d9773;
  height: 60px;
  font-size: 20px;
  font-weight: bold;
}

#aptDetail .icon {
  color: #ffba00;
}

.table-style {
  border-collapse: collapse;
  width: 100%;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
}
.chartAreaWrapper {
  height: 500px;
  overflow-y: scroll;
}

.table-style thead {
  background-color: #6d9773;
  color: #ffffff;
  font-weight: 100;
}

.table-style th,
.table-style td {
  padding: 12px 15px;
}

.table-style tbody tr {
  border-bottom: 1px solid #dddddd;
}

.table-style tbody tr:nth-of-type(even) {
  background-color: rgb(109, 151, 115, 0.2);
}

.table-style tbody tr:last-of-type {
  border-bottom: 2px solid #6d9773;
}

.b-table-style {
  border-collapse: collapse;
  width: 100%;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
}

.b-table-style b-thead b-tr {
  background-color: #6d9773;
  color: #ffffff;
  font-weight: 100;
}

.b-table-style b-th,
.b-table-style b-td {
  padding: 12px 15px;
}

.b-table-style b-tbody b-tr {
  border-bottom: 1px solid #dddddd;
}

.b-table-style b-tbody b-tr:nth-of-type(even) {
  background-color: rgb(109, 151, 115, 0.2);
}

.b-table-style b-tbody b-tr:last-of-type {
  border-bottom: 2px solid #6d9773;
}
.non-selected-heart,
.selected-heart {
  color: #d62042;
}
</style>
