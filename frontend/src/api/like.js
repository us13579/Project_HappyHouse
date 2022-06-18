import api from "./http.js";

async function addLikeApt(data, success, fail) {
  await api
    .post(`/user/like/apt`, JSON.stringify(data))
    .then(success)
    .catch(fail);
}

async function addLikeArea(data, success, fail) {
  await api
    .post(`user/like/area`, JSON.stringify(data))
    .then(success)
    .catch(fail);
}

async function deleteLikeApt(no, success, fail) {
  await api.delete(`user/like/apt/${no}`).then(success).catch(fail);
}

async function deleteLikeArea(no, success, fail) {
  await api.delete(`user/like/area/${no}`).then(success).catch(fail);
}

async function getLikeAptList(id, success, fail) {
  console.log(id);
  await api.get(`user/like/apt/${id}`).then(success).catch(fail);
}

async function getLikeAreaList(id, success, fail) {
  await api.get(`user/like/area/${id}`).then(success).catch(fail);
}

async function getPopularArea(success, fail) {
  await api.get(`map/area/popular`).then(success).catch(fail);
}

export {
  addLikeApt,
  addLikeArea,
  deleteLikeArea,
  deleteLikeApt,
  getLikeAptList,
  getLikeAreaList,
  getPopularArea,
};
