import api from "@/api/http.js";

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function areaName(params, success, fail) {
  api.get(`/map/area`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/list`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, areaName };
