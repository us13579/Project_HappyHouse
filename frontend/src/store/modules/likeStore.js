import { getLikeAptList, getLikeAreaList } from "@/api/like.js";

const likeStore = {
  namespaced: true,
  state: {
    likeArea: null,
    likeApt: null,
    isSelectedArea: false,
    isSelectedApt: null,
  },
  getters: {},
  mutations: {
    SET_LIKE_AREA: (state, areas) => {
      state.likeArea = areas;
    },
    SET_LIKE_APT: (state, apts) => {
      state.likeApt = apts;
    },
    CLEAR_LIKE_AREA_APT: (state) => {
      state.likeArea = null;
      state.likeApt = null;
    },
  },
  actions: {
    async getLikeArea({ commit }, id) {
      console.log("getlikeArea");
      await getLikeAreaList(
        id,
        ({ data }) => {
          console.log(data);
          commit("SET_LIKE_AREA", data);
        },
        () => {}
      );
    },
    async getLikeApt({ commit }, id) {
      await getLikeAptList(
        id,
        ({ data }) => {
          console.log(data);
          commit("SET_LIKE_APT", data);
        },
        () => {}
      );
    },
  },
};

export default likeStore;
