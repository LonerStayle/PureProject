package app.dummy

import business.entities.*

val dummyUsers = listOf(
    KhaosUser.INIT_TEMP,
    KhaosUser(
        id = UserId(0),
        name = "AAA",
        email = "AAA0000@gmail.com",
        thumbUrl = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=13333624&filePath=L2Rpc2sxL25ld2RhdGEvMjAyMy8yMS9DTFMxMDAwNC83MjY3YmQyMS1lNDQ0LTQwMjUtYTFiMS1hOTVmOGMyZDZjYjE=&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10004",
        myImages = listOf(
            KhaosImage(id = ImageId(0), uploadUserId = UserId(0), "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=13266916&filePath=L2Rpc2sxL25ld2RhdGEvMjAyMC8yMS9DTFMxMDAwNC8xMzI2NjkxNl9XUlRfMjFfQ0xTMTAwMDRfMjAyMDEyMThfMQ==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10004"),
            KhaosImage(id = ImageId(1), uploadUserId = UserId(0), "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=13239949&filePath=L2Rpc2sxL25ld2RhdGEvMjAyMC85OC9DTFMxMDAwNC8xMzIzOTk0OV9XUlRfMjAyMDAxMTZfMQ==&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10004")
        )
    ),
    KhaosUser(
        id = UserId(1),
        name = "BBB",
        email = "BBB0000@gmail.com",
        thumbUrl = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=13323417&filePath=L2Rpc2sxL25ld2RhdGEvMjAyMi8yMS9DTFMxMDAwNi8xMzMyMzQxN19XUlQ=&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006",
        myVideos = listOf(
            KhaosVideo(id = VideoId(0), uploadUserId = UserId(1), "https://streaming.copyright.or.kr:8080/upload/encoding/video/2023/05/1c811efcc9994eddaf493439624bae28_media1.mp4"),
            KhaosVideo(id = VideoId(1), uploadUserId = UserId(1), "https://streaming.copyright.or.kr:8080/upload/encoding/video/2021/06/fffae24147ad4f4997bbc32fa127c10f_media1.mp4")
        )
    ),
    KhaosUser(
        id = UserId(2),
        name = "CCC",
        email = "CCC0000@gmail.com",
        thumbUrl = "https://gongu.copyright.or.kr/gongu/wrt/cmmn/wrtFileImageView.do?wrtSn=13323889&filePath=L2Rpc2sxL25ld2RhdGEvMjAyMi8yMS9DTFMxMDAwNi8xMzMyMzg4OV9XUlQ=&thumbAt=Y&thumbSe=b_tbumb&wrtTy=10006",
        myFiles = emptyList()
    )
)