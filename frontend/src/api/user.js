import axios from "./http.js";

async function joinUser(user, success, fail) {
  console.log("inser 들어옴");
  console.log(user);
  await axios.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function isUsedId(id, success, fail) {
  console.log("id 사용 가능 여부 check");
  await axios.get(`/user/${id}`).then(success).catch(fail);
}

// JSON.stringify -> JavaScript 값이나 객체를 JSON 문자열로 변환
async function login(user, success, fail) {
  console.log("api - login");
  console.log(JSON.stringify(user));
  await axios
    .post("/user/login", JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(id, success, fail) {
  axios.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await axios.get(`/user/info/${id}`).then(success).catch(fail);
}

async function updateUser(user, success, fail) {
  await axios.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function deleteUser(id, success, fail) {
  await axios.delete(`/user/${id}`).then(success).catch(fail);
}

async function getUserList(success, fail) {
  await axios.get(`/user/list`).then(success).catch(fail);
}

export {
  isUsedId,
  joinUser,
  login,
  findById,
  updateUser,
  deleteUser,
  getUserList,
};
