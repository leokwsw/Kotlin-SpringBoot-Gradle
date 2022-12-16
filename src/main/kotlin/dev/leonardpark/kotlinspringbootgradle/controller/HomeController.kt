package dev.leonardpark.kotlinspringbootgradle.controller

import org.jsoup.Jsoup
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URL

@RestController
@RequestMapping("/api")
class HomeController {

  @GetMapping("/getIronManData")
  fun getData(): HashMap<String, Any> {
    // 初始化 API 輸出集合
    val response = HashMap<String, Any>()

    // 設定爬蟲會用到的基本參數
    // 鐵人賽網站連結
    val ironManUrl = "https://ithelp.ithome.com.tw/2020-12th-ironman/signup/list"
    var document = Jsoup.connect(ironManUrl).get()
    // 取得全部網站註冊人數
    val totalRegisterPerson = document.select(".contestants-num")[0].text().replace("報名數 ", "").toInt()
    // 取得每頁參加者數量
    val onePageCount = document.select(".contestants-list").size
    // 取得全部頁面數量
    val totalPageCount = totalRegisterPerson / onePageCount + 1
    // 初始化參數
    var challengingCount = 0 // 仍正在挑戰中的人數
    var challengeSuccessCount = 0 // 挑戰成功的人數
    var challengeFailedCount = 0 // 挑戰失敗的人數
    var unchallengedCount = 0 // 已經報名，但未開賽的人數
    // 初始化每日進度集合
    val daysCount = HashMap<String, Int>()
    for (index in 0..30) daysCount[index.toString()] = 0

    // 帶入每頁頁碼參數
    for (page in 1..totalPageCount) {
      // 連結加入頁碼參數
      document = Jsoup.parse(URL("$ironManUrl?page=$page"), 60000)
      // 查詢此頁參加者區塊數量
      val cardSize = document.select(".contestants-list").size
      // 帶入此頁區塊數量
      for (index in 0 until cardSize) {
        // 取得區塊元素 Element
        val item = document.select(".contestants-list")
        // 取得挑戰天數資料
        val challengeDay =
          item.select(".team-dashboard__day")[index].text().replace("DAY ", "").replace("尚未開賽", "0")
        // 將該挑戰天數的挑賽人數 + 1
        if (daysCount[challengeDay] == null) {
          daysCount[challengeDay] = 0
        }
        daysCount[challengeDay] = daysCount[challengeDay]!!.toInt().plus(1)

        // 取得挑戰狀態
        val progressByChallengeStatus = !item.select(".team-progress--challenge").isEmpty()
        val progressByFailStatus = !item.select(".team-progress--fail").isEmpty()

        // 計算挑戰成功、挑戰中、挑戰失敗、已報名未挑戰人數

        if (progressByChallengeStatus && !progressByFailStatus) {
          if (challengeDay.toInt() >= 30) {
            challengeSuccessCount++
          } else {
            challengingCount++
          }
        } else if (!progressByChallengeStatus && progressByFailStatus) {
          if (challengeDay.toInt() > 0) {
            challengeFailedCount++
          } else if (challengeDay.toInt() == 0) {
            unchallengedCount++
          }
        }
      }
    }
    // 儲存 API 結果進行輸出
    response["全部參賽人數"] = totalRegisterPerson
    response["挑戰成功人數"] = challengeSuccessCount
    response["挑戰進行人數"] = challengingCount
    response["挑戰失敗人數"] = challengeFailedCount
    response["挑戰進度文章數量（天/篇）"] = daysCount

    return response
  }
}
