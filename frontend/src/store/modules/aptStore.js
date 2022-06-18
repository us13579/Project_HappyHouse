import {
  sidoList,
  gugunList,
  houseList,
  dongList,
  areaName,
} from "@/api/map.js";

const aptStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "읍/면/동" }],
    dongsPoint: [],
    houses: [],
    house: null,
    code: null,
    areaName: null,
    selectedSido: null,
    selectedGugun: null,
    selectedDong: null,
  },

  getters: {},

  mutations: {
    SET_SELECTED_SIDO: (state, code) => {
      state.selectedSido = code;
    },
    SET_SELECTED_GUGUN: (state, code) => {
      state.selectedGugun = code;
    },
    SET_SELECTED_DONG: (state, code) => {
      state.selectedDong = code;
    },
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
        state.dongsPoint.push({
          name: dong.dongName,
          lat: dong.lat,
          lng: dong.lng,
        });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "읍/면/동" }];
      state.dongsPoint = [];
    },
    SET_CODE: (state, dong) => {
      state.code = dong;
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_CODE_NAME: (state, data) => {
      let name = `${data.sidoName} ${data.gugunName} ${data.dongName}`;
      state.areaName = name;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getGugun({ commit }, sidoCode) {
      console.log("getGugun!!! " + sidoCode);
      const params = {
        sido: sidoCode,
      };
      await gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getDong({ commit }, gugunCode) {
      console.log("getDong" + gugunCode);
      const params = {
        gugun: gugunCode,
      };

      await dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },

    areaName: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      areaName(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_CODE", data.dongCode);
          commit("SET_CODE_NAME", data);
        },
        () => {}
      );
    },
    changeSelect: ({ commit }, payload) => {
      console.log(payload);
      console.log(commit);
      // this.getDong(commit, payload.sido);
    },
  },
};

export default aptStore;
