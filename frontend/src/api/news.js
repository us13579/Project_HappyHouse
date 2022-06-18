import api from "./http.js";

// Read ( List )
function getNewsDatas(param, success, fail) {
  api.get(`/news`, { params: param }).then(success).catch(fail);
}

export { getNewsDatas };
