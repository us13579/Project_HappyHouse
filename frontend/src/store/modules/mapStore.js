const mapStore = {
  namespaced: true,
  state: {
    lng: 128.413952,
    lat: 36.1081964,
    type: 0,
    name: "SSAFY",
    mapInfo: [
      {
        lng: 128.413952,
        lat: 36.1081964,
        name: "SSAFY",
      },
    ],
  },
  getters: {},
  mutations: {
    SET_LNG: (state, lng) => {
      state.lng = [];
      for (let index = 0; index < lng.length; index++) {
        state.lng.push(lng[index]);
      }
    },
    SET_LAT: (state, lat) => {
      state.lat = [];
      // console.log(lat);
      for (let index = 0; index < lat.length; index++) {
        state.lat.push(lat[index]);
      }
    },
    SET_TYPE: (state, type) => {
      state.type = type;
    },
    SET_MAP_INFO: (state, info) => {
      state.mapInfo = info;
    },
    CLEAR_MAP_INFO: (state) => {
      let apt = state.mapInfo[0];
      state.mapInfo = [apt];
    },
  },
  actions: {},
};

export default mapStore;
