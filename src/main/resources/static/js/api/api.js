console.log("api.js");

//카카오 지도 api
    //1. 카카오 지도 객체
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.3218778, 126.8308848), // 지도의 중심좌표 안산시 :37.3218778, 126.8308848
        level : 14 // 지도의 확대 레벨
    });

    //2. 마커 클러스터러를 생성합니다(마커가 여러개 일때 합쳐지는 효과
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 9 // 클러스터 할 최소 지도 레벨
    });

    //3.
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=mLJ1qysDtS9LTzy2oi33T2pMOYtpwH6Uhm9urocRTvAZFxHMu7UkSelTyL4wm4UHZefPL5T0WC8YHt9sYer7mw%3D%3D",
            function(r) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = r.data.map((object) => {
        // 마커 1개 만들어서 리턴하고 markers에 대입
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(object.식당위도, object.식당경도)
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });
// 안산시 원곡동 일반음식점
$.ajax({
    url:"https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=mLJ1qysDtS9LTzy2oi33T2pMOYtpwH6Uhm9urocRTvAZFxHMu7UkSelTyL4wm4UHZefPL5T0WC8YHt9sYer7mw%3D%3D",
    method:"get",
    success: (response)=> {console.log(response);

        let apiTable2= document.querySelector('.apiTable2');
        let html= '';

            response.data.forEach((object)=>{

                html+= `<tr>
                            <th>${object.사업장명}</th>
                            <th>${object.도로명전체주소}</th>
                            <th>${object.대표메뉴1.toLocaleString()}</th>
                            <th>${object.메뉴가격1}</th>
                            <th>${object['대표전화']}</th>
                            <th>${object['주차 가능']}</th>
                       </tr>`

            });
        apiTable2.innerHTML=html;
    }

})  //fend

//안산시 강우량 api
$.ajax({
    url:"https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=mLJ1qysDtS9LTzy2oi33T2pMOYtpwH6Uhm9urocRTvAZFxHMu7UkSelTyL4wm4UHZefPL5T0WC8YHt9sYer7mw%3D%3D",
    method: 'get',
    success: (r)=>{
        console.log(r);

        let apiTable1= document.querySelector('.apiTable1');
        let html='';

            r.data.forEach((object)=>{
                html+=`<tr>
                        <th>${object.관리기관명}</th>
                        <th>${object.날짜}</th>
                        <th>${object.시도명} ${object.시군구명} ${object.읍면동} </th>
                        <th>${object['우량(mm)']}</th>
                       </tr>`
            })
            apiTable1.innerHTML=html;
    }

})