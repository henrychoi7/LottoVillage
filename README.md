# 2017-2학기 서버프로그래밍 기말 프로젝트

<br>

## 1\. 프로젝트 주제

### 모바일·웹 기반 복권번호 추천 및 Reward 서비스

<br>

## 2\. 팀 구성

- 팀장 : 유수곤
- 팀원 : 경다현, 하채영, 최한동

<br>

## 3\. 개발 진도표

## v0.3 (_30%_)

- **서버**

```
/app/controllers/index.server.controller.js
/app/controllers/product.server.controller.js
/app/controllers/user.server.controller.js

/app/routes/index.server.route.js
/app/routes/product.server.route.js
/app/routes/user.server.route.js

/views/index.ejs

/config/express.js
/config/winston.js

/index.js
/package.json
/package-lock.json
```

<br>

- **웹 클라이언트 (/assets/, DB 연동 제외)**

```
/lottoVillage_login.html
/lottoVillage_main.html
/lottoVillage_rewardAdmin.html
/lottoVillage_rewardModify.html
/lottoVillage_rewardRegist.html
```

<br>

- **모바일 클라이언트 (_/res/ 제외_)**

```
/activities/LoginActivity.kt
/activities/MainActivity.kt
/activities/RegisterActivity.kt
/activities/SplashActivity.kt

/base/AddCookiesInterceptor.kt
/base/BaseActivity.kt
/base/BaseApplication.kt
/base/ClearEditText.kt
/base/PasswordEditText.kt
/base/ReceivedCookiesInterceptor.kt

/interfaces/RetrofitInterface.kt

/model/DataClasses.kt

/services/SmsBroadcastReceiver.kt
```
<br>

## v0.5 (_50%_)

- **서버**

```
/app/mobile/controllers/participation.server.controller.js
/app/mobile/controllers/schedule.server.controller.js
/app/mobile/controllers/token.server.controller.js

/app/mobile/routes/participation.server.route.js

/app/web/../..js
// 이하 mobile 디렉토리와 동일

/config/express.js
/config/winston.js

/index.js
/package.json
/package-lock.json
```

<br>

- **웹 클라이언트 (/assets/, DB 연동 제외)**

```
/lottoVillage_login.html
/lottoVillage_main.html
/lottoVillage_rewardAdmin.html
/lottoVillage_rewardModify.html
/lottoVillage_rewardRegist.html
```

<br>

- **모바일 클라이언트 (_/res/ 제외_)**

```
/activities/InformationActivity.kt
/activities/MainActivity.kt
/activities/PointHistory.kt
/activities/PurchaseHistoryActivity.kt

/adapters/LottoAdapter.kt
/adapters/ParticipationAdapter.kt
/adapters/WinningInfoAdapter.kt

/fragments/HomeFragment.kt
/fragments/LottoFragment.kt
/fragments/StoreFragment.kt

/base/AddCookiesInterceptor.kt
/base/BaseActivity.kt
/base/BaseApplication.kt
/base/ClearEditText.kt
/base/PasswordEditText.kt
/base/ReceivedCookiesInterceptor.kt

/assets/
```
