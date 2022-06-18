<template>
  <div>
    <div id="map" class="position-relative"></div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const mapStore = "mapStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      // 화면에 표시할 지점 설정
      markerPositions: [],
      //  화면에 표시된 marker들을 저장
      markers: [],
      // infowindow: null,
      // 선택되는 주변 상가
      curState: ["", "CS2", "CE7", "SC4", "OL7", "HP8", "PM9", "SW8", "BK9"],
    };
  },
  created() {
    this.markerPositions = this.mapInfo;
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      // 스크립트 객체 생성
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=54eb590ff2f217ee89f42f58c253277a&libraries=services,clusterer,drawing";
      // document의 head에 script 추가
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(mapStore, ["mapInfo", "type"]),
  },
  watch: {
    mapInfo() {
      this.markerPositions = this.mapInfo;
      this.displayMarker(this.markerPositions);
    },
    // 주변 상가 가져오기
    type() {
      if (this.type == 9) {
        this.removeMarker();
      } else if (this.type != 0) {
        console.log("10이 아님 " + this.type);
        this.displayType();
      }
    },
  },
  methods: {
    ...mapMutations(mapStore, ["SET_TYPE", "CLEAR_MAP_INFO"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.1081964, 128.413952),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    // 마커 표시 메소드
    displayMarker(markerPositions) {
      // 1. 현재 표시되어있는 마커들이 있으면 marker에 등록된 map을 없애준다.
      // console.log(markerPositions);
      console.log("마카스 !! : " + this.markers);
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      this.markers = [];
      // 마커 이미지 설정

      // 2. 마커 표시하기
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(position.lat, position.lng)
      );

      console.log("마커찍는곳안에 경위도 정보 : " + positions);
      if (positions.length > 0) {
        for (let index = 0; index < positions.length; index++) {
          let imgSrc = "";
          switch (markerPositions[index].type) {
            case 0:
              imgSrc = require("@/assets/home.png");
              break;
            case 1:
              imgSrc = require("@/assets/store.png");
              break;
            case 2:
              imgSrc = require("@/assets/coffee-marker.png");
              break;
            case 3:
              imgSrc = require("@/assets/school.png");
              break;
            case 4:
              imgSrc = require("@/assets/gas-station.png");
              break;
            case 5:
              imgSrc = require("@/assets/hospital.png");
              break;
            case 6:
              imgSrc = require("@/assets/pharmacy.png");
              break;
            case 7:
              imgSrc = require("@/assets/bus-stop.png");
              break;
            case 8:
              imgSrc = require("@/assets/bank.png");
              break;
          }
          const imgSize = new kakao.maps.Size(50, 50);
          const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
          // console.log(markerPositions[index]);
          const marker = new kakao.maps.Marker({
            map: this.map,
            position: positions[index], // 마커 위치
            title: markerPositions[index].name, // 마우스 오버시 표시할 제목
            image: markerImage, // 마커 이미지
          });

          let content = `
            <div class="customoverlay">
              <a href="https://map.kakao.com/link/map/11394059" target="_blank">
               <span class="title">
            ${this.markerPositions[index].name}
            </span> 
              </a> 
            </div>`;

          const customoverlay = new kakao.maps.CustomOverlay({
            position: positions[index],
            content: content,
            yAnchor: 1,
          });

          kakao.maps.event.addListener(marker, "mouseover", () => {
            // console.log("이벤트 발생");
            customoverlay.setMap(this.map);
          });

          kakao.maps.event.addListener(marker, "mouseout", () => {
            customoverlay.setMap(null);
          });

          kakao.maps.event.addListener(marker, "click", () => {
            if (markerPositions[index].type == 0) {
              this.$router.push({
                name: "aptDetail",
                params: {
                  aptCode: markerPositions[index].aptCode,
                },
              });
            }
          });

          this.markers.push(marker);
        }

        //  3. 지도 이동
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    // 주변 편의 시설 마커 출력
    displayType() {
      // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      var ps = new kakao.maps.services.Places(this.map);
      // 현재 type를 이용해서 어떤 주변 편의 시설인지 확인
      var point = this.curState[this.type];

      ps.categorySearch(point, this.placesSearchCB, { useMapBounds: true });
    },

    placesSearchCB(data, status) {
      console.log("현재 타입 :   " + this.type);
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          this.markerPositions.push({
            lng: data[i].x,
            lat: data[i].y,
            name: data[i].place_name,
            type: this.type,
          });
        }
        console.log("지금 markers  " + this.markerPositions);
        this.displayMarker(this.markerPositions);
      } else {
        alert("정보가 없습니다 !! ");
      }
    },

    // 마커 초기화
    removeMarker() {
      console.log("삭제할 markers " + this.markers);
      for (var i = 1; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
      this.CLEAR_MAP_INFO();
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 100%;
  height: 100vh;
  z-index: 1;
}
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #ffba00;
  background: #ffba00
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
