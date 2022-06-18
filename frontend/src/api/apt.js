import api from "./http.js";

// Select ( one - detail )
function selectHouse(aptCode, success, fail) {
  api.get(`/house/${aptCode}`).then(success).catch(fail);
}

// Select ( 아파트 거래 정보 )
function listHouse(aptCode, success, fail) {
  api.get(`/house/list/${aptCode}`).then(success).catch(fail);
}

// Select ( 아파트 거래 일년전꺼 까지만 )
function listHouseOneYear(aptCode, success, fail) {
  api.get(`/house/list/year/${aptCode}`).then(success).catch(fail);
}

// Select ( 아파트 거래 일년을 단위로 그룹 )
function listHouseYear(aptCode, success, fail) {
  api.get(`/house/list/group/${aptCode}`).then(success).catch(fail);
}
export { selectHouse, listHouse, listHouseOneYear, listHouseYear };
